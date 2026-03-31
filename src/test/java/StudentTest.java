import com.aston.models.Student;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @Test
    @DisplayName("Builder correctly creates student and getters return correct values")
    void testBuilderAndGetters() {
        Student student = Student.builder()
                .name("Alexandr")
                .averageGrade(4.5)
                .studentCardNumber(12345)
                .build();

        assertEquals("Alexandr", student.getName());
        assertEquals(4.5, student.getAverageGrade());
        assertEquals(12345, student.getStudentCardNumber());
    }

    @Test
    @DisplayName("Student equals itself")
    void testEqualsSameObject() {
        Student student = Student.builder()
                .name("Alexandr")
                .averageGrade(4.5)
                .studentCardNumber(12345)
                .build();

        assertEquals(student, student);
    }

    @Test
    @DisplayName("Students with identical fields are equal")
    void testEqualsSameFields() {
        Student s1 = Student.builder()
                .name("Alexandr")
                .averageGrade(4.5)
                .studentCardNumber(12345)
                .build();

        Student s2 = Student.builder()
                .name("Alexandr")
                .averageGrade(4.5)
                .studentCardNumber(12345)
                .build();

        assertEquals(s1, s2);
    }

    @Test
    @DisplayName("Equal students have same hashCode")
    void testHashCodeSameFields() {
        Student s1 = Student.builder()
                .name("Alexandr")
                .averageGrade(4.5)
                .studentCardNumber(12345)
                .build();

        Student s2 = Student.builder()
                .name("Alexandr")
                .averageGrade(4.5)
                .studentCardNumber(12345)
                .build();

        assertEquals(s1.hashCode(), s2.hashCode());
    }

    @Test
    @DisplayName("Student compared with null returns false")
    void testEqualsNull() {
        Student student = Student.builder()
                .name("Alexandr")
                .averageGrade(4.5)
                .studentCardNumber(12345)
                .build();

        assertNotEquals(student, null);
    }

    @Test
    @DisplayName("Students with completely different fields are not equal")
    void testDifferentStudents() {
        Student s1 = Student.builder()
                .name("Alexandr")
                .averageGrade(4.5)
                .studentCardNumber(12345)
                .build();

        Student s2 = Student.builder()
                .name("Roman")
                .averageGrade(3.0)
                .studentCardNumber(54321)
                .build();

        assertNotEquals(s1, s2);
    }

    @Test
    @DisplayName("Students differ only by name")
    void testDifferentName() {
        Student s1 = Student.builder()
                .name("Alexandr")
                .averageGrade(4.5)
                .studentCardNumber(12345)
                .build();

        Student s2 = Student.builder()
                .name("Roman")
                .averageGrade(4.5)
                .studentCardNumber(12345)
                .build();

        assertNotEquals(s1, s2);
    }

    @Test
    @DisplayName("Students differ only by averageGrade")
    void testDifferentAverageGrade() {
        Student s1 = Student.builder()
                .name("Alexandr")
                .averageGrade(4.5)
                .studentCardNumber(12345)
                .build();

        Student s2 = Student.builder()
                .name("Alexandr")
                .averageGrade(3.5)
                .studentCardNumber(12345)
                .build();

        assertNotEquals(s1, s2);
    }

    @Test
    @DisplayName("Students differ only by studentCardNumber")
    void testDifferentCardNumber() {
        Student s1 = Student.builder()
                .name("Alexandr")
                .averageGrade(4.5)
                .studentCardNumber(12345)
                .build();

        Student s2 = Student.builder()
                .name("Alexandr")
                .averageGrade(4.5)
                .studentCardNumber(54321)
                .build();

        assertNotEquals(s1, s2);
    }

    @Test
    @DisplayName("toString contains all fields")
    void testToStringContainsFields() {
        Student s = Student.builder()
                .name("Alexandr")
                .averageGrade(4.5)
                .studentCardNumber(12345)
                .build();

        String str = s.toString();
        assertTrue(str.contains("Alexandr"));
        assertTrue(str.contains("4.5"));
        assertTrue(str.contains("12345"));
    }
}