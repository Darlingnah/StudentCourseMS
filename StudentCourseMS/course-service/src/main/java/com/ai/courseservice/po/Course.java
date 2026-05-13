package com.ai.courseservice.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    Long courseId;
    String courseName;
    Double credit;
    String description;
    String department;
}
