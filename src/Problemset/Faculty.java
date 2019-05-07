package Problemset;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.HashSet;


class Faculty implements Iterable, Serializable {

    private final String            Name;
    private Collection<Department>  departments;


    public Faculty(String name) {
        this(name, new HashSet<>());
    }

    public Faculty(String name, Collection<Department> departments) {
        Name = name;
        setDepartments(departments);
    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }

    @Override
    public String toString() {
        return getName();
    }

    /**
     * @return Department iterator.
     * @see Iterator
     * @see Department
     */
    public Iterator iterator() {
        return getDepartments().iterator();
    }

    /**
     * Search Faculty in University by name.
     *
     * @param department Department
     * @return if found Department, else null
     * @see Department
     */
    public Department searchDepartment(Department department) {
        for (Department d : getDepartments()) {
            if(d.equals(department)) return d;
        }
        return null;
    }

    /**
     * Add a Department to this Faculty.
     *
     * @param department Initialized Department object
     * @throws AlreadyExistsException if department already exists
     */
    public void addDepartment(Department department) throws AlreadyExistsException {
        if (getDepartments().contains(department)) {
            throw new AlreadyExistsException();
        } else {
            getDepartments().add(department);
        }
    }

    /**
     * Compare 2 Faculty objects.
     *
     * @param other Faculty object
     * @return boolean
     */
    public boolean equals(Faculty other) {
        return this.hashCode() == other.hashCode();
    }

    public String getName() {
        return Name;
    }

    private Collection<Department> getDepartments() {
        return departments;
    }

    private void setDepartments(Collection<Department> departments) {
        this.departments = departments;
    }
}
