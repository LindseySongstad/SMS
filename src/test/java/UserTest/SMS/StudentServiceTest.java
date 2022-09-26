package UserTest.SMS;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jpa.entitymodels.Course;
import jpa.entitymodels.Student;
import jpa.service.StudentService;

class StudentServiceTest {
	private static StudentService studentService;
	@BeforeEach
	void setUp() throws Exception {
		studentService = new StudentService();
	}

	@Test
	void testGetCourseById() {
		Course expected = new Course();
		expected.setcId(1);
		expected.setcName("English");
		expected.setcInstuctorName("Anderea Scamaden");
		Course actual = studentService.getCourseById(1);
		assertEquals(expected, actual);
	}
}
