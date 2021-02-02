package cn.ha5fun.redcover.service;

import cn.ha5fun.redcover.mapper.InviteMapper;
import cn.ha5fun.redcover.mapper.UserMapper;
import cn.ha5fun.redcover.pojo.Invite;
import cn.ha5fun.redcover.pojo.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author chen
 * @Company ha5fun.com
 * @Description
 * @Date 2021/2/2 3:26 下午
 * @Version 1.0.0
 */
@Service
public class InviteService {
    @Autowired
    InviteMapper inviteMapper;
    @Autowired
    UserMapper userMapper;
    // 判断邀请
    public int addInvite(int userId,int inviteUserId,String ticket){
        Date date = new Date();
        String getDate = date.getMonth() + 1 + "-" + date.getDay();
        QueryWrapper<Invite> inviteQueryWrapper = new QueryWrapper<>();
        inviteQueryWrapper.eq("date",getDate);
        inviteQueryWrapper.eq("invite_user_id",inviteUserId);
        // 查询该用户今日助力多少人
        List<Invite> invites = inviteMapper.selectList(inviteQueryWrapper);
        inviteQueryWrapper.eq("user_id", userId);
        // 查询该用户今日是否帮user助力过
        List<Invite> invitesThis = inviteMapper.selectList(inviteQueryWrapper);
        // 邀请人未登录 -> 被邀请人未登录 -> 被邀请人已经助力2次
        if (invitesThis.size() >= 1){
            return 10411;
        }
        if (invites.size() >= 2){
            return 10412;
        }
        if(userMapper.selectById(userId) == null )
            return 10413;
        if(userMapper.selectById(inviteUserId) == null)
            return 10414;

        if (inviteMapper.insert(new Invite().setInvite_user_id(inviteUserId).setUserId(userId).setTicket(ticket).setDate(getDate)) > 0){
            return 10200;
        }else {
            return 10400;
        }
    }
}
