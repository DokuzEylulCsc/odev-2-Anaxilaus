package Problemset;


import java.io.Serializable;

public class Main {
    public static University Deserialize(String filePath) {
        throw new UnsupportedOperationException();
    }

    public static University Deserialize() {
        return Deserialize("data.ser");

    }

    public static void Serialize(University university, String filePath) {
        throw new UnsupportedOperationException();
    }

    public static void Serialize(University university) {
        Serialize(university, "data.ser");
    }
    public static void main(String[] args) {
        System.out.println("Working Directory = " +
                System.getProperty("user.dir"));
    }
}
