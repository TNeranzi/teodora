package edu.scoalainformala.HW08Exceptions;

import java.time.LocalDate;
import java.util.*;
import java.util.logging.Logger;

public class StudentRepository {
    private static final Logger logger = Logger.getLogger(StudentRepository.class.getName());
    private List<Student> students;

    public StudentRepository() {
        students = new ArrayList<>();
    }

    public void addStudent(Student student) throws Exception {
        if (student == null || student.getId() == null || student.getId().isEmpty()) {
            logger.severe("ID is empty.");
            throw new Exception("ID is empty.");
        }

        for (Student s : students) {
            if (s.getId().equals(student.getId())) {
                logger.severe("Student with the same ID already exists.");
                throw new Exception("Student with the same ID already exists.");
            }
        }

        students.add(student);
        logger.info("Student added: " + student.getId());
    }

    public void deleteStudent(String id) throws Exception {
        if (id == null || id.isEmpty()) {
            logger.severe("ID is empty.");
            throw new Exception("ID is empty.");
        }

        Student student = null;
        for (Student s : students) {
            if (s.getId().equals(id)) {
                student = s;
                break;
            }
        }

        if (student == null) {
            logger.severe("Student does not exist.");
            throw new Exception("Student does not exist.");
        }

        students.remove(student);
        logger.info("Student deleted: " + id);
    }

    public List<Student> getAllStudents(int minAge) throws Exception {
        if (minAge < 0) {
            logger.severe("Age cannot be negative.");
            throw new Exception("Age cannot be negative.");
        }

        LocalDate minBirthDate = LocalDate.now().minusYears(minAge);
        List<Student> result = new ArrayList<>();
        for (Student student : students) {
            if (student.getDateOfBirth().isBefore(minBirthDate)) {
                result.add(student);
            }
        }

        return result;
    }

    public List<Student> listStudents(Comparator<Student> comparator) throws Exception {
        if (comparator == null) {
            logger.severe("Comparator cannot be null.");
            throw new Exception("Comparator cannot be null.");
        }

        List<Student> result = new ArrayList<>(students);
        Collections.sort(result, comparator);

        return result;
    }
}