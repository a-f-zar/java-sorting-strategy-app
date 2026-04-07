package com.aston.input.context;

import com.aston.generation.RandomStudentGenerator;
import com.aston.input.context.strategy.InputStudentStrategy;
import com.aston.input.context.strategy.ManualInputStudent;
import com.aston.models.Student;


import java.io.File;
import java.io.InputStream;
import java.util.List;

public class InputStudent {

    private InputStudentStrategy strategy;

    /***
     * Set strategy for students input
     * @param strategy strategy that will be used
     */
    public void setFileStrategy(InputStudentStrategy strategy) {
        this.strategy = strategy;
    }

    /***
     * Choose correct strategy for input
     * @param in input stream for students
     * @param count count students, that we want input
     * @return list students that we input
     */
    public List<Student> input(InputStream in, int count) {
        if (in == System.in){
            return new ManualInputStudent().input(in, count);
        } else {
            return strategy.input(in, count);
        }
    }

    // Заглушка для файлового ввода, все стратегии на ввод из файла пишите сюда
    public List<Student> input(File file, int count){
        return null;
    }

    /***
     * Random generation students
     * @param count count students that will be generated
     * @return list random generated students
     */
    public List<Student> input(int count) {
        return new RandomStudentGenerator().generateStudents(count);
    }
}
