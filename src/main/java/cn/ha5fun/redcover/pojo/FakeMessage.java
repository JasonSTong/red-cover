package cn.ha5fun.redcover.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author chen
 * @Company ha5fun.com
 * @Description fake
 * @Date 2021/2/3 11:25 下午
 * @Version 1.0.0
 */
@Data
@Accessors( chain = true )
public class FakeMessage {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    private String username;

    private String time;
}
