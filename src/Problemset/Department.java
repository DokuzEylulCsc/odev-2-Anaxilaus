package Problemset;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

class Department implements Collection, Serializable {

    private final String Name;
    Set<Class> classSet;
    Set<Teacher> teacherSet;

    /**
     * @return Class iterator.
     */
    public Iterator createIterator() {
        return getClassSet().iterator();
    }

    public Set<Class> getClassSet() {
        return classSet;
    }

    private void setClassSet(Set<Class> classSet) {
        this.classSet = classSet;
    }
    public void addClass(Class newClass) throws AlreadyExistsException {
        if (getClassSet().contains(newClass)) throw new AlreadyExistsException();
        else getClassSet().add(newClass);
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
        setTeacherSet(new HashSet<>());
        setClassSet(new HashSet<>());
    }
    public Department(String name, Set<Class> _classSet) {
        Name = name;
        setTeacherSet(new HashSet<>());
        setClassSet(_classSet);
    }

    public Boolean equals(Department d) {
        return getName().equals(d.getName());
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public int hashCode() { return getName().hashCode(); }
}
