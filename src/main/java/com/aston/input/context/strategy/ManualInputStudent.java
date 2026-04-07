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

    @Override
    public List<Student> input(InputStream in, int count) {
        Scanner scanner = new Scanner(in);
        List<Student> students = new ArrayList<>();
        int i = 0;

        while (i < count) {

            students.add(
                    Student.builder()
                    .name(studentNameIn(scanner))
                    .averageGrade(studentGradeIn(scanner))
                    .studentCardNumber(studentCardIn(scanner))
                    .build()
            );

            i++;

        }
        return students;
    }

    private static String studentNameIn(Scanner scanner) {
        String name;
        while (true) {
            System.out.print("Name student: ");
            name = scanner.nextLine();
            try {
                validator.validateName(name);
            } catch (ValidationException e) {
                System.out.println(e.getMessage() + " Try again");
                continue;
            }
            break;
        }
        return name;
    }

    private static Double studentGradeIn(Scanner scanner) {
        Double averageGrade;

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
                System.out.println(e.toString() + " Try again");
                scanner.nextLine();
                continue;
            }
            break;
        }
        return averageGrade;
    }

    private static Integer studentCardIn(Scanner scanner) {
        Integer studentCardNumber;

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
                System.out.println(e.toString() + " Try again");
                scanner.nextLine();
                continue;
            }
            break;
        }
        return studentCardNumber;
    }

}
