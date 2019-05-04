package Problemset;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


class University implements Collection, Serializable {

    private final String Name;
    private Set<Human> humanSet;
    private Set<Faculty> facultySet;

    /**
     * Return iterator for faculties.
     */
    public Iterator createIterator() {
        return createFacultyIterator();
    }

    public Iterator createFacultyIterator() {
        return getFacultySet().iterator();
    }

    public Iterator createHumanIterator() {
        return getHumanSet().iterator();
    }

    public Set<Human> getHumanSet() {
        return humanSet;
    }

    public void addHuman(Human h) throws AlreadyExistsException {
        if (getHumanSet().contains(h)) throw new AlreadyExistsException(h.getId() + " exists in " + getName());
        else getHumanSet().add(h);
    }


    private void setHumanSet(Set<Human> humanSet) {
        this.humanSet = humanSet;
    }

    /**
     * Add all Humans in all Classes to this University.
     * @param print verbose indicator
     */
    public void update(boolean print) {
        if (print) System.out.println("Starting verbose update of " + this);

        Iterator faculties = createIterator();
        while (faculties.hasNext()) {
            Faculty f = (Faculty) faculties.next();
            if (print) System.out.println(f.getName());

            Iterator departments = f.createIterator();
            while (departments.hasNext()) {
                Department d = (Department) departments.next();
                if (print) System.out.println("\t" + d);

                Iterator classes = d.createIterator();
                while (classes.hasNext()) {
                    Class c = (Class) classes.next();
                    if (print) System.out.println("\t\t" + c);

                    Iterator humans = c.createIterator();
                    while (humans.hasNext()) {
                        Human h = (Human)humans.next();
                        if (print) System.out.println("\t\t\t" + h);

                        try {
                            addHuman(h);
                            if (h instanceof Teacher) d.addTeacher((Teacher) h);
                        } catch (AlreadyExistsException e) {
                            continue;
                        }
                    }
                }
            }
        }
    }

    public void update() {
        update(true);
    }

    public void removeHuman(Human human) {
        if (getHumanSet().contains(human)) {
            getHumanSet().remove(human);
        }
    }

    public String getName() { return Name; }

    public Set<Faculty> getFacultySet() {
        return facultySet;
    }

    public void setFacultySet(Set<Faculty> facultySet) {
        this.facultySet = facultySet;
    }

    public void addFaculty(Faculty faculty) {
        if (getFacultySet().contains(faculty)) throw new AlreadyExistsException(faculty.getName());
        else getFacultySet().add(faculty);
    }

    /**
     * Search Student in University by name
     *
     * @param searchName String
     * @return if found Student, else null
     * @see Student
     */
    public Student searchStudent(String searchName) {
        Iterator iterator = createHumanIterator();
        while(iterator.hasNext()) {
            Human h = (Human) iterator.next();
            if (h instanceof Student && h.getName().equals(searchName)) {
                return (Student) h;
            }
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
        Iterator iterator = createHumanIterator();
        while(iterator.hasNext()) {
            Human h = (Human) iterator.next();
            if (h instanceof Student && h.getId().equals(id)) {
                return (Student) h;
            }
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
        Iterator iterator = createFacultyIterator();
        while (iterator.hasNext()) {
            Faculty f = (Faculty) iterator.next();
            if (f.getName().equals(searchName)) return f;
        }

        return null;
    }

    public University(String name) {
        Name = name;
        setFacultySet(new HashSet<>());
        setHumanSet(new HashSet<>());
    }

    public University(String name, Set<Faculty> faculties) {
        Name = name;
        setFacultySet(faculties);
        setHumanSet(new HashSet<>());
    }

    @Override
    public String toString() {
        return getName();
    }
}
