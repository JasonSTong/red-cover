package cn.ha5fun.redcover.controller;

import cn.ha5fun.redcover.pojo.Invite;
import cn.ha5fun.redcover.pojo.Order;
import cn.ha5fun.redcover.service.InviteService;
import cn.ha5fun.redcover.service.OrderService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author chen
 * @Company ha5fun.com
 * @Description 邀请控制
 * @Date 2021/2/2 3:22 下午
 * @Version 1.0.0
 */
@Controller
@RequestMapping("/invite")
public class InviteController {
    @Autowired
    InviteService inviteService;
    @Autowired
    OrderService orderService;
    @PostMapping("/addInvite")
    public ResponseEntity<JSONObject> inviteUser(int userId,int inviteUserId, String ticket,int orderId){
        JSONObject jsonObject = new JSONObject();

        int isAdd = inviteService.addInvite(userId, inviteUserId, ticket);
        if (isAdd == 10200){
            Order order = orderService.selOneOrder(orderId);
            if (order!= null){
                orderService.updateOrderByInvite(orderId,order.setInviteLockNum(order.getInviteLockNum()+1));
            }
            jsonObject.put("code",200);
            jsonObject.put("message","助力成功");
        }else if(isAdd == 10411) {
            jsonObject.put("code",isAdd);
            jsonObject.put("message","您当日已为该好友助力过");
        }else if(isAdd == 10412) {
            jsonObject.put("code",isAdd);
            jsonObject.put("message","您今日已为好友助力两次,无法再次助力");
        }else if(isAdd == 10413) {
            jsonObject.put("code",isAdd);
            jsonObject.put("message","查找不到助力用户,请联系客服");
        }else if(isAdd == 10414) {
            jsonObject.put("code",isAdd);
            jsonObject.put("message","查找不到当前用户,请联系客服");
        }else if(isAdd == 10400) {
            jsonObject.put("code",isAdd);
            jsonObject.put("message","助力失败");
        }

        return ResponseEntity.ok(jsonObject);
    }
}
