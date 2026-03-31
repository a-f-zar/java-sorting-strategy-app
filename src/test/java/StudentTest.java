import com.aston.models.Student;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @Test
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
    void testEqualsAndHashCode() {
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

        Student s3 = Student.builder()
                .name("Roman")
                .averageGrade(3.0)
                .studentCardNumber(54321)
                .build();

        // одинаковые объекты
        assertEquals(s1, s2);
        assertEquals(s1.hashCode(), s2.hashCode());

        // разные объекты
        assertNotEquals(s1, s3);
    }

    @Test
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