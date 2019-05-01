package Problemset;

import java.util.HashSet;
import java.util.Set;

class Faculty {

    private final String Name;
    Set<Department> departmentSet;

    public String getName() {
        return Name;
    }

    public Set<Department> getDepartmentSet() {
        return departmentSet;
    }

    public void setDepartmentSet(Set<Department> departmentSet) {
        this.departmentSet = departmentSet;
    }

    public void addDepartment(Department department) {
        getDepartmentSet().add(department);
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

    @Override public int hashCode() { return getName().hashCode(); }

}