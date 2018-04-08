package com.whyalwaysmea.account.service;

import com.whyalwaysmea.account.po.WechatUser;

/**
 * @Author: whyalwaysmea
 * @Date: Create in 2018/4/6 11:21
 * @Description:
 */
public interface UserService {

    /**
     * 获取微信用户信息
     * @param openid    微信openid
     * @return
     */
    WechatUser getWechatUser(String openid);

    /**
     * 根据token获取当前用户id
     * @return
     */
    String getCurrentUserId();

    /**
     * 根据token获取当前用户
     */
    WechatUser getCurrentUser();

    /**
     * 新增微信用户
     * @param wechatUser
     */
    void saveWechatUser(WechatUser wechatUser);
}
