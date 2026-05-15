package com.ai.courseservice.mapper;

import com.ai.courseservice.po.Course;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface CourseMapper {

    @Select("select * from course where courseId = #{courseId}")
    Course findCourseById(Long courseId);

    @Insert("INSERT INTO course (courseName, credit, description, department) VALUES (#{courseName}, #{credit}, #{description}, #{department})")
    boolean newCourse(Course course);

    @Update("update course set courseName = #{courseName}, credit = #{credit}, description = #{description}, department = #{department}  where courseId = #{courseId}")
    boolean updateCourse(Course course);

    @Delete("delete from course where courseId = #{courseId}")
    boolean deleteCourseById(Long courseId);

}
