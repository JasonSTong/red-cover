package cn.ha5fun.redcover.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author chen
 * @Company ha5fun.com
 * @Description 红包封面配置
 * @Date 2021/1/26 10:10 下午
 * @Version 1.0.0
 */
@Data
@TableName(value = "red_cover")
public class RedCover implements Serializable {
    private static final long serialVersionUID = -2831789142241786607L;
    private Integer id;
    // 红包封面Url
    private String redCoverUrl;
    // 领取方式 信息
    private String getDesc;
    // 限制 分享好友数领取
    private Integer inviteLockNum;
    // 限制 观看视频 数量
    private Integer lookVideoLockNum;
    // 是否免费领取
    private Boolean isFree;
    // 红包封面数量
    private Integer count;
}
