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
 * @Description 领取订单管理
 * @Date 2021/1/26 10:19 下午
 * @Version 1.0.0
 */
@Data
@TableName("order_")
@Accessors(chain = true)
public class Order implements Serializable {
    private static final long serialVersionUID = -703282590339275806L;
    // 订单ID
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    // 红包封面ID
    private Integer coverId;
    //用户名
    private String nickname;
    // 用户ID
    private String ticket;
    // 用户邀请好友次数
    private Integer inviteLockNum;
    // 用户观看视频次数
    private Integer lookVideoLockNum;
    // isFree
    private boolean isFree;
    // 用户是否有资格领取
    private Boolean isReceive;
}
