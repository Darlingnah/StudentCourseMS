package com.ai.courseclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = {"com.ai.courseclient.controller"})
@EnableEurekaClient
@EnableFeignClients("com.ai.courseclient.Client")
public class CourseClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(CourseClientApplication.class, args);
    }

}
