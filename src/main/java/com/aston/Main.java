package com.aston;

import com.aston.input.context.InputStudent;
import com.aston.models.Student;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        InputStudent service = new InputStudent();
        List<Student> students = service.input(System.in);
        students.forEach(System.out::println);
    }
}