package com.aston.input.context.strategy;

import com.aston.models.Student;

import java.io.InputStream;
import java.util.List;

public interface InputStudentStrategy {
    List<Student> input(InputStream in, int count);
}
