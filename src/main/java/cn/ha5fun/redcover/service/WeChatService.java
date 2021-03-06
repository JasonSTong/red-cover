package cn.ha5fun.redcover.service;


import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


/**
 * @author chen
 * @Company ha5fun.club
 */
@Service
public class WeChatService {

    @Autowired
    RestTemplate restTemplate;


    /**
     * 用户code 校验
     * @param code
     * @return 返回状态码
     */
    public String wxLogin(String code){
        String ticket = "";
        String appId = "wx9a886bd40e8fb753";
        String secret = "0aa9f444dedb0c52cc4e0d7a70c428be";
        String url = "https://api.weixin.qq.com/sns/jscode2session?"
                +"appid="+appId
                +"&secret=" +secret
                +"&js_code="+code
                +"&grant_type=authorization_code";
        //http请求
        String jsonData =this.restTemplate.getForObject(url, String.class);
        //判断返回的json字符串是否有errorcode
        System.out.println(jsonData);
        if(StringUtils.contains(jsonData,"errcode")){

            return "error";
        }
        // 转为Object
        JSONObject jsonDataObject = JSONObject.parseObject(jsonData);
        String md5Key = DigestUtils.md5Hex(jsonDataObject.get("openid")+"EASY_WORK_LOGIN");

        return "ha5fun_"+md5Key;
    }




}
