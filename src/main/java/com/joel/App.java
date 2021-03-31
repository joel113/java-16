package com.joel;

import java.io.*;

// Java 16 introduces records
record Person(String name, int age) implements Serializable {
}

// Java 16 introduces sealed interfaces
// The compilation process creates a full-blown class out of it
//sealed interface Move permits Athlete, Jump, Kick {
//}
//
//final class Athlete implements Move {
//}
//
//final class Jump implements Move {
//}
//
//final class Kick implements Move {
//}

// Records allow generics
//record Parcel<T>(T contents, double length, double breadth, double height, double weight) {
//}

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) {
        Person person = new Person("Johannes", 38);
        writeToFile(person, "person.txt");
        Person person2 = (Person)readFromFile("person.txt");
        System.out.println(String.format("Person %s is %d years old.", person2.name(), person2.age()));
    }

    static void writeToFile(Object obj, String path) {
        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(path))) {
            oos.writeObject(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static Object readFromFile(String path) {
        Object result = null;
        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream(path))) {
            result = ois.readObject();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
