package com.ai.courseclient.Client;

import com.ai.courseclient.model.Course;
import com.ai.productclient.model.ReturnData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="course-service")
public interface CourseServiceClient {

    @GetMapping("findCourseById/{courseId}")
    public ReturnData findCourseById(@PathVariable("courseId") Long courseId);


    @PostMapping("newCourse")
    public ReturnData newCourse(@RequestBody Course course);


    @PostMapping("/changeCourse")
    public ReturnData changeCourse(@RequestBody Course course);

    @GetMapping("deleteByCourseId/{courseId}")
    public ReturnData deleteByCourseId(@PathVariable("courseId") Long courseId);
}
