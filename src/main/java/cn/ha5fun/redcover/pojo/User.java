package cn.ha5fun.redcover.pojo;

import lombok.Data;

/**
 * @author chen
 * @Company ha5fun.com
 * @Description 用户表
 * @Date 2021/1/27 3:50 下午
 * @Version 1.0.0
 */
@Data
public class User {
    private Integer id;
    private String nickname;

    private String ticket;
}
