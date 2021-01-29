package cn.ha5fun.redcover.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author chen
 * @Company ha5fun.com
 * @Description 签名
 * @Date 2021/1/28 11:25 下午
 * @Version 1.0.0
 */
@Data
@TableName("sign_")
@Accessors(chain = true)
public class Sign implements Serializable {
    private static final long serialVersionUID = -7574644166198179272L;
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String userTicket;
    private String signTicket;
    private int signCount;
}
