package com.fsproject.floatlyshare;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = {"com.fsproject.floatlyshare.dao"})
public class FloatlyshareApplication {

    public static void main(String[] args) {
        SpringApplication.run(FloatlyshareApplication.class, args);
    }

}
