package com.aston.input.context.strategy;

import com.aston.exception.ValidationException;
import com.aston.models.Student;
import com.aston.models.custom.CustomArrayList;
import com.aston.models.custom.MyList;
import com.aston.validation.StudentValidator;

import java.io.InputStream;
import java.util.Scanner;

public class ManualInputStudent implements InputStudentStrategy {
    private static final StudentValidator validator = new StudentValidator();

    @Override
    public MyList<Student> input(InputStream in, int count) {
        Scanner scanner = new Scanner(in);
        return input(scanner, count);
    }

    @Override
    public MyList<Student> input(Scanner scanner, int count) {
        MyList<Student> students = new CustomArrayList<>();

        for (int i = 0; i < count; i++) {
            students.add(
                    Student.builder()
                            .name(studentNameIn(scanner))
                            .averageGrade(studentGradeIn(scanner))
                            .studentCardNumber(studentCardIn(scanner))
                            .build()
            );
        }

        return students;
    }

    private static String studentNameIn(Scanner scanner) {
        while (true) {
            System.out.print("Name student: ");
            String name = scanner.nextLine();

            try {
                validator.validateName(name);
                return name;
            } catch (ValidationException e) {
                System.out.println(e.getMessage() + " Try again");
            }
        }
    }

    private static Double studentGradeIn(Scanner scanner) {
        while (true) {
            System.out.print("Average grade student: ");
            String input = scanner.nextLine().trim().replace(',', '.');

            try {
                Double averageGrade = Double.parseDouble(input);
                validator.validateAverageGrade(averageGrade);
                return averageGrade;
            } catch (NumberFormatException e) {
                System.out.println("Invalid average grade. Try again");
            } catch (ValidationException e) {
                System.out.println(e.getMessage() + " Try again");
            }
        }
    }

    private static Integer studentCardIn(Scanner scanner) {
        while (true) {
            System.out.print("Student card number: ");
            String input = scanner.nextLine().trim();

            try {
                Integer studentCardNumber = Integer.parseInt(input);
                validator.validateStudentCardNumber(studentCardNumber);
                return studentCardNumber;
            } catch (NumberFormatException e) {
                System.out.println("Invalid student card number. Try again");
            } catch (ValidationException e) {
                System.out.println(e.getMessage() + " Try again");
            }
        }
    }
}
