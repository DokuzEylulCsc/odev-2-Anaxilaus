package Problemset;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

class Faculty implements Collection, Serializable {
    private final String Name;

    public String getName() {
        return Name;
    }

    Set<Department> departmentSet;

    /**
     * @return Department iterator.
     */
    public Iterator createIterator() {
        return getDepartmentSet().iterator();
    }

    public void addDepartment(Department department) throws AlreadyExistsException {
        if (getDepartmentSet().contains(department)) {
            throw new AlreadyExistsException();
        } else {
            getDepartmentSet().add(department);
        }
    }

    private Set<Department> getDepartmentSet() {
        return departmentSet;
    }

    private void setDepartmentSet(Set<Department> departmentSet) {
        this.departmentSet = departmentSet;
    }

    /**
     * Search Faculty in University by name
     *
     * @param searchName String
     * @return if found Faculty, else null
     * @see Faculty
     */
    public Department searchDepartment(Department department) {
        for (Department d : getDepartmentSet()) {
            if(d.equals(department)) return d;
        }
        return null;
    }

    public Faculty(String name) {
        Name = name;
        setDepartmentSet(new HashSet<>());
    }

    public Faculty(String name, Set<Department> departmentSet) {
        Name = name;
        setDepartmentSet(departmentSet);
    }

    public Boolean equals(Faculty d) {
        return getName().equals(d.Name);
    }

    @Override
    public String toString(){
        return getName();
    }

    @Override
    public int hashCode() { return getName().hashCode(); }

}