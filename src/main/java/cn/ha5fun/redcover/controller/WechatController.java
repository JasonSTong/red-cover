package cn.ha5fun.redcover.controller;

import cn.ha5fun.redcover.pojo.User;
import cn.ha5fun.redcover.service.UserService;
import cn.ha5fun.redcover.service.WeChatService;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @author chen
 * @Company ha5fun.com
 * @Description 微信Controller
 * @Date 2021/1/27 5:17 下午
 * @Version 1.0.0
 */
@Controller
@RequestMapping("/wechat")
public class WechatController {
    @Autowired
    WeChatService weChatService;
    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity<JSONObject> userLogin(String code, String user){
        // 从wx拿来的json -> JsonObject -> entity
        User getUser = JSONObject.parseObject(user).toJavaObject(User.class);
        System.out.println(getUser);
        // 获取ticket
        String ticket = weChatService.wxLogin(code);
        JSONObject jsonObject = new JSONObject();
        // 判断是否拿到ticket
        if (!StringUtils.contains(ticket,"error")) {
            // 查询是否存在 user -> 如果存在user 直接返回查到的User
            User selUserByTicket = userService.selUserByTicket(ticket);
            if (null == selUserByTicket){
                getUser.setTicket(ticket);
                System.out.println(getUser);
                userService.insertUser(getUser);
                jsonObject.put("data",getUser);
                jsonObject.put("code",200);
            } else {
                jsonObject.put("data",selUserByTicket);
                jsonObject.put("message","用户已存在");
                jsonObject.put("code",100200);
            }
        // 如果没有从wx获取到ticket -> 返回错误码 10500
        }else {
            jsonObject.put("message","登录失败");
            jsonObject.put("code",10500);
        }

        return ResponseEntity.ok(jsonObject) ;
    }
}
