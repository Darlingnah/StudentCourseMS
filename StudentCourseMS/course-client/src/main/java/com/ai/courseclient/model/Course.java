package com.ai.courseclient.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
