package Problemset;

import java.io.Serializable;
import java.util.*;

/**
 * Represents any Human being in an educational institute.
 */
public abstract class Human implements Iterable, Serializable {

    private final Integer       Id;
    private final String        Name;
    private Collection<Class>   classes;


    public Human(Integer id) {
        this(id, null);
    }

    public Human(Integer id, String name) {
        Id = id;
        Name = name;
        setClasses(new ArrayList<>());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }

    @Override
    public String toString() {
        return this.getClass().getName().substring("Problemset.".length())
                + " | Id: " + getId()
                + " | Name: " + getName();
    }

    /**
     * @return Class object iterator.
     * @see Iterator
     * @see Class
     */
    public Iterator iterator() {
        return getClasses().iterator();
    }

    public Integer getId() {
        return Id;
    }

    public String getName() {
        if (Name != null) return Name;
        else return "null";
    }

    /*
     * joinClass and dropClass methods are protected because
     * students can drop a class themselves but they can't
     * add or remove their names formally themselves. Therefore,
     * these methods are exists only within package.
     */

    /* Used by Class.add* */
    protected void joinClass(Class newClass) throws AlreadyExistsException {

        if (getClasses().contains(newClass))
            throw new AlreadyExistsException(newClass.getCode());
        else getClasses().add(newClass);
    }

    /* Used by Class.remove* */
    protected void dropClass(Class oldClass) throws DoesntExistsException {

        if (getClasses().contains(oldClass)) getClasses().remove(oldClass);
        else throw new DoesntExistsException(oldClass.getCode());
    }

    private Collection<Class> getClasses() {
        return classes;
    }

    private void setClasses(Collection<Class> classes) {
        this.classes = classes;
    }
}
