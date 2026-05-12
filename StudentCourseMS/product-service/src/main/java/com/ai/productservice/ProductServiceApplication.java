package com.ai.productservice;

import org.springframework.boot.SpringApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@MapperScan(basePackages="com.ai.productservice.mapper")
@SpringBootApplication(scanBasePackages = {"com.ai.productservice.controller"})
public class ProductServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }

}
