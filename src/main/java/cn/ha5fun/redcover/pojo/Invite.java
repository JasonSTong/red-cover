package cn.ha5fun.redcover.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author chen
 * @Company ha5fun.com
 * @Description 邀请服务
 * @Date 2021/2/2 3:23 下午
 * @Version 1.0.0
 */
@Data
@Accessors( chain = true )
@TableName(value = "invite_")
public class Invite {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private Integer userId;
    private String ticket;
    private Integer invite_user_id;
    private String date;
}
