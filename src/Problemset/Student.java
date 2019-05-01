package Problemset;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

abstract class Student implements Serializable {

    private final Integer Id;
    private final String Name;
    Set<Class> classSet;

    public Integer getId() {
        return Id;
    }

    public String getName() {
        if (Name == null) return "null";
        else return Name;
    }

    public Set<Class> getClassSet() {
        return classSet;
    }

    public void setClassSet(Set<Class> classSet) {
        this.classSet = classSet;
    }

    public void dropClass(Class oldClass) { getClassSet().remove(oldClass); }

    public Student(Integer id) {
        Id = id;
        Name = null;
        setClassSet(new HashSet<>());
    }

    public Student(Integer id, String name) {
        Id = id;
        Name = name;
        setClassSet(new HashSet<>());
    }

    @Override public String toString() {
        return "Student -> id: " + getId() + " name: " + getName();
    }

    @Override public int hashCode() {
        return getId().hashCode();
    }
}
