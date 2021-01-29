package cn.ha5fun.redcover.controller;

import cn.ha5fun.redcover.pojo.RedCover;
import cn.ha5fun.redcover.service.RedCoverService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author chen
 * @Company ha5fun.com
 * @Description 主页面controller
 * @Date 2021/1/26 10:31 下午
 * @Version 1.0.0
 */
@Controller
@RequestMapping("/index")
public class IndexController {
    @Autowired
    RedCoverService redCoverService;
    @GetMapping("/allCover")
    public ResponseEntity<JSONObject> getRedCoverList(){
        JSONObject jsonObject = new JSONObject();
        List<RedCover> redCoverList = redCoverService.getRedCoverList();
        jsonObject.put("data",redCoverList);
        return ResponseEntity.ok(jsonObject);
    }
}
