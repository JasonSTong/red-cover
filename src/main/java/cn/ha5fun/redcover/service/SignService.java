package cn.ha5fun.redcover.service;

import cn.ha5fun.redcover.mapper.SignMapper;
import cn.ha5fun.redcover.pojo.Sign;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author chen
 * @Company ha5fun.com
 * @Description 签名
 * @Date 2021/1/28 11:24 下午
 * @Version 1.0.0
 */
@Service
public class SignService {
    @Autowired
    SignMapper signMapper;
    // 创建一个签名
    public boolean createSignBySignTicket(Sign sign){

        return signMapper.insert(sign) > 0;
    }
    // 查询一个签名
    public Sign getOneSignBySignTicket(String signTicket){
        QueryWrapper<Sign> signQueryWrapper = new QueryWrapper<>();
        signQueryWrapper.eq("sign_Ticket",signTicket);
        return  signMapper.selectOne(signQueryWrapper);
    }
    // 给签名次数加1
    public boolean updateSignCount(int id,Sign sign){
        UpdateWrapper<Sign> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",id);
        return signMapper.update(sign,updateWrapper) > 0;
    }
}
