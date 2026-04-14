package com.aston.input;

import com.aston.exception.StudentFileLoadException;
import com.aston.exception.ValidationException;
import com.aston.models.Student;
import com.aston.models.custom.MyList;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.StreamSupport;

public class StudentJsonFileLoader {

    private final StudentInputParser parser;
    private final ObjectMapper objectMapper;

    public StudentJsonFileLoader() {
        this(new StudentInputParser(), new ObjectMapper());
    }

    StudentJsonFileLoader(StudentInputParser parser, ObjectMapper objectMapper) {
        this.parser = parser;
        this.objectMapper = objectMapper;
    }

    public MyList<Student> load(Path path) {
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            return loadRootNode(objectMapper.readTree(reader));
        } catch (IOException e) {
            throw new StudentFileLoadException("Failed to read JSON file: " + path.toAbsolutePath(), e);
        }
    }

    public MyList<Student> load(InputStream inputStream) {
        if (inputStream == null) {
            throw new StudentFileLoadException("Input stream cannot be null");
        }

        try {
            return loadRootNode(objectMapper.readTree(inputStream));
        } catch (IOException e) {
            throw new StudentFileLoadException("Failed to read JSON content", e);
        }
    }

    private MyList<Student> loadRootNode(JsonNode rootNode) {
        if (rootNode == null || !rootNode.isArray()) {
            throw new StudentFileLoadException("JSON root must be an array of student objects");
        }

        return new CustomArrayList<Student>(
                StreamSupport.stream(rootNode.spliterator(), false)
                        .filter(JsonNode::isObject)
                        .map(this::parseStudent)
                        .flatMap(Optional::stream)
                        .collect(() -> new CustomArrayList<Student>(), MyList::add, MyList::addAll)
        );
    }

    private Optional<Student> parseStudent(JsonNode recordNode) {
        String name = getFieldAsText(recordNode, "name");
        String averageGrade = getFieldAsText(recordNode, "averageGrade");
        String studentCardNumber = getFieldAsText(recordNode, "studentCardNumber");

        if (name == null || averageGrade == null || studentCardNumber == null) {
            return Optional.empty();
        }

        try {
            return Optional.of(parser.parseInput(name, averageGrade, studentCardNumber));
        } catch (ValidationException e) {
            return Optional.empty();
        }
    }

    private String getFieldAsText(JsonNode node, String fieldName) {
        JsonNode field = node.get(fieldName);

        if (field == null || field.isNull()) {
            return null;
        }

        return field.asText();
    }
}