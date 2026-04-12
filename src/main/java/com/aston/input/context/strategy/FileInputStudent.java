package com.aston.input.context.strategy;

import com.aston.input.StudentJsonFileLoader;
import com.aston.models.Student;

import java.io.InputStream;
import java.util.List;

public class FileInputStudent implements InputStudentStrategy {

    private final StudentJsonFileLoader studentJsonFileLoader = new StudentJsonFileLoader();

    @Override
    public List<Student> input(InputStream in, int count) {
        return studentJsonFileLoader.load(in);
    }
}
