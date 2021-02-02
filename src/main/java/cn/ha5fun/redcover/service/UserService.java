package cn.ha5fun.redcover.service;

import cn.ha5fun.redcover.mapper.UserMapper;
import cn.ha5fun.redcover.pojo.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chen
 * @Company ha5fun.com
 * @Description 用户服务
 * @Date 2021/2/1 11:35 下午
 * @Version 1.0.0
 */
@Service
public class UserService {
    @Autowired
    UserMapper userMapper;
    // 根据ticket查找user
    public User selUserByTicket(String ticket){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ticket",ticket);
        List<User> users = userMapper.selectList(queryWrapper);
        if (users.size() != 0){
            for (User user : users) {
                return user;
            }
        }
        return null;
    }
    // 塞入一个user
    public boolean insertUser(User user){
        return userMapper.insert(user) != 0;
    }
}
