package com.aston.input.context.strategy;

import com.aston.exception.ValidationException;
import com.aston.input.StudentInputParser;
import com.aston.models.Student;
import com.aston.validation.StudentValidator;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ManualInputStudent implements InputStudentStrategy {
    private static final StudentValidator validator = new StudentValidator();
    private static final StudentInputParser parser = new StudentInputParser();

    @Override
    public List<Student> input(InputStream in) {
        Scanner scanner = new Scanner(in);
        List<Student> students = new ArrayList<>();
        System.out.println("Введите студентов. Для окончания введите пустую строку для имени.");
        String name;
        Double averageGrade;
        Integer studentCardNumber;

        while (true) {


            while (true) {
                System.out.print("Name student: ");
                name = scanner.nextLine();
                if (name.isBlank()) break;
                try {
                    validator.validateName(name);
                } catch (ValidationException e) {
                    System.out.println(e.getMessage() + " Try again");
                    continue;
                }
                break;
            }

            if (name.isBlank()) break;

            while (true) {
                System.out.print("Averаge grade student: ");
                try {
                    averageGrade = scanner.nextDouble();
                    scanner.nextLine();
                    validator.validateAverageGrade(averageGrade);
                } catch (ValidationException e) {
                    System.out.println(e.getMessage() + " Try again");
                    scanner.nextLine();
                    continue;
                } catch (InputMismatchException e) {
                    System.out.println(e.getMessage());
                    scanner.nextLine();
                    continue;
                }
                break;
            }

            while (true) {
                System.out.print("Student card number: ");
                try {
                    studentCardNumber = scanner.nextInt();
                    scanner.nextLine();
                    validator.validateStudentCardNumber(studentCardNumber);
                } catch (ValidationException e) {
                    System.out.println(e.getMessage() + " Try again");
                    scanner.nextLine();
                    continue;
                } catch (InputMismatchException e) {
                    System.out.println(e.getMessage());
                    scanner.nextLine();
                    continue;
                }
                break;
            }

            students.add(
                    Student.builder()
                    .name(name)
                    .averageGrade(averageGrade)
                    .studentCardNumber(studentCardNumber)
                    .build()
            );

        }
        return students;
    }
}
