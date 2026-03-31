package com.aston.tests;

import com.aston.models.Student;

public class StudentTest {

    public static void main(String[] args) {
        testBuilder();
        testEqualsAndHashCode();
        testToString();
    }

    private static void testBuilder() {
        Student student = new Student.Builder()
                .name("Alexander")
                .averageGrade(4.5)
                .studentCardNumber(12345)
                .build();

        if (!"Alexander".equals(student.toString().contains("Alexander") ? "Alexander" : "")) {
            System.out.println("Builder test FAILED");
        } else {
            System.out.println("Builder test PASSED");
        }
    }

    private static void testEqualsAndHashCode() {
        Student s1 = new Student.Builder()
                .name("Alexander")
                .averageGrade(4.5)
                .studentCardNumber(12345)
                .build();

        Student s2 = new Student.Builder()
                .name("Alexander")
                .averageGrade(4.5)
                .studentCardNumber(12345)
                .build();

        Student s3 = new Student.Builder()
                .name("Roman")
                .averageGrade(3.0)
                .studentCardNumber(54321)
                .build();

        if (s1.equals(s2) && s1.hashCode() == s2.hashCode()) {
            System.out.println("Equals/HashCode test PASSED");
        } else {
            System.out.println("Equals/HashCode test FAILED");
        }

        if (!s1.equals(s3)) {
            System.out.println("Inequality test PASSED");
        } else {
            System.out.println("Inequality test FAILED");
        }
    }

    private static void testToString() {
        Student s = new Student.Builder()
                .name("Alexander")
                .averageGrade(4.5)
                .studentCardNumber(12345)
                .build();

        String str = s.toString();
        if (str.contains("Alexander") && str.contains("4.5") && str.contains("12345")) {
            System.out.println("toString test PASSED");
        } else {
            System.out.println("toString test FAILED");
        }
    }
}
