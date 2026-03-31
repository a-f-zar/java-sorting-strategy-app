package com.aston.models;
import java.util.Objects;

public class Student {
    private String name;
    private Double averageGrade;
    private Integer studentCardNumber;

    private Student(Builder builder) {
        this.name = builder.name;
        this.averageGrade = builder.averageGrade;
        this.studentCardNumber = builder.studentCardNumber;
    }

    public static class Builder {

        private String name;
        private Double averageGrade;
        private Integer studentCardNumber;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder averageGrade(Double averageGrade) {
            this.averageGrade = averageGrade;
            return this;
        }

        public Builder studentCardNumber(Integer studentCardNumber) {
            this.studentCardNumber = studentCardNumber;
            return this;
        }

        public Student build() {
            return new Student(this);
        }
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", averageGrade=" + averageGrade +
                ", studentCardNumber=" + studentCardNumber +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(name, student.name) &&
                Objects.equals(averageGrade, student.averageGrade) &&
                Objects.equals(studentCardNumber, student.studentCardNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, averageGrade, studentCardNumber);
    }
}
