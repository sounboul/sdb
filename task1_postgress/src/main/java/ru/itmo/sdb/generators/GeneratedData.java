package ru.itmo.sdb.generators;

import ru.itmo.sdb.models.Discipline;
import ru.itmo.sdb.models.Specialty;
import ru.itmo.sdb.models.Student;
import ru.itmo.sdb.models.Subject;
import ru.itmo.sdb.models.Teacher;
import ru.itmo.sdb.models.University;

import java.util.List;

public class GeneratedData {
    private final List<Student> students;
    private final List<Subject> subjects;
    private final List<Teacher> teachers;
    private final List<Specialty> specialties;
    private final List<Discipline> disciplines;
    private final List<University> universities;

    public GeneratedData(
            List<Student> students,
            List<Subject> subjects,
            List<Teacher> teachers,
            List<Specialty> specialties,
            List<Discipline> disciplines,
            List<University> universities) {
        this.students = students;
        this.subjects = subjects;
        this.teachers = teachers;
        this.specialties = specialties;
        this.disciplines = disciplines;
        this.universities = universities;
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public List<Specialty> getSpecialties() {
        return specialties;
    }

    public List<Discipline> getDisciplines() {
        return disciplines;
    }

    public List<University> getUniversities() {
        return universities;
    }
}
