package Problemset;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;


class Class implements Iterable, Serializable {

    private final String        Code;
    private Collection<Student> students;
    private Collection<Teacher> teachers;


    public Class(String code) {
        this(code, new HashSet<>(), new HashSet<>());
    }

    public Class(String code, Collection<Teacher> teachers, Collection<Student> students) {

        Code = code.toUpperCase();
        setTeachers(teachers);
        setStudents(students);
    }


    @Override
    public int hashCode() {
        return getCode().hashCode();
    }

    @Override
    public String toString() {
        return getCode();
    }

    /**
     * @return Human iterator.
     * @see Iterator
     * @see Human
     */
    public Iterator iterator() {
        return (new ArrayList<>(){{
                    addAll(getStudents());
                    addAll(getTeachers());
                }}).iterator();
    }

    /**
     * Use default path to export this class as XML.
     */
    public void exportXml() { exportXml(getCode() + ".xml"); }

    /**
     * Exports this Class's Code, Teachers and Students
     * as XML file to filePath.
     *
     * @param filePath String object
     */
    public void exportXml(String filePath) {
        try {
            // root
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            Element rootElement = doc.createElement("class");
            doc.appendChild(rootElement);

            // add class code
            Element code = doc.createElement("code");
            code.appendChild(doc.createTextNode(getCode()));
            rootElement.appendChild(code);

            // humans
            // create elements
            Element teachers = doc.createElement("teachers");
            Element students = doc.createElement("students");
            rootElement.appendChild(teachers);
            rootElement.appendChild(students);

            // add nodes
            for (Object obj : this) {
                Element element;
                if (obj instanceof Teacher) {
                    element = doc.createElement("teacher");
                    element.setAttribute("id", ((Teacher) obj).getId().toString());

                    teachers.appendChild(element);
                } else {
                    element = doc.createElement("student");
                    element.setAttribute("id", ((Student) obj).getId().toString());
                    element.setAttribute("type", obj.getClass().getName().substring("Problemset.".length()));

                    students.appendChild(element);
                }
                element.appendChild(doc.createTextNode(((Human) obj).getName()));
            }

            // write to file
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            DOMSource source = new DOMSource(doc);
            File file = new File(filePath);
            StreamResult result = new StreamResult(file);
            transformer.transform(source, result);

            System.out.println("XML file saved to " + file.getAbsolutePath());

        } catch (ParserConfigurationException e) {
            System.out.println("ParserConfigurationException!");
            e.printStackTrace();
        } catch (TransformerException e) {
            System.out.println("TransformerException!");
            e.printStackTrace();
        }
    }

    /**
     * Assign a Teacher to this class.
     *
     * @param t Teacher object
     * @throws AlreadyExistsException if already exists
     */
    public void addTeacher(Teacher t) throws AlreadyExistsException {
        if (getTeachers().contains(t)) throw new AlreadyExistsException();
        else getTeachers().add(t);
    }

    /**
     * Remove a Teacher from this class.
     * @param t Teacher object
     * @throws DoesntExistsException if doesn't exists
     */
    public void removeTeacher(Teacher t) throws DoesntExistsException {
        if (getTeachers().contains(t)) getTeachers().remove(t);
        else throw new DoesntExistsException();
    }

    /**
     * Add a student to this class.
     *
     * @param s Student object.
     * @throws AlreadyExistsException if already exists.
     */
    public void addStudent(Student s) throws AlreadyExistsException {
        if (getStudents().contains(s)) throw new AlreadyExistsException();
        else {
            getStudents().add(s);
            s.joinClass(this);
        }
    }

    /**
     * Remove a student from this class.
     *
     * @param s Student object
     * @throws DoesntExistsException if doesn't exists
     */
    public void removeStudent(Student s) throws DoesntExistsException {
        if (getStudents().contains(s)) {
            getStudents().remove(s);
            s.dropClass(this);
        }
        else throw new DoesntExistsException();
    }

    public String getCode() {
        return Code;
    }

    private Collection<Teacher> getTeachers() {
        return teachers;
    }

    private void setTeachers(Collection<Teacher> teachers) {
        this.teachers = teachers;
    }

    private Collection<Student> getStudents() {
        return students;
    }

    private void setStudents(Collection<Student> students) {
        this.students = students;
    }
}
