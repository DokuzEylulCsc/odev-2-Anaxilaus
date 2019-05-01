package Problemset;

import java.io.Serializable;
import java.util.Set;

class Class implements Serializable {

    private final String Code;
    Set<Student> studentSet;
    Set<Teacher> teacherSet;

    public String getCode() {
        return Code;
    }

    public Set<Student> getStudentSet() {
        return studentSet;
    }

    public void setStudentSet(Set<Student> studentSet) {
        this.studentSet = studentSet;
    }

    public Set<Teacher> getTeacherSet() {
        return teacherSet;
    }

    public void setTeacherSet(Set<Teacher> teacherSet) {
        this.teacherSet = teacherSet;
    }

    public Class(String code) {
        Code = code.toUpperCase();
    }

    public Class(String code, Set<Teacher> teachers, Set<Student> students) {
        Code = code.toUpperCase();
        setStudentSet(students);
        setTeacherSet(teachers);
    }

    @Override public String toString() {
        return getCode();
    }

    @Override public int hashCode() {
        return getCode().hashCode();
    }
}