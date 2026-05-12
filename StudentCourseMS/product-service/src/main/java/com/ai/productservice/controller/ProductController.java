package com.ai.productservice.controller;

import com.ai.productservice.mapper.StudentMapper;
import com.ai.productservice.po.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@Data
@Slf4j
@RestController
public class ProductController {

    @Autowired
    private final StudentMapper studentMapper;

    @GetMapping("/findByStuId/{stuId}")
    public Student findByStuId(@PathVariable Long stuId)
    {
        Student stu  = studentMapper.findByStuId(stuId);
        log.info("-------------ok-----------  /findByStuId/{stuId}  -----------------");
        return stu;
    }

}
