package com.rabbit;

import com.rabbit.util.MyMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
//启注册事务管理，等同于xml配置方式的 <tx:annotation-driven
@MapperScan(basePackages = "com.rabbit.dao", markerInterface = MyMapper.class)
@EnableTransactionManagement
public class MybatisApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(MybatisApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(MybatisApplication.class, args);
    }

}
