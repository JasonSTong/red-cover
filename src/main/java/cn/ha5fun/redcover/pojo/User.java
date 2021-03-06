package cn.ha5fun.redcover.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author chen
 * @Company ha5fun.com
 * @Description 用户表
 * @Date 2021/1/27 3:50 下午
 * @Version 1.0.0
 */
@Data
@TableName("user_")
@Accessors( chain = true )
public class User {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String nickname;
    private String avatarUrl;
    private String city;
    private String country;
    private Integer gender;
    private String language;
    private String province;
    private String ticket;
}