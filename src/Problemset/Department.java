package Problemset;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.HashSet;


class Department implements Iterable, Serializable {

    private final String        Name;
    private Collection<Class>   classes;
    private Collection<Teacher> teachers;


    public Department(String name) {
        this(name, new HashSet<>());
    }

    /*
     * Because of University.update, Teachers will be imported from classes.
     * No need to pass Iterable<Teacher> to initialize.
     */
    public Department(String name, Collection<Class> collection) {
        Name = name;
        setTeachers(new HashSet<>());
        setClasses(collection);
    }


    @Override
    public int hashCode() { return getName().hashCode(); }

    @Override
    public String toString() {
        return getName();
    }

    /**
     * @return Class iterator.
     * @see Iterator
     * @see Class
     */
    public Iterator iterator() {
        return getClasses().iterator();
    }

    /**
     * Open a pre-initialized Class in this Department.
     *
     * @param newClass Already initialized Class object.
     * @throws AlreadyExistsException if newClass already exists
     */
    public void openClass(Class newClass) throws AlreadyExistsException {
        if (getClasses().contains(newClass)) throw new AlreadyExistsException();
        else getClasses().add(newClass);
    }

    /**
     * Close a class. Update remove all Humans' relations to
     * this Class and delete this object.
     *
     * @param oldClass Class object to terminate
     * @throws DoesntExistsException if oldClass doesn't exists
     */
    public void closeClass(Class oldClass) throws DoesntExistsException {
        if (getClasses().contains(oldClass)) {
            for (Object h : oldClass) {
                if (h instanceof Teacher) ((Teacher) h).dropClass(oldClass);
                else  ((Student) h).dropClass(oldClass);
            }
            getClasses().remove(oldClass);
            System.out.println("Closed class: " + oldClass.toString());
        } else throw new DoesntExistsException();
    }

    /**
     * Try to register a new Teacher into this Department.
     *
     * @param newTeacher Teacher object to register.
     * @throws AlreadyExistsException if object already registered.
     */
    public void addTeacher(Teacher newTeacher) throws AlreadyExistsException {
        if (getTeachers().contains(newTeacher)) {
            throw new AlreadyExistsException();
        } else getTeachers().add(newTeacher);
    }

    /**
     * Try to remove a Teacher from this Department.
     *
     * @param teacher Teacher object to remove
     * @throws DoesntExistsException if object doesn't exists
     */
    public void removeTeacher(Teacher teacher) throws DoesntExistsException {
        if (getTeachers().contains(teacher)) {
            getTeachers().remove(teacher);
        } else throw new DoesntExistsException();
    }

    /**
     * @return Name of this Department.
     */
    public String getName() {
        return Name;
    }

    /**
     * Compare 2 Departments by their hashCode.
     * @param other Department to compare
     * @return boolean
     * @see boolean
     */
    public Boolean equals(Department other) {
        return this.hashCode() == other.hashCode();
    }

    private Collection<Teacher> getTeachers() {
        return teachers;
    }

    private void setTeachers(Collection<Teacher> teachers) {
        this.teachers = teachers;
    }

    private Collection<Class> getClasses() {
        return classes;
    }

    private void setClasses(Collection<Class> classes) {
        this.classes = classes;
    }
}
