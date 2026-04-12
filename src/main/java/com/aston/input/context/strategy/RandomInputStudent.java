package com.aston.input.context.strategy;

import com.aston.generation.RandomStudentGenerator;
import com.aston.models.Student;

import java.util.List;

public class RandomInputStudent implements InputStudentStrategy {

    private final RandomStudentGenerator randomStudentGenerator = new RandomStudentGenerator();

    @Override
    public List<Student> input(int count) {
        return randomStudentGenerator.generateStudents(count);
    }
}
