package com.whyalwaysmea.account.service.impl;

import com.whyalwaysmea.account.mapper.WechatUserMapper;
import com.whyalwaysmea.account.parameters.WechatUserInfoParam;
import com.whyalwaysmea.account.po.WechatUser;
import com.whyalwaysmea.account.service.ExpenditureService;
import com.whyalwaysmea.account.service.UserService;
import com.whyalwaysmea.account.utils.UserUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @Author: whyalwaysmea
 * @Date: Create in 2018/4/6 11:22
 * @Description:
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private WechatUserMapper userMapper;

    @Autowired
    private ExpenditureService expenditureService;


    @Override
    public WechatUser getWechatUser(String openid) {
        WechatUser wechatUser = userMapper.selectByPrimaryKey(openid);
        return wechatUser;
    }

    @Override
    public String getCurrentUserId() {
        return UserUtils.getCurrentUserId();
    }

    @Override
    public WechatUser getCurrentUser() {
        String currentUserId = getCurrentUserId();
        WechatUser user = userMapper.selectByPrimaryKey(currentUserId);
        return user;
    }

    @Override
    @Transactional
    public WechatUser login(WechatUserInfoParam param) {
        WechatUser wechatUser = userMapper.selectByPrimaryKey(param.getOpenId());
        // 如果用户存在，则更新资料
        if(wechatUser != null) {
            BeanUtils.copyProperties(param, wechatUser);
            wechatUser.setWechatOpenid(param.getOpenId());
            wechatUser.setLastLoginTime(new Date());
            userMapper.updateByPrimaryKey(wechatUser);
            return wechatUser;
        }
        // 如果用户不存在，则新增，
        wechatUser = new WechatUser();
        BeanUtils.copyProperties(param, wechatUser);
        wechatUser.setWechatOpenid(param.getOpenId());
        userMapper.insertSelective(wechatUser);

        // 同步收支相关基础信息
        expenditureService.addDefaultExpenditureForNewUser(wechatUser.getWechatOpenid());

        return wechatUser;
    }


}
