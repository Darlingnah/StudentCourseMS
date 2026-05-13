package com.ai.productclient.controller;

import com.ai.productclient.Client.ProductServiceClient;
import com.ai.productservice.po.Student;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@Slf4j
public class ProductServiceClientController {
    @Autowired
    private final ProductServiceClient prodServiceClient;

    @GetMapping("/findByStuId/{stuId}")
    public Student findByStuId(@PathVariable Long stuId) {
        Student product = prodServiceClient.findByStuId(stuId);
        log.info("-------------In client    findByProductId---------------");
        return product;
    }

}


