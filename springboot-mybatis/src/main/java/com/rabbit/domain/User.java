package com.rabbit.domain;

import com.rabbit.util.BaseEntity;

import javax.persistence.Table;

/**
 * @Author chentao
 * Date 2019/2/21
 * Description
 **/
@Table(name = "user")
public class User extends BaseEntity {


    private String name;

    private String passwrod;


    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * @return passwrod
     */
    public String getPasswrod() {
        return passwrod;
    }

    /**
     * @param passwrod
     */
    public void setPasswrod(String passwrod) {
        this.passwrod = passwrod == null ? null : passwrod.trim();
    }
}


