package Problemset;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Represents any Human being in an Educational Institute.
 */
public abstract class Human implements Iterable, Serializable {

    private final Integer   Id;
    private final String    Name;
    private Set<Class>      classSet;


    public Human(Integer id) {
        this(id, null);
    }

    public Human(Integer id, String name) {
        Id = id;
        Name = name;
        setClassSet(new HashSet<>());
    }

    /**
     * @return Iterator for associated classes.
     */
    @Override
    public Iterator iterator() {
        return getClassSet().iterator();
    }

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

    private void setClassSet(Set<Class> classSet) {
        this.classSet = classSet;
    }

    public void addClass(Class newClass) throws AlreadyExistsException {
        if (getClassSet().contains(newClass)) throw new AlreadyExistsException(newClass.getCode());
        else getClassSet().add(newClass);
    }

    public void dropClass(Class oldClass) throws DoesntExistsException {
        if (getClassSet().contains(oldClass)) getClassSet().remove(oldClass);
        else throw new DoesntExistsException(oldClass.getCode());
    }

    @Override
    public String toString() {
        return this.getClass().getName().substring("Problemset.".length())
                + " | Id: " + getId()
                + " | Name: " + getName();
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
