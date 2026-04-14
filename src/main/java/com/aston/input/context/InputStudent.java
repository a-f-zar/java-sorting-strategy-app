package com.aston.input.context;

import com.aston.input.context.strategy.InputStudentStrategy;
import com.aston.models.Student;
import com.aston.models.custom.MyList;

import java.io.InputStream;
import java.util.Scanner;

public class InputStudent {

    private InputStudentStrategy strategy;

    public void setStrategy(InputStudentStrategy strategy) {
        this.strategy = strategy;
    }

    public MyList<Student> input(InputStream in, int count) {
        if (strategy == null) {
            throw new IllegalStateException("Input strategy not set");
        }

        return strategy.input(in, count);
    }

    public MyList<Student> input(Scanner scanner, int count) {
        if (strategy == null) {
            throw new IllegalStateException("Input strategy not set");
        }

        return strategy.input(scanner, count);
    }

    public MyList<Student> input(int count) {
        if (strategy == null) {
            throw new IllegalStateException("Input strategy not set");
        }

        return strategy.input(count);
    }
}
