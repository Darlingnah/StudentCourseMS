package com.ai.courseservice.controller;

import com.ai.courseservice.mapper.CourseMapper;
import com.ai.courseservice.po.Course;
import com.ai.productservice.po.ReturnData;
import freemarker.core.ReturnInstruction;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Data
@Slf4j
@RestController
public class CourseServiceController {
    @Autowired
    private final CourseMapper courseMapper;

    @GetMapping("findCourseById/{courseId}")
    public ReturnData findCourseById(@PathVariable("courseId") Long courseId)
    {
        Course course;
        if((course = courseMapper.findCourseById(courseId)) !=null)
        {
            return new ReturnData("查询成功！",course);
        }
        else
        {
            return new ReturnData("未查询到课程!",null);
        }
    }

    @PostMapping("newCourse")
    public ReturnData newCourse(@RequestBody Course course)
    {
        if(courseMapper.newCourse(course))
        {
            return new ReturnData("添加成功",course);
        }
        else {
            return new ReturnData("添加失败",course);        }
    }

    @PostMapping("/changeCourse")
    public ReturnData changeCourse(@RequestBody Course course)
    {
        if(courseMapper.findCourseById(course.getCourseId()) != null)
        {
            if(courseMapper.updateCourse(course))
            {
                return new ReturnData("修改成功",course);
            }
            else {
                return new ReturnData("修改失败",course);
            }
        }
        else {
            return new ReturnData("未查询到该课程！",course);
        }
    }

    @GetMapping("/deleteByCourseId/{courseId}")
    public ReturnData deleteByCourseId(@PathVariable("courseId") Long courseId)
    {
        if(courseMapper.findCourseById(courseId) != null)
        {
            if(courseMapper.deleteCourseById(courseId))
            {
                return new ReturnData("删除成功",null);
            }
            else {
                return new ReturnData("删除失败",null);
            }
        }
        else {
            return new ReturnData("未查询到该课程！",null);
        }
    }

}
