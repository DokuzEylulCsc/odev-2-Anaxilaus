package Problemset;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

class University implements Serializable {

    private final String Name;
    private Set<Student> studentSet;
    private Set<Faculty> Faculties;

    public String getName() { return Name; }

    public Set<Faculty> getFaculties() {
        return Faculties;
    }
    public void setFaculties(Set<Faculty> faculties) {
        Faculties = faculties;
    }
    public void addFaculty(Faculty faculty) {
        getFaculties().add(faculty);
    }

    public Set<Student> getStudents() {
        return studentSet;
    }

    public void setStudents(Set<Student> students) {
        this.studentSet = students;
    }

    /**
     * Search Student in University by name
     *
     * @param searchName String
     * @return if found Student, else null
     * @see Student
     */
    public Student searchStudent(String searchName) {
        for (Student s : getStudents()) {
            if (s.getName().equals(searchName)) return s;
        }
        return null;
    }

    /**
     * Search Student in University by name
     *
     * @param id decimal
     * @return if found Student, else null
     * @see Student
     */
    public Student searchStudent(Integer id) {
        for (Student s : getStudents()) {
            if (s.getId().equals(id)) return s;
        }
        return null;
    }

    /**
     * Search Faculty in University by name
     *
     * @param searchName String
     * @return if found Faculty, else null
     * @see Faculty
     */
    public Faculty searchFaculty(String searchName) {
        for (Faculty f : getFaculties()) {
            if (f.getName().equals(searchName)) return f;
        }
        return null;
    }

    public University(String name) {
        Name = name;
        setFaculties(new HashSet<>());
    }

    public University(String name, Set<Faculty> faculties) {
        Name = name;
        setFaculties(faculties);
    }
}
