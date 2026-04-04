package com.aston.models.comparator;

import com.aston.exception.FieldException;
import com.aston.models.Student;

import java.util.Comparator;

public class StudentComparator {

    public enum By {
        NAME,
        AVERAGE_GRADE,
        STUDENT_CARD_NUMBER
    }

    public static Comparator<Student> compare(By field) {
        switch (field) {
            case NAME:
                return Comparator.comparing(Student::getName);
            case AVERAGE_GRADE:
                return Comparator.comparing(Student::getAverageGrade);
            case STUDENT_CARD_NUMBER:
                return Comparator.comparing(Student::getStudentCardNumber);
            default:
                throw new FieldException("The sorting field is not set");

        }
    }
}
