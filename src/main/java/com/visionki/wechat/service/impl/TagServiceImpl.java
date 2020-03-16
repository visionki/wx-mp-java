package com.visionki.wechat.service.impl;

import com.visionki.wechat.constant.REnum;
import com.visionki.wechat.exceptions.BaseException;
import com.visionki.wechat.mapper.WechatTagMapper;
import com.visionki.wechat.mapper.WechatUserMapper;
import com.visionki.wechat.model.WechatTag;
import com.visionki.wechat.model.WechatUser;
import com.visionki.wechat.service.TagService;
import com.visionki.wechat.util.DateUtils;
import com.visionki.wechat.util.FileUtil;
import com.visionki.wechat.util.UUIDUtils;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpUserTagService;
import me.chanjar.weixin.mp.bean.result.WxMpQrCodeTicket;
import me.chanjar.weixin.mp.bean.tag.WxUserTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

/**
 * @Author: vision
 * @CreateDate: 2020/3/14 14:59
 * @Version: 1.0
 * @Copyright: Copyright (c) 2020
 * @Description:
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private WechatTagMapper wechatTagMapper;
    @Autowired
    private WxMpService wxMpService;
    @Autowired
    private WechatUserMapper wechatUserMapper;
    @Autowired
    private FileUtil fileUtil;

    @Override
    public List<WechatTag> getList() {
        List<WechatTag> wechatTags = wechatTagMapper.selectAll();
        return wechatTags;
    }

    @Override
    public int newTag(WechatTag wechatTag) throws WxErrorException {
        // 同步到微信公众号
        WxUserTag wxUserTag = wxMpService.getUserTagService().tagCreate(wechatTag.getTagName());
        wechatTag.setId(UUIDUtils.uuid());
        // 生成对应的带参数二维码(用的是微信给的id,方便关注的时候备注)
        WxMpQrCodeTicket ticket = wxMpService.getQrcodeService().qrCodeCreateLastTicket(wechatTag.getId());
        File file = wxMpService.getQrcodeService().qrCodePicture(ticket);
        System.out.println(file.getPath());
        // 组装保存信息
        wechatTag.setTagId(wxUserTag.getId());
        wechatTag.setCreateTime(DateUtils.getCurrentDateTime(DateUtils.DATE_TIME_FORMAT));
        wechatTag.setUpdateTime(DateUtils.getCurrentDateTime(DateUtils.DATE_TIME_FORMAT));
        wechatTag.setQrcodeUrl(fileUtil.upload(file));
        return wechatTagMapper.insert(wechatTag);
    }

    @Override
    public WechatTag getTag(String id) {
        return wechatTagMapper.selectByPrimaryKey(id);
    }

    @Override
    public WechatTag getTag(WechatTag wechatTag) {
        return wechatTagMapper.selectOne(wechatTag);
    }

    @Override
    public int deleteTag(String id) throws WxErrorException {
        // 获取标签信息
        WechatTag wechatTag = wechatTagMapper.selectByPrimaryKey(id);
        if (wechatTag == null){
            throw new BaseException(REnum.DATA_NOT_EXISTS);
        }
        wxMpService.getUserTagService().tagDelete(wechatTag.getTagId());
        // 删除粉丝标签
        wechatUserMapper.updateAllUserTag(wechatTag.getTagName(),"");
        return wechatTagMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateTag(WechatTag wechatTag) throws WxErrorException {
        // 获取数据库那份
        WechatTag data = wechatTagMapper.selectByPrimaryKey(wechatTag.getId());
        if (data == null){
            throw new BaseException(REnum.DATA_NOT_EXISTS);
        }
        // 更新标签，更新失败就告辞
        if (!wxMpService.getUserTagService().tagUpdate(data.getTagId(), wechatTag.getTagName())){
            throw new BaseException(REnum.ERROR);
        }
        // 更新粉丝标签
        wechatUserMapper.updateAllUserTag(data.getTagName(),wechatTag.getTagName());
        // 更新标签信息
        data.setTagName(wechatTag.getTagName());
        return wechatTagMapper.updateByPrimaryKey(data);
    }

}
