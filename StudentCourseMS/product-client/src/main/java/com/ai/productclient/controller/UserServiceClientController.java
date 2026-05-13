package com.ai.productclient.controller;

import com.ai.productclient.Client.ProductServiceClient;
import com.ai.productclient.Client.UserServiceClient;
import com.ai.productservice.po.ReturnData;
import com.ai.productservice.po.Student;
import com.ai.productservice.po.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@Slf4j
public class UserServiceClientController {

    @Autowired
    private final UserServiceClient userServiceClient;
    @GetMapping("/newUserByStuId/{stuId}")
    public ReturnData newUser(@PathVariable Long stuId) {
        ReturnData flag = userServiceClient.newUser(stuId);
        log.info("-------------In client    newUser---------------");
        return flag;
    }

    @PostMapping("/logAccount")
    public ReturnData logAccount(@RequestBody User user)
    {
        return userServiceClient.logAccount(user);
    }

    @PostMapping("/changePassword")
    public ReturnData changePassword(@RequestBody User user)
    {
        return userServiceClient.changePassword(user);
    }
}
