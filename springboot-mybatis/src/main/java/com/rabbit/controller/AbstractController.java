package com.rabbit.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * controller公共组件
 * @Author chentao
 * Date 2019/2/21
 * Description
 **/
public class AbstractController {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private HttpServletRequest request;

    protected String getContextPath(){
        return request.getContextPath();
    }
}

