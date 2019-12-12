package com.rabbit.verification.test;

import com.rabbit.verification.annotation.ParamCheck;
import com.rabbit.verification.constant.FormatConstant;
import com.rabbit.verification.model.BaseModel;

/**
 * @Author chentao
 * Date 2019/12/12
 * Description
 **/
@SuppressWarnings("serial")
public class UserInfo extends BaseModel {

    @ParamCheck(format=FormatConstant.USER_NAME)
    private String userName;
    @ParamCheck(format=FormatConstant.USER_PWD)
    private String password;
    @ParamCheck(format=FormatConstant.MOBILE, allowNull=true,orNulls="email")
    private String mobile;
    @ParamCheck(format=FormatConstant.EMAIL, allowNull=true,orNulls="mobile")
    private String email;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}

