package com.ai.productclient.Client;


import com.ai.productclient.model.ReturnData;
import com.ai.productclient.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="student-service")
public interface UserServiceClient {
    @GetMapping("/newUserByStuId/{stuId}")
    ReturnData newUser(@RequestParam(value="stuId") Long stuId);

    @PostMapping("/logAccount")
    public ReturnData logAccount(@RequestBody User user);

    @PostMapping("/changePassword")
    public ReturnData changePassword(@RequestBody User user);
}
