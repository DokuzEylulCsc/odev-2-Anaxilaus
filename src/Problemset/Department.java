package Problemset;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

class Department implements Serializable {

    private final String Name;
    Set<Class> classSet;
    Set<Teacher> teacherSet;

    public Set<Class> getClassSet() {
        return classSet;
    }

    public void setClassSet(Set<Class> classSet) {
        this.classSet = classSet;
    }

    public void addClass(Class newClass) {
        getClassSet().add(newClass);
    }

    public void closeClass(Class oldClass) throws UnsupportedOperationException {
        if (getClassSet().contains(oldClass)) {
            for (Student s : oldClass.getStudentSet()) s.dropClass(oldClass);
            for (Teacher t : oldClass.getTeacherSet()) t.dropClass(oldClass);
            getClassSet().remove(oldClass);
            System.out.println("Closed class: " + oldClass.toString());
        } else throw new UnsupportedOperationException("You tried to close a class the doesn't exist in this Faculty: " + this.toString());
    }

    public Set<Teacher> getTeacherSet() {
        return teacherSet;
    }

    public void setTeacherSet(Set<Teacher> teacherSet) {
        this.teacherSet = teacherSet;
    }

    public void addTeacher(Teacher newTeacher) {
        getTeacherSet().add(newTeacher);
    }

    public String getName() {
        return Name;
    }

    public Department(String name) {
        Name = name;
        setClassSet(new HashSet<>());
    }
    public Department(String name, Set<Class> _classSet) {
        Name = name;
        setClassSet(_classSet);
    }

    public Boolean equals(Department d) {
        return getName().equals(d.getName());
    }

    @Override public int hashCode() { return getName().hashCode(); }
}
