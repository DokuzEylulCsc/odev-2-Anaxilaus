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
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

class Class implements Collection, Serializable {
    private final String Code;
    private Set<Student> studentSet;
    private Set<Teacher> teacherSet;

    /**
     * @return Human iterator.
     */
    public Iterator createIterator() {
        return (new HashSet<>(){{
            addAll(getStudentSet());
            addAll(getTeacherSet());
        }}).iterator();
    }

    public String getCode() {
        return Code;
    }

    public Set<Student> getStudentSet() {
        return studentSet;
    }

    private void setStudentSet(Set<Student> studentSet) {
        this.studentSet = studentSet;
    }

    public Set<Teacher> getTeacherSet() {
        return teacherSet;
    }

    private void setTeacherSet(Set<Teacher> teacherSet) {
        this.teacherSet = teacherSet;
    }

    public void addTeacher(Teacher t) throws AlreadyExistsException {
        if (getTeacherSet().contains(t)) throw new AlreadyExistsException();
        else getTeacherSet().add(t);
    }

    public void removeTeacher(Teacher t) throws DoesntExistsException {
        if (getTeacherSet().contains(t)) getTeacherSet().remove(t);
        else throw new DoesntExistsException();
    }

    public void addStudent(Student s) throws AlreadyExistsException {
        if (getStudentSet().contains(s)) throw new AlreadyExistsException();
        else getStudentSet().add(s);
    }

    public void removeStudent(Student s) throws DoesntExistsException {
        if (getStudentSet().contains(s)) getStudentSet().remove(s);
        else throw new DoesntExistsException();
    }

    @Override public String toString() {
        return getCode();
    }

    @Override public int hashCode() {
        return getCode().hashCode();
    }

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
            Iterator iterator = createIterator();
            while (iterator.hasNext()) {
                Object obj = iterator.next();
                Element element;
                if (obj instanceof Teacher) {
                    element = doc.createElement("teacher");
                    element.setAttribute("id", ((Teacher) obj).getId().toString());

                    teachers.appendChild(element);
                } else {
                    element = doc.createElement("student");
                    element.setAttribute("id", ((Student) obj).getId().toString());
                    element.setAttribute("studentType", obj.getClass().getName());

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

    public void exportXml() {
        exportXml(getCode() + ".xml");
    }

    public Class(String code) {
        Code = code.toUpperCase();
        setStudentSet(new HashSet<>());
        setTeacherSet(new HashSet<>());
    }

    public Class(String code, Set<Teacher> teachers, Set<Student> students) {
        Code = code.toUpperCase();
        setStudentSet(students);
        setTeacherSet(teachers);
    }
}