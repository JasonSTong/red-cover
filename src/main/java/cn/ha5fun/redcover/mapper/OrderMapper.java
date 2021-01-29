package cn.ha5fun.redcover.mapper;

import cn.ha5fun.redcover.pojo.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author chen
 * @Company ha5fun.com
 * @Description 订单管理
 * @Date 2021/1/26 10:29 下午
 * @Version 1.0.0
 */
@Component(value = "orderMapper")
public interface OrderMapper extends BaseMapper<Order> {
}
