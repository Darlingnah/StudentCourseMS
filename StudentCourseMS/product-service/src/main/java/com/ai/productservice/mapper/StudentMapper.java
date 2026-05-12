package com.ai.productservice.mapper;


import com.ai.productservice.po.Student;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface StudentMapper {
    @Select("select * from student where stuId = #{stuId}")
    Student findByStuId(Long stuId);
}
