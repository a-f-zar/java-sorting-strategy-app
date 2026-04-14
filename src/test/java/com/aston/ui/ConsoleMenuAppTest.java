package com.aston.ui;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ConsoleMenuAppTest {

    @Test
    @DisplayName("Консольное меню завершает работу при выборе пункта выхода")
    void runShouldFinishWhenUserChoosesExit() {
        String output = runApp("0" + System.lineSeparator());

        assertTrue(output.contains("=== Student Sorting App ==="));
        assertTrue(output.contains("Application finished."));
    }

    @Test
    @DisplayName("Консольное меню проходит сценарий со случайной генерацией и сортировкой")
    void runShouldProcessRandomInputAndSortStudents() {
        String input = String.join(System.lineSeparator(),
                "2",
                "3",
                "1",
                "1",
                "2",
                "2",
                "0"
        ) + System.lineSeparator();

        String output = runApp(input);

        assertTrue(output.contains("Source collection:"));
        assertTrue(output.contains("Sorted collection:"));
        assertTrue(output.contains("Do you want to count occurrences of a student?"));
        assertTrue(output.contains("Do you want to save the result to file?"));
        assertTrue(output.contains("Application finished."));
    }

    private String runApp(String input) {
        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;

        ByteArrayInputStream testIn = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream testOut = new PrintStream(outputStream, true, StandardCharsets.UTF_8);

        try {
            System.setIn(testIn);
            System.setOut(testOut);

            ConsoleMenuApp app = new ConsoleMenuApp();
            app.run();

            return outputStream.toString(StandardCharsets.UTF_8);
        } finally {
            System.setIn(originalIn);
            System.setOut(originalOut);
        }
    }
}
