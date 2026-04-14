package com.aston.input.context.strategy;

import com.aston.input.StudentJsonFileLoader;
import com.aston.models.Student;
import com.aston.models.custom.MyList;

import java.io.InputStream;

public class FileInputStudent implements InputStudentStrategy {

    private final StudentJsonFileLoader studentJsonFileLoader = new StudentJsonFileLoader();

    @Override
    public MyList<Student> input(InputStream in, int count) {
        return studentJsonFileLoader.load(in);
    }
}