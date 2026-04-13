package com.aston.input.context;

import com.aston.input.context.strategy.InputStudentStrategy;
import com.aston.models.Student;

import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

public class InputStudent {

    private InputStudentStrategy strategy;

    public void setStrategy(InputStudentStrategy strategy) {
        this.strategy = strategy;
    }

    public List<Student> input(InputStream in, int count) {
        if (strategy == null) {
            throw new IllegalStateException("Input strategy not set");
        }

        return strategy.input(in, count);
    }

    public List<Student> input(Scanner scanner, int count) {
        if (strategy == null) {
            throw new IllegalStateException("Input strategy not set");
        }

        return strategy.input(scanner, count);
    }

    public List<Student> input(int count) {
        if (strategy == null) {
            throw new IllegalStateException("Input strategy not set");
        }

        return strategy.input(count);
    }
}
