package cn.ha5fun.redcover.controller;

import cn.ha5fun.redcover.pojo.Order;
import cn.ha5fun.redcover.pojo.RedCover;
import cn.ha5fun.redcover.pojo.Sign;
import cn.ha5fun.redcover.service.OrderService;
import cn.ha5fun.redcover.service.RedCoverService;
import cn.ha5fun.redcover.service.SignService;
import cn.ha5fun.redcover.utils.MD5Utils;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author chen
 * @Company ha5fun.com
 * @Description 订单管理
 * @Date 2021/1/27 6:37 下午
 * @Version 1.0.0
 */
@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    RedCoverService redCoverService;
    @Autowired
    MD5Utils md5Utils;
    @Autowired
    SignService signService;
    @PostMapping("createOrder")
    public ResponseEntity<Object> createOrder(int coverId,String ticket,String nickname){
        JSONObject jsonObject = new JSONObject();
        // 查找该封面获取需求
        RedCover oneRedCover = redCoverService.getOneRedCover(coverId);
        List<Order> orders = orderService.selOrderByTicket(coverId,ticket,nickname);
        System.out.println(orders.size());
        if (orders.size() != 0) {
            jsonObject.put("code",50002);
            jsonObject.put("message","订单已存在");
            return ResponseEntity.ok(jsonObject);
        }
        if (oneRedCover == null){
            jsonObject.put("code",50003);
            jsonObject.put("message","封面不存在");
            return ResponseEntity.ok(jsonObject);
        }
        // new Order对象
        Order order = new Order();
        order.setCoverId(coverId)
                .setNickname(nickname)
                .setTicket(ticket)
                .setLookVideoLockNum(0)
                .setInviteLockNum(0)
                .setFree(oneRedCover.getIsFree())
                .setHasReceive(false);
        if (oneRedCover.getIsFree()){
            order.setIsReceive(true);
        }else{
            order.setIsReceive(false);
        }
        System.out.println(order);
        boolean isCreate = orderService.createOrder(order);

        if(isCreate){
            jsonObject.put("code",200);
            jsonObject.put("message","创建订单成功");
            jsonObject.put("orderData",order);
        }else {
            jsonObject.put("code",50001);
            jsonObject.put("message","创建订单失败");
            jsonObject.put("orderData",null);
        }
        return ResponseEntity.ok(jsonObject);
    }

    @GetMapping("getOneOrder")
    public ResponseEntity<JSONObject> getOneOrder(int coverId,int orderId,String ticket,String nickname){
        JSONObject jsonObject = new JSONObject();
        Order order = orderService.selOneOrder(orderId);
        System.out.println(coverId+"xx"+orderId+"xx"+ticket);
        if (order == null ){
            jsonObject.put("code",50501);
            jsonObject.put("orderData",null);
            jsonObject.put("message","订单不存在");
            return ResponseEntity.ok(jsonObject);
        }
        List<Order> orders = orderService.selOrderByTicket(coverId, ticket,nickname);
        if (orders.size() != 0) {
            for (Order getOrder : orders) {
                if(redCoverService.getOneRedCover(getOrder.getCoverId()).getInviteLockNum() <= getOrder.getInviteLockNum())
                    getOrder.setIsReceive(true);
                jsonObject.put("orderData", getOrder);
            }
            jsonObject.put("code",50200);
        }else {
            jsonObject.put("code",50502);
            jsonObject.put("orderData", null);
        }
        jsonObject.put("message","获取订单成功");
        return ResponseEntity.ok(jsonObject);
    }

    @PostMapping("/updateInviteLockNum")
    public ResponseEntity<JSONObject> updateInviteLockNum(int orderId,String ticket,String timestamp){
        JSONObject jsonObject = new JSONObject();
        System.out.println("========================================================");
        System.out.println(orderId);
        System.out.println("========================================================");
        String md5 = md5Utils.getMD5(orderId + timestamp+ticket);
        Sign oneSignBySignTicket = signService.getOneSignBySignTicket(md5);
        if (oneSignBySignTicket == null){
            signService.createSignBySignTicket(new Sign().setSignTicket(md5).setUserTicket(ticket));
        }else if (oneSignBySignTicket.getSignCount() >= 2) {
            jsonObject.put("code",60001);
            jsonObject.put("message","助力失败");
            return ResponseEntity.ok(jsonObject);
        }else {
            signService.updateSignCount(oneSignBySignTicket.getId(),oneSignBySignTicket.setSignCount(oneSignBySignTicket.getSignCount()+1));
        }

        Order order = orderService.selOneOrder(orderId);
        RedCover oneRedCover = redCoverService.getOneRedCover(order.getCoverId());
        order.setInviteLockNum(order.getInviteLockNum()+1);

        if(oneRedCover.getInviteLockNum() <= order.getInviteLockNum())
            order.setIsReceive(true);
        if(orderService.updateOrderByInvite(order.getId(), order)){
            jsonObject.put("code",60200);
            jsonObject.put("message","助力成功");
        }
        return ResponseEntity.ok(jsonObject);
    }

    @PostMapping("/checkHasReceive")
    public ResponseEntity<JSONObject> checkHasReceive(int orderId , String ticket){
        JSONObject jsonObject = new JSONObject();
        Order order = orderService.selOneOrder(orderId);
        if (!order.getTicket().equals(ticket)){
            jsonObject.put("code",70400);
            jsonObject.put("message","订单或用户不存在");
        }else if (order.getHasReceive()){
            jsonObject.put("code",70500);
            jsonObject.put("message","订单已领取过");
        }else if (!order.getIsReceive()){
            jsonObject.put("code",70501);
            jsonObject.put("message","订单未完成任务");
        }else {
            orderService.updateOrderByInvite(orderId, order.setHasReceive(true));
            jsonObject.put("code",70202);
            jsonObject.put("message","成功领取");
        }
        jsonObject.put("data", order);
        return ResponseEntity.ok(jsonObject);
    }
}
