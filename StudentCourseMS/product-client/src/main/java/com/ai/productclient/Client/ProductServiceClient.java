package com.ai.productclient.Client;

import com.ai.productservice.po.Student;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="student-service")
public interface ProductServiceClient {

    @GetMapping("/findByStuId/{stuId}")
    Student findByStuId(@RequestParam(value="stuId") Long stuId);
}
