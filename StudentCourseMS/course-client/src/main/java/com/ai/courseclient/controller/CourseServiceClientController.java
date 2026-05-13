package com.ai.courseclient.controller;


import com.ai.courseclient.Client.CourseServiceClient;
import com.ai.courseclient.model.Course;
import com.ai.productclient.model.ReturnData;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@Slf4j
public class CourseServiceClientController {

    @Autowired
    private final CourseServiceClient courseServiceClient;

    @GetMapping("findCourseById/{courseId}")
    public ReturnData findCourseById(@PathVariable Long courseId)
    {
        return courseServiceClient.findCourseById(courseId);
    }


    @PostMapping("newCourse")
    public ReturnData newCourse(@RequestBody Course course)
    {
        return courseServiceClient.newCourse(course);
    }


    @PostMapping("/changeCourse")
    public ReturnData changeCourse(@RequestBody Course course)
    {
        return courseServiceClient.changeCourse(course);
    }

    @GetMapping("deleteByCourseId/{courseId}")
    public ReturnData deleteByCourseId(@PathVariable("courseId") Long courseId)
    {
        return courseServiceClient.deleteByCourseId(courseId);
    }
}