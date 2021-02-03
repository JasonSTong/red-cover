package cn.ha5fun.redcover.service;

import cn.ha5fun.redcover.mapper.FakeMessageMapper;
import cn.ha5fun.redcover.pojo.FakeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chen
 * @Company ha5fun.com
 * @Description fake
 * @Date 2021/2/3 11:28 下午
 * @Version 1.0.0
 */
@Service
public class FakeMessageService {
    @Autowired
    FakeMessageMapper fakeMessageMapper;

    public boolean addFakeMessage(String username ,String time){
        return 0 != fakeMessageMapper.insert(new FakeMessage().setTime(time).setUsername(username)  );
    }
    public List<FakeMessage> getAllFakeMessage(){
        return fakeMessageMapper.selectList(null);
    }
}
