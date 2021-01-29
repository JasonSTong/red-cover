package cn.ha5fun.redcover.service;

import cn.ha5fun.redcover.mapper.RedCoverMapper;
import cn.ha5fun.redcover.pojo.RedCover;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chen
 * @Company ha5fun.com
 * @Description 红包服务
 * @Date 2021/1/26 10:39 下午
 * @Version 1.0.0
 */
@Service
public class RedCoverService {
    @Autowired
    RedCoverMapper redCoverMapper;
    // 查询所有红包封面
    public List<RedCover> getRedCoverList(){

        return redCoverMapper.selectList(null);
    }
    // 查询一个红包封面
    public RedCover getOneRedCover(Integer id){
        return redCoverMapper.selectById(id);
    }
}
