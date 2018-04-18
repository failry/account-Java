package com.whyalwaysmea.account.enums;

import lombok.Getter;

/**
 * @Author: whyalwaysmea
 * @Date: Create in 2018/4/14 11:16
 * @Description:
 */
@Getter
public enum AccountBookError implements CodeEnum {
    // 添加账本错粗
    ERROR_INSERT(2000, "添加错误，请重试"),
    // 修改账本预算时，剩余预算不能超过总的预算
    ERROR_BUDGETARYAMOUNT(2001, "剩余预算不能超过总预算"),
    // 根据id没有找到有效的账本
    ERROR_ACCOUNTBOOK(2001, "错误的账本信息");

    private Integer code;
    private String message;

    AccountBookError(int code, String message) {
        this.code = code;
        this.message = message;
    }
}