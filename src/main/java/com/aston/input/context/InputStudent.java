package com.aston.input.context;

import com.aston.input.context.strategy.InputStudentStrategy;
import com.aston.input.context.strategy.ManualInputStudent;
import com.aston.models.Student;
import com.aston.sorting.context.strategy.SortingStrategy;

import java.io.File;
import java.io.InputStream;
import java.util.List;

public class InputStudent {

    private InputStudentStrategy fileStrategy;

    public void setFileStrategy(InputStudentStrategy strategy) {
        this.fileStrategy = strategy;
    }

    public List<Student> input(InputStream in) {
        if (in == System.in){
            return new ManualInputStudent().input(in);
        } else {
            return fileStrategy.input(in);
        }
    }

    // Заглушка для файлового ввода, все стратегии на ввод из файла пишите сюда
    public List<Student> input(File file){
        return null;
    }
}
