package Problemset;

import java.io.*;


public class Main {
    public static void main(String[] args) {

        University DEU = null;
        if (new File("University.ser").exists()) {
            DEU = Deserialize();
        } if (DEU == null) DEU = createDefault();
        // TEST BELOW

        DEU.update();

        Serialize(DEU);
    }

    static void Serialize(University university) {
        Serialize(university, "University.ser");
    }

    /**
     * Serialize an University object.
     *
     * @param university University object
     * @param filePath String
     */
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

    static University Deserialize() {
        return Deserialize("University.ser");
    }

    /**
     * Deserialize an University object from filePath.
     *
     * @param filePath String
     * @return deserialized University object
     * @see University
     */
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

    /**
     * Create a prototype University object.
     * Write Class objects as XML files to CWD.
     *
     * @return prototype University object.
     * @see University
     */
    static University createDefault() {

        try {
            Class BIL2002 = new Class("BIL2002");
            Teacher UE = new Teacher(1, "Ugur Eliiyi");
            BIL2002.addTeacher(UE);
            BIL2002.addTeacher(new Teacher(2, "Alican Dogan"));
            BIL2002.addTeacher(new Teacher(3, "Baris Tezel"));
            BIL2002.addStudent(new Undergraduate(2016280010, "Mert Dede"));
            BIL2002.addStudent(new Undergraduate(2016280042, "Metehan Sivri"));
            BIL2002.addStudent(new Undergraduate(2016280002, "Ulugbey Alp"));
            BIL2002.addStudent(new Undergraduate(2016280037, "Mustafa Firat Yilmaz"));

            Class BILGraduate1001 = new Class("BILG1001");
            BILGraduate1001.addTeacher(UE);
            BILGraduate1001.addStudent(new Graduate(2010000001, "Random Person 1"));
            BILGraduate1001.addStudent(new Graduate(2010000002, "Random Person 2"));

            Department computerScience = new Department("Computer Science");
            computerScience.openClass(BIL2002);
            computerScience.openClass(BILGraduate1001);

            for (Object obj : computerScience) ((Class) obj).exportXml();

            Faculty scienceFaculty = new Faculty("Faculty of Science");
            scienceFaculty.addDepartment(computerScience);

            University DEU = new University("DEU");
            DEU.addFaculty(scienceFaculty);
            DEU.update(false);
            return DEU;

        } catch (UnsupportedOperationException e) {
            System.out.println("Exception while adding or removing elements!");
            e.printStackTrace();

            return null;
        }
    }
}
