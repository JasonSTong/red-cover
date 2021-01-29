package cn.ha5fun.redcover.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

/**
 * @author chen
 * @Company ha5fun.com
 * @Description MD5Utils
 * @Date 2021/1/28 11:32 下午
 * @Version 1.0.0
 */
@Component

public class MD5Utils {
    public String getMD5(String src){
        return DigestUtils.md5Hex(src);
    }
}
