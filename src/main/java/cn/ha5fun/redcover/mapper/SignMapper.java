package cn.ha5fun.redcover.mapper;

import cn.ha5fun.redcover.pojo.Sign;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author chen
 * @Company ha5fun.com
 * @Description 签名Mapper
 * @Date 2021/1/28 11:25 下午
 * @Version 1.0.0
 */
@Component(value = "signMapper")
public interface SignMapper extends BaseMapper<Sign> {
}
