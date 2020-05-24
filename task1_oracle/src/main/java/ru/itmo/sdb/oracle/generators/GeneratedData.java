package ru.itmo.sdb.oracle.generators;

import ru.itmo.sdb.oracle.models.Department;
import ru.itmo.sdb.oracle.models.Direction;
import ru.itmo.sdb.oracle.models.Discipline;
import ru.itmo.sdb.oracle.models.Mark;
import ru.itmo.sdb.oracle.models.Specialty;
import ru.itmo.sdb.oracle.models.Student;
import ru.itmo.sdb.oracle.models.StudyGroup;
import ru.itmo.sdb.oracle.models.Subject;
import ru.itmo.sdb.oracle.models.Teacher;

import java.util.List;

public class GeneratedData {
    private final List<Mark> marks;
    private final List<Student> students;
    private final List<Subject> subjects;
    private final List<Teacher> teachers;
    private final List<StudyGroup> studyGroups;
    private final List<Specialty> specialties;
    private final List<Department> departments;
    private final List<Discipline> disciplines;
    private final List<Direction> directions;

    public GeneratedData(List<Mark> marks,
                         List<Student> students,
                         List<Subject> subjects,
                         List<Teacher> teachers,
                         List<StudyGroup> studyGroups,
                         List<Specialty> specialties,
                         List<Department> departments,
                         List<Discipline> disciplines,
                         List<Direction> directions) {
        this.marks = marks;
        this.students = students;
        this.subjects = subjects;
        this.teachers = teachers;
        this.studyGroups = studyGroups;
        this.specialties = specialties;
        this.departments = departments;
        this.disciplines = disciplines;
        this.directions = directions;
    }

    public List<Mark> getMarks() {
        return marks;
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

    public List<StudyGroup> getStudyGroups() {
        return studyGroups;
    }

    public List<Specialty> getSpecialties() {
        return specialties;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public List<Discipline> getDisciplines() {
        return disciplines;
    }

    public List<Direction> getDirections() {
        return directions;
    }
}
