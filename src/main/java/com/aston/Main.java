package com.aston;

import com.aston.input.context.InputStudent;
import com.aston.models.Student;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        InputStudent service = new InputStudent();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите количкство студентов: ");
        int count = scanner.nextInt();
        scanner.nextLine();
        List<Student> students = service.input(System.in, count);
        students.forEach(System.out::println);
    }
}