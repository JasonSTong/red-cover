package cn.ha5fun.redcover.controller;

import cn.ha5fun.redcover.service.WeChatService;
import com.alibaba.fastjson.JSONObject;
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

    @PostMapping("/login")
    public ResponseEntity<JSONObject> userLogin(String code){

        return ResponseEntity.ok(weChatService.wxLogin(code)) ;
    }
}
