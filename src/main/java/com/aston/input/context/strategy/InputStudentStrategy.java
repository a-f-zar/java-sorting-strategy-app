package com.aston.input.context.strategy;

import com.aston.models.Student;
import com.aston.models.custom.MyList;

import java.io.InputStream;
import java.util.Scanner;

public interface InputStudentStrategy {
    default MyList<Student> input(InputStream in, int count) {
        throw new UnsupportedOperationException("InputStream input is not supported");
    }

    default MyList<Student> input(Scanner scanner, int count) {
        throw new UnsupportedOperationException("Scanner input is not supported");
    }

    default MyList<Student> input(int count) {
        throw new UnsupportedOperationException("Count-based input is not supported");
    }
}
