package com.aston.input.context.strategy;

import com.aston.models.Student;

import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

public interface InputStudentStrategy {
    default List<Student> input(InputStream in, int count) {
        throw new UnsupportedOperationException("InputStream input is not supported");
    }

    default List<Student> input(Scanner scanner, int count) {
        throw new UnsupportedOperationException("Scanner input is not supported");
    }

    default List<Student> input(int count) {
        throw new UnsupportedOperationException("Count-based input is not supported");
    }
}
