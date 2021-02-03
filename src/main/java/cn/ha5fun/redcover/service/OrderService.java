package cn.ha5fun.redcover.service;

import cn.ha5fun.redcover.mapper.OrderMapper;
import cn.ha5fun.redcover.pojo.Order;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chen
 * @Company ha5fun.com
 * @Description 订单服务
 * @Date 2021/1/26 10:43 下午
 * @Version 1.0.0
 */
@Service
public class OrderService {
    @Autowired
    OrderMapper orderMapper;
    // 创建订单
    public boolean createOrder(Order order){
        return orderMapper.insert(order) > 0;
    }
    // 根据要求查询订单
    public List<Order> selOrderByTicket(int coverId, String ticket,String nickname){
        QueryWrapper<Order> orderQueryWrapper = new QueryWrapper<>();
        orderQueryWrapper.eq("ticket",ticket);
        orderQueryWrapper.eq("cover_id",coverId);
        orderQueryWrapper.eq("nickname",nickname);
        return orderMapper.selectList(orderQueryWrapper);
    }
    // 根据ID 查询订单
    public Order selOneOrder(int orderId){
        return orderMapper.selectById(orderId);
    }
    // 修改订单
    public boolean updateOrderByInvite(int id, Order order){
        UpdateWrapper<Order> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",id);
        return orderMapper.update(order,updateWrapper)>0;
    }

    // 修改订单
    public boolean updateOrderByLookVideo(int id ,Order order){
        UpdateWrapper<Order> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",id);
        return orderMapper.update(order,updateWrapper)>0;
    }
    // 更改是否可以领取条件
    public boolean updateReceive(Order order){
        UpdateWrapper<Order> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("is_receive",true);
        return orderMapper.update(order,updateWrapper)>0;
    }
}
