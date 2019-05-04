package Problemset;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


public abstract class Human implements Collection, Serializable {

    private final Integer Id;
    private final String Name;
    private Set<Class> classSet;

    /**
     * @return Iterator for associated classes.
     */
    @Override
    public Iterator createIterator() {
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

    public Human(Integer id) {
        Id = id;
        Name = null;
        setClassSet(new HashSet<>());
    }

    public Human(Integer id, String name) {
        Id = id;
        Name = name;
        setClassSet(new HashSet<>());
    }

    @Override
    public String toString() {
        return this.getClass().getName().substring("Problemset.".length()) + " | Id: " + getId() + " | Name: " + getName();
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
