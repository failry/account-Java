package com.whyalwaysmea.account.mq;

import lombok.Getter;

/**
 * @Author: Long
 * @Date: Create in 17:52 2018/6/25
 * @Description:  队列信息
 */
@Getter
public enum QueueEnum {
    /**
     * 用户注册
     */
    USER_REGISTER("user.register"),

    /**
     * 记账同步
     */
    RECORD("record.sync")
    ;

    /**
     * 队列名称
     */
    private String name;


    QueueEnum(String name) {
        this.name = name;
    }

}
