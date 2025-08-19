package com.nhavronskyi.prostolearn.dto;

import com.nhavronskyi.prostolearn.model.Specification;

import java.util.List;

public class Teacher {
    String name;
    String surname;
    List<Specification> specifications;
    String description;
    List<Lesson> lessons;
}
