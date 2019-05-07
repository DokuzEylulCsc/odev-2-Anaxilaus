package Problemset;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.HashSet;


/**
 * Root object of the object structure.
 */
class University implements Iterable, Serializable {

    private final String            Name;
    private Collection<Human>       humans;
    private Collection<Faculty>     faculties;


    public University(String name) {
        this(name, new HashSet<>());
    }

    public University(String name, Collection<Faculty> faculties) {
        Name = name;
        setFaculties(faculties);
        setHumans(new HashSet<>());

        update(false);
    }


    @Override
    public String toString() {
        return getName();
    }

    /**
     * @return Faculty iterator.
     * @see this.createFacultyIterator
     */
    public Iterator iterator() {
        return createFacultyIterator();
    }

    /**
     * @return Faculty iterator.
     * @see Iterator
     * @see Faculty
     */
    public Iterator createFacultyIterator() {
        return getFaculties().iterator();
    }

    /**
     * @return Human iterator.
     * @see Iterator
     * @see Human
     */
    public Iterator createHumanIterator() {
        return getHumans().iterator();
    }

    /**
     * Add all people in every Class who isn't included in this University.
     * Update University bottom up using iterators.
     */
    public void update() {
        update(true);
    }

    /**
     * Add all people in every Class who isn't included in this University.
     * Update University bottom up using iterators.
     *
     * @param verbose boolean
     */
    public void update(boolean verbose) {
        if (verbose) System.out.println("Starting verbose update of " + this);

        for (Object objF : this) {
            Faculty f = (Faculty) objF;
            if (verbose) System.out.println(f.getName());

            for (Object objD : (Faculty) objF) {
                Department d = (Department) objD;
                if (verbose) System.out.println("\t" + d);

                for (Object objC : d) {
                    Class c = (Class) objC;
                    if (verbose) System.out.println("\t\t" + c);

                    for (Object objH : c) {
                        Human h = (Human) objH;
                        if (verbose) System.out.println("\t\t\t" + h);

                        try {
                            this.addHuman(h);
                            if (h instanceof Teacher) d.addTeacher((Teacher) h);
                        } catch (AlreadyExistsException e) {
                            ;
                        }
                    }
                }
            }
        }
    }

    /**
     * Search Student in University by Name.
     *
     * @param searchName String
     * @return if found Student, else null
     * @see Student
     * @see null
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
     * Search Student in University by id number.
     *
     * @param id decimal
     * @return if found Student, else null
     * @see Student
     * @see null
     */
    public Student searchStudent(Integer id) {
        Iterator iterator = createHumanIterator();
        while(iterator.hasNext()) {
            Human h = (Human) iterator.next();

            if (h instanceof Student && h.getId().equals(id))
                return (Student) h;
        }
        return null;
    }

    /**
     * Search Faculty in University by Name.
     *
     * @param searchName String
     * @return if found Faculty, else null
     * @see Faculty
     * @see null
     */
    public Faculty searchFaculty(String searchName) {
        Iterator iterator = createFacultyIterator();
        while (iterator.hasNext()) {
            Faculty f = (Faculty) iterator.next();

            if (f.getName().equals(searchName))
                return f;
        }
        return null;
    }

    /**
     * Register a Human to this University.
     *
     * @param human Human object
     * @throws AlreadyExistsException if human already exists
     */
    public void addHuman(Human human) throws AlreadyExistsException {
        if (getHumans().contains(human))
            throw new AlreadyExistsException(human.getId() + " exists in " + getName());
        else getHumans().add(human);
    }

    /**
     * Remove a Human from this University.
     *
     * @param human Human object
     * @throws DoesntExistsException if human doesn't exists
     */
    public void removeHuman(Human human) throws DoesntExistsException {
        if (getHumans().contains(human)) getHumans().remove(human);
        else throw new DoesntExistsException();
    }

    /**
     * Add a Faculty to this University.
     *
     * @param faculty Faculty object
     * @throws AlreadyExistsException if already exists
     */
    public void addFaculty(Faculty faculty) throws AlreadyExistsException {
        if (getFaculties().contains(faculty))
            throw new AlreadyExistsException(faculty.getName());
        else getFaculties().add(faculty);
    }

    /**
     * Open a Faculty in this University.
     * Initializes a Faculty and invokes addFaculty.
     *
     * @param Name Name of the Faculty
     * @param departmentIterable Iterable object contains departments to add, or null
     */
    public void openFaculty(String Name, Collection<Department> departmentIterable) {
        addFaculty(new Faculty(Name, departmentIterable));
    }

    public String getName() { return Name; }

    private Collection<Faculty> getFaculties() {
        return faculties;
    }

    private void setFaculties(Collection<Faculty> faculties) {
        this.faculties = faculties;
    }

    private Collection<Human> getHumans() {
        return humans;
    }

    private void setHumans(Collection<Human> humans) {
        this.humans = humans;
    }
}
