package com.rabbit.verification.test;

import com.rabbit.verification.process.ParamVerficationProcess;

import java.util.Date;

/**
 * @Author chentao
 * Date 2019/12/12
 * Description
 **/
public class Test {

    public static void main(String[] args) {

        OrderInfo order=new OrderInfo();
        order.setDate(new Date());
        order.setOrderId("20180531122355211");

        UserInfo userInfo=new UserInfo();
//        userInfo.setEmail("6445566809823");
        userInfo.setMobile("15812341234");
        userInfo.setPassword("abc123456");
        userInfo.setUserName("Rabbit");
        order.setUserInfo(userInfo);

        ParamVerficationProcess.checkPara(order);
    }
}

