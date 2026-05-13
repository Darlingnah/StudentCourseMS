package com.ai.productservice.controller;

import com.ai.productservice.mapper.StudentMapper;
import com.ai.productservice.mapper.UserMapper;
import com.ai.productservice.po.ReturnData;
import com.ai.productservice.po.Student;
import com.ai.productservice.po.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Data
@Slf4j
@RestController
public class ProductController {

    @Autowired
    private final StudentMapper studentMapper;
    @Autowired
    private final UserMapper userMapper;

    @GetMapping("/findByStuId/{stuId}")
    public Student findByStuId(@PathVariable Long stuId)
    {
        Student stu  = studentMapper.findByStuId(stuId);
        log.info("-------------ok-----------  /findByStuId/{stuId}  -----------------");
        return stu;
    }

    @GetMapping("/newUserByStuId/{stuId}")
    public ReturnData newUserByStuId(@PathVariable Long stuId)
    {
        if(userMapper.findUserByStuId(stuId) == null && findByStuId(stuId) != null)
        {
            if(userMapper.newUser(stuId))
            {
                log.info("-------------ok-----------  /newUser  -----------------");
                return new ReturnData("创建成功",userMapper.findUserByStuId(stuId));
            }
            else
            {
                log.info("-------------fail-----------  newUser  -----------------");
                return new ReturnData("用户创建失败",null);
            }
        }
        else{
            return new ReturnData("用户已存在或学生信息未存入！",null);
        }
    }
    @PostMapping("/logAccount")
    public ReturnData logAccount(@RequestBody User uesr)
    {
        if(userMapper.findBySIAndPW(uesr) != null)
        {
            return new ReturnData("登陆成功!",null);
        }
        else{
            return new ReturnData("登陆失败!",null);
        }
    }

    @PostMapping("/changePassword")
    public ReturnData changePassword(@RequestBody User user){
        if(userMapper.findUserByStuId(user.getStuId()) != null)
        {
            if(userMapper.updatePassword(user))
            {
                return new ReturnData("修改成功！",null);
            }
            else
            {
                return new ReturnData("修改失败!",null);
            }
        }
        else {
            return new ReturnData("用户不存在！",null);
        }
    }

}
