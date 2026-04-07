package com.aston.input.context;

import com.aston.input.context.strategy.InputStudentStrategy;
import com.aston.input.context.strategy.ManualInputStudent;
import com.aston.models.Student;


import java.io.File;
import java.io.InputStream;
import java.util.List;

public class InputStudent {

    private InputStudentStrategy fileStrategy;

    public void setFileStrategy(InputStudentStrategy strategy) {
        this.fileStrategy = strategy;
    }

    public List<Student> input(InputStream in, int count) {
        if (in == System.in){
            return new ManualInputStudent().input(in, count);
        } else {
            return fileStrategy.input(in, count);
        }
    }

    // Заглушка для файлового ввода, все стратегии на ввод из файла пишите сюда
    public List<Student> input(File file, int count){
        return null;
    }
}
