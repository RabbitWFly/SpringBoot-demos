package com.rabbit.verification.test;

import com.rabbit.verification.annotation.ParamCheck;
import com.rabbit.verification.constant.FormatConstant;
import com.rabbit.verification.model.BaseModel;

import java.util.Date;


/**
 * @Author chentao
 * Date 2019/12/12
 * Description
 **/
@SuppressWarnings("serial")
public class OrderInfo extends BaseModel {
    @ParamCheck
    private UserInfo userInfo;

    @ParamCheck(format = FormatConstant.POSITIVE_NUMBER)
    private String orderId;

    @ParamCheck
    private Date date;

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}

