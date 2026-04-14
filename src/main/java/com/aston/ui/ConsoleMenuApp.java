package com.aston.ui;

import com.aston.concurrent.StudentOccurrenceCounter;
import com.aston.input.StudentInputParser;
import com.aston.input.context.InputStudent;
import com.aston.input.context.strategy.FileInputStudent;
import com.aston.input.context.strategy.ManualInputStudent;
import com.aston.input.context.strategy.RandomInputStudent;
import com.aston.models.Student;
import com.aston.models.comparator.StudentComparator;
import com.aston.models.custom.MyList;
import com.aston.output.StudentFileWriter;
import com.aston.sorting.context.Sorter;
import com.aston.sorting.context.strategy.BubbleSortStrategy;
import com.aston.sorting.context.strategy.InsertionSortStrategy;
import com.aston.sorting.context.strategy.SelectionSortStrategy;
import com.aston.sorting.context.strategy.SortingEvenNumbersStrategy;
import com.aston.sorting.context.strategy.SortingStrategy;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.Scanner;

public class ConsoleMenuApp {

    private static final int MAIN_MENU_MIN = 0;
    private static final int MAIN_MENU_MAX = 3;
    private static final int YES_NO_MIN = 1;
    private static final int YES_NO_MAX = 2;
    private static final int SORT_FIELD_MIN = 1;
    private static final int SORT_FIELD_MAX = 3;
    private static final int SORT_TYPE_MIN = 1;
    private static final int SORT_TYPE_MAX = 3;
    private static final String DEFAULT_OUTPUT_FILE = "results.txt";

    private final Scanner scanner = new Scanner(System.in);
    private final InputStudent inputStudent = new InputStudent();
    private final StudentInputParser studentInputParser = new StudentInputParser();
    private final StudentOccurrenceCounter studentOccurrenceCounter = new StudentOccurrenceCounter();
    private final StudentFileWriter studentFileWriter = new StudentFileWriter();

    private enum SortType {
        BUBBLE,
        INSERTION,
        SELECTION
    }

    public void run() {
        boolean running = true;

        while (running) {
            printMainMenu();
            int action = readMenuChoice(MAIN_MENU_MIN, MAIN_MENU_MAX);

            switch (action) {
                case 1 -> handleManualInput();
                case 2 -> handleRandomInput();
                case 3 -> handleFileInput();
                case 0 -> {
                    System.out.println("Application finished.");
                    running = false;
                }
                default -> System.out.println("Unknown menu item. Try again.");
            }
        }
    }

    private void handleManualInput() {
        int count = readStudentCount();
        inputStudent.setStrategy(new ManualInputStudent());
        MyList<Student> students = inputStudent.input(scanner, count);
        processStudents(students);
    }

    private void handleRandomInput() {
        int count = readStudentCount();
        inputStudent.setStrategy(new RandomInputStudent());
        MyList<Student> students = inputStudent.input(count);
        processStudents(students);
    }

    private void handleFileInput() {
        while (true) {
            System.out.print("Enter JSON file path: ");
            String pathText = scanner.nextLine().trim();

            try {
                inputStudent.setStrategy(new FileInputStudent());
                MyList<Student> students = loadStudentsFromFile(pathText);
                processStudents(students);
                return;
            } catch (InvalidPathException e) {
                System.out.println("Invalid file path. Try again.");
            } catch (IOException e) {
                System.out.println("Failed to read file: " + e.getMessage());
                System.out.println("Try again.");
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
                System.out.println("Try again.");
            }
        }
    }

    private void processStudents(MyList<Student> students) {
        if (students.isEmpty()) {
            System.out.println("Student list is empty.");
            return;
        }

        System.out.println();
        System.out.println("Source collection:");
        printStudents(students);

        SortType sortType = readSortType();
        StudentComparator.By sortField = readSortField();
        MyList<Student> sortedStudents = sortStudents(students, sortType, sortField);

        System.out.println();
        System.out.println("Sorted collection:");
        printStudents(sortedStudents);
        OccurrenceResult occurrenceResult = handleOccurrencesCount(sortedStudents);
        handleSaveResult(sortedStudents, occurrenceResult);
        System.out.println();
    }

    private MyList<Student> sortStudents(MyList<Student> students, SortType sortType, StudentComparator.By sortField) {
        Sorter<Student> sorter = new Sorter<>();
        sorter.setStrategy(buildSortingStrategy(sortType, sortField));
        return sorter.sort(students, StudentComparator.compare(sortField));
    }

    private SortingStrategy<Student> buildSortingStrategy(SortType sortType, StudentComparator.By sortField) {
        SortingStrategy<Student> baseStrategy = switch (sortType) {
            case BUBBLE -> new BubbleSortStrategy<>();
            case INSERTION -> new InsertionSortStrategy<>();
            case SELECTION -> new SelectionSortStrategy<>();
        };

        if (sortField == StudentComparator.By.STUDENT_CARD_NUMBER && readNeedEvenOnlySort()) {
            return new SortingEvenNumbersStrategy<>(
                    baseStrategy,
                    Student::getStudentCardNumber
            );
        }

        return baseStrategy;
    }

    private SortType readSortType() {
        while (true) {
            System.out.println();
            System.out.println("Select sort type:");
            System.out.println("1. Bubble sort");
            System.out.println("2. Insertion sort");
            System.out.println("3. Selection sort");

            int choice = readMenuChoice(SORT_TYPE_MIN, SORT_TYPE_MAX);

            switch (choice) {
                case 1:
                    return SortType.BUBBLE;
                case 2:
                    return SortType.INSERTION;
                case 3:
                    return SortType.SELECTION;
                default:
                    throw new IllegalStateException("Unexpected sort type");
            }
        }
    }

    private boolean readNeedEvenOnlySort() {
        System.out.println();
        System.out.println("Select card number sort mode:");
        System.out.println("1. Standard sort");
        System.out.println("2. Sort only students with even card number");
        return readMenuChoice(YES_NO_MIN, YES_NO_MAX) == 2;
    }

    private StudentComparator.By readSortField() {
        System.out.println();
        System.out.println("Select sort field:");
        System.out.println("1. Name");
        System.out.println("2. Average grade");
        System.out.println("3. Student card number");

        int choice = readMenuChoice(SORT_FIELD_MIN, SORT_FIELD_MAX);

        return switch (choice) {
            case 1 -> StudentComparator.By.NAME;
            case 2 -> StudentComparator.By.AVERAGE_GRADE;
            case 3 -> StudentComparator.By.STUDENT_CARD_NUMBER;
            default -> throw new IllegalStateException("Unexpected sort field");
        };
    }

    private int readStudentCount() {
        while (true) {
            System.out.print("Enter number of students: ");
            String value = scanner.nextLine().trim();

            try {
                int count = Integer.parseInt(value);

                if (count <= 0) {
                    System.out.println("Student count must be positive.");
                    continue;
                }

                return count;
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Try again.");
            }
        }
    }

    private int readMenuChoice(int min, int max) {
        while (true) {
            System.out.print("Choose menu item: ");
            String value = scanner.nextLine().trim();

            try {
                int choice = Integer.parseInt(value);

                if (choice < min || choice > max) {
                    System.out.println("Choice is out of range. Try again.");
                    continue;
                }

                return choice;
            } catch (NumberFormatException e) {
                System.out.println("Invalid menu item. Try again.");
            }
        }
    }

    private void printMainMenu() {
        System.out.println("=== Student Sorting App ===");
        System.out.println("1. Manual input");
        System.out.println("2. Random input");
        System.out.println("3. Load from JSON file");
        System.out.println("0. Exit");
    }

    private OccurrenceResult handleOccurrencesCount(MyList<Student> students) {
        System.out.println();
        System.out.println("Do you want to count occurrences of a student?");
        System.out.println("1. Yes");
        System.out.println("2. No");

        if (readMenuChoice(YES_NO_MIN, YES_NO_MAX) != 1) {
            return null;
        }

        Student targetStudent = readStudentForCount();
        int occurrences = studentOccurrenceCounter.countOccurrences(students, targetStudent);
        System.out.println("Occurrences found: " + occurrences);
        return new OccurrenceResult(targetStudent, occurrences);
    }

    private Student readStudentForCount() {
        while (true) {
            try {
                System.out.print("Enter student name: ");
                String name = scanner.nextLine();
                System.out.print("Enter average grade: ");
                String averageGrade = scanner.nextLine();
                System.out.print("Enter student card number: ");
                String studentCardNumber = scanner.nextLine();

                return studentInputParser.parseInput(name, averageGrade, studentCardNumber);
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
                System.out.println("Try again.");
            }
        }
    }

    private void handleSaveResult(MyList<Student> sortedStudents, OccurrenceResult occurrenceResult) {
        System.out.println();
        System.out.println("Do you want to save the result to file?");
        System.out.println("1. Yes");
        System.out.println("2. No");

        if (readMenuChoice(YES_NO_MIN, YES_NO_MAX) == 1) {
            saveResultToFile(sortedStudents, occurrenceResult);
        }
    }

    private void saveResultToFile(MyList<Student> sortedStudents, OccurrenceResult occurrenceResult) {
        while (true) {
            System.out.print("Enter output file path or press Enter for " + DEFAULT_OUTPUT_FILE + ": ");
            String pathText = scanner.nextLine().trim();

            try {
                Path path = pathText.isBlank() ? Path.of(DEFAULT_OUTPUT_FILE) : Path.of(pathText);
                studentFileWriter.writeSortedStudents(sortedStudents, path);

                if (occurrenceResult != null) {
                    studentFileWriter.writeOccurrenceResult(
                            occurrenceResult.student(),
                            occurrenceResult.count(),
                            path
                    );
                }

                System.out.println("Result saved to file: " + path.toAbsolutePath());
                return;
            } catch (InvalidPathException e) {
                System.out.println("Invalid file path. Try again.");
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
                System.out.println("Try again.");
            }
        }
    }

    private MyList<Student> loadStudentsFromFile(String pathText) throws IOException {
        try (InputStream inputStream = Files.newInputStream(Path.of(pathText))) {
            return inputStudent.input(inputStream, 0);
        }
    }

    private void printStudents(MyList<Student> students) {
        for (int i = 0; i < students.size(); i++) {
            System.out.println((i + 1) + ". " + students.get(i));
        }
    }

    private record OccurrenceResult(Student student, int count) {
    }
}
