package com.aston.concurrent;

import com.aston.models.Student;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class StudentOccurrenceCounter {

    private static final long AWAIT_TIMEOUT_SECONDS = 5L;

    public int countOccurrences(List<Student> students, Student target) {
        if (students == null) {
            throw new IllegalArgumentException("Students list cannot be null");
        }

        if (target == null) {
            throw new IllegalArgumentException("Target student cannot be null");
        }

        if (students.isEmpty()) {
            return 0;
        }

        int threadCount = resolveThreadCount(students.size());
        AtomicInteger counter = new AtomicInteger(0);
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        int partSize = (int) Math.ceil((double) students.size() / threadCount);

        try {
            for (int i = 0; i < threadCount; i++) {
                int start = i * partSize;
                int end = Math.min(start + partSize, students.size());

                if (start >= students.size()) {
                    break;
                }

                List<Student> part = students.subList(start, end);
                executorService.submit(() -> countPartOccurrences(part, target, counter));
            }
        } finally {
            executorService.shutdown();
        }

        awaitCompletion(executorService);
        return counter.get();
    }

    private int resolveThreadCount(int studentsCount) {
        return Math.min(studentsCount, Runtime.getRuntime().availableProcessors());
    }

    private void countPartOccurrences(List<Student> studentsPart, Student target, AtomicInteger counter) {
        for (Student student : studentsPart) {
            if (student.equals(target)) {
                counter.incrementAndGet();
            }
        }
    }

    private void awaitCompletion(ExecutorService executorService) {
        try {
            if (!executorService.awaitTermination(AWAIT_TIMEOUT_SECONDS, TimeUnit.SECONDS)) {
                throw new IllegalStateException("Threads did not finish in time");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Thread execution was interrupted", e);
        }
    }
}
