package com.aston.input.context.strategy;

import com.aston.generation.RandomStudentGenerator;
import com.aston.models.Student;
import com.aston.models.custom.MyList;


public class RandomInputStudent implements InputStudentStrategy {

    private final RandomStudentGenerator randomStudentGenerator = new RandomStudentGenerator();

    @Override
    public MyList<Student> input(int count) {
        return randomStudentGenerator.generateStudents(count);
    }
}