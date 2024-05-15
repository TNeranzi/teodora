package edu.scoalainformala.HW08Exceptions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StudentTest {
    private StudentRepository repository;

    @BeforeEach
    public void setUp() {
        repository = new StudentRepository();
    }

    @Test
    public void testAddStudent() throws Exception {
        Student student = new Student("John", "Doe", LocalDate.of(2000, 1, 1), "male", "1234567890123");
        repository.addStudent(student);
        assertTrue(repository.getAllStudents(21).contains(student));
    }

    @Test
    public void testAddStudent_DuplicateId() throws Exception {
        Student student1 = new Student("John", "Doe", LocalDate.of(2000, 1, 1), "male", "1234567890123");
        Student student2 = new Student("Jane", "Doe", LocalDate.of(2000, 1, 1), "female", "1234567890123");

        repository.addStudent(student1);
        assertThrows(Exception.class, () -> repository.addStudent(student2));
    }

    @Test
    public void testDeleteStudent() throws Exception {
        Student student = new Student("John", "Doe", LocalDate.of(2000, 1, 1), "male", "1234567890123");
        repository.addStudent(student);
        repository.deleteStudent(student.getId());
        assertTrue(repository.getAllStudents(21).isEmpty());
    }

    @Test
    public void testDeleteStudent_NonexistentId() throws Exception {
        assertThrows(Exception.class, () -> repository.deleteStudent("1234567890123"));
    }

    @Test
    public void testGetAllStudents() throws Exception {
        Student student1 = new Student("John", "Doe", LocalDate.of(2000, 1, 1), "male", "1234567890123");
        Student student2 = new Student("Jane", "Doe", LocalDate.of(2003, 1, 1), "female", "2345678901234");
        Student student3 = new Student("Alice", "Smith", LocalDate.of(2005, 1, 1), "female", "3456789012345");

        repository.addStudent(student1);
        repository.addStudent(student2);
        repository.addStudent(student3);

        assertEquals(2, repository.getAllStudents(21).size());
        assertEquals(1, repository.getAllStudents(23).size());
    }

    @Test
    public void testListStudents() throws Exception {
        Student student1 = new Student("John", "Doe", LocalDate.of(2000, 1, 1), "male", "1234567890123");
        Student student2 = new Student("Jane", "Smith", LocalDate.of(2000, 1, 1), "female", "2345678901234");

        repository.addStudent(student1);
        repository.addStudent(student2);

        List<Student> sortedByLastName = repository.listStudents(Comparator.comparing(Student::getLastName));
        assertEquals("Doe", sortedByLastName.get(0).getLastName());
        assertEquals("Smith", sortedByLastName.get(1).getLastName());

        List<Student> sortedByBirthDate = repository.listStudents(Comparator.comparing(Student::getDateOfBirth));
        assertEquals("John", sortedByBirthDate.get(0).getFirstName());
        assertEquals("Jane", sortedByBirthDate.get(1).getFirstName());
    }
}
