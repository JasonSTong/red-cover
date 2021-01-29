package cn.ha5fun.redcover.controller;

import cn.ha5fun.redcover.pojo.RedCover;
import cn.ha5fun.redcover.service.RedCoverService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author chen
 * @Company ha5fun.com
 * @Description 红包控制器
 * @Date 2021/1/27 6:54 下午
 * @Version 1.0.0
 */
@Controller
@RequestMapping("/redCover")
public class RedCoverController {
    @Autowired
    RedCoverService redCoverService;
    @GetMapping("/getOne")
    public ResponseEntity<JSONObject> getOneRedCover(int coverId){
        RedCover oneRedCover = redCoverService.getOneRedCover(coverId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("cover_data",oneRedCover);
        return ResponseEntity.ok(jsonObject);
    }
}
