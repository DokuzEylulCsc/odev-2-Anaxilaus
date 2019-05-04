package Problemset;

import java.io.*;

public class Main {

    public static University createDefault() {

        try {
            Class BIL2002 = new Class("BIL2002");
            BIL2002.addTeacher(new Teacher(1, "Ugur Eliiyi"));
            BIL2002.addTeacher(new Teacher(2, "Alican Dogan"));
            BIL2002.addTeacher(new Teacher(3, "Baris Tezel"));

            BIL2002.addStudent(new Undergraduate(2016280010, "Mert Dede"));
            BIL2002.addStudent(new Undergraduate(2016280002, "Ulugbey Alp"));
            BIL2002.addStudent(new Undergraduate(234234234, "Firstname Lastname"));
            BIL2002.exportXml();

            Department computerScience = new Department("Computer Science");
            computerScience.addClass(BIL2002);

            Faculty science = new Faculty("Faculty of Science");
            science.addDepartment(computerScience);

            University DEU = new University("DEU");
            DEU.addFaculty(science);
            DEU.update();
            return DEU;

        } catch (UnsupportedOperationException e) {
            System.out.println("Exception while adding or removing elements!");
            e.printStackTrace();

            return null;
        }
    }

    static void Serialize(University university, String filePath) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(university);

            fileOutputStream.close();
            objectOutputStream.close();

            System.out.println(university.getName() + " serialized at " + new File(filePath).getAbsolutePath());

        } catch (FileNotFoundException e) {
            System.out.println("Serialization: File not found!" + filePath);
        } catch (IOException e) {
            System.out.println("Serialization: IOException!");
            e.printStackTrace();
        }
    }

    static void Serialize(University university) {
        Serialize(university, "University.ser");
    }

    static University Deserialize(String filePath) {
        University result = null;

        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            result = (University) objectInputStream.readObject();

            objectInputStream.close();
            fileInputStream.close();
            System.out.println(result.getName() + " deserialized!");

        } catch(IOException e) {
            System.out.println("Deserialization: IOException!");
        } catch(ClassNotFoundException e) {
            System.out.println("Deserialization: ClassNotFoundException!");
            e.printStackTrace();
        }

        return result;
    }

    public static University Deserialize() {
        return Deserialize("University.ser");
    }

    public static void main(String[] args) {
        University DEU = null;
        if (new File("University.ser").exists()) {
            DEU = Deserialize();
        }
        if (DEU == null) DEU = createDefault();


        // TEST HERE
        DEU.update();


        Serialize(DEU);
    }
}
