import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Person {
    private String name;
    private String surname;
    private int age;

    public Person() {}

    public Person(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return name + " " + surname + " " + age;
    }
}

public class CRUDOperations {
    private static final String FILE_NAME = "contacts.txt";
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("CRUD Operations Menu:");
            System.out.println("1 - Add Person");
            System.out.println("2 - View All Persons");
            System.out.println("3 - Update Person");
            System.out.println("4 - Delete Person");
            System.out.println("5 - Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline

            switch (choice) {
                case 1:
                    addPerson();
                    break;
                case 2:
                    viewAllPersons();
                    break;
                case 3:
                    updatePerson();
                    break;
                case 4:
                    deletePerson();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addPerson() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter surname: ");
        String surname = scanner.nextLine();
        System.out.print("Enter age: ");
        int age = scanner.nextInt();
        scanner.nextLine();  // Consume the newline

        Person person = new Person(name, surname, age);
        try (FileWriter writer = new FileWriter(FILE_NAME, true)) {
            writer.write(person.toString() + "\n");
        } catch (IOException e) {
            System.out.println("An error occurred while adding the person.");
            e.printStackTrace();
        }
    }

    private static List<Person> readAllPersons() {
        List<Person> persons = new ArrayList<>();
        try (FileReader reader = new FileReader(FILE_NAME)) {
            StringBuilder content = new StringBuilder();
            int ch;
            while ((ch = reader.read()) != -1) {
                if (ch == '\n') {
                    String line = content.toString();
                    content = new StringBuilder();
                    String[] parts = line.split(" ");
                    if (parts.length == 3) {
                        String name = parts[0];
                        String surname = parts[1];
                        int age = Integer.parseInt(parts[2]);
                        persons.add(new Person(name, surname, age));
                    }
                } else {
                    content.append((char) ch);
                }
            }
            // Handle the last line if it doesn't end with a newline character
            if (content.length() > 0) {
                String line = content.toString();
                String[] parts = line.split(" ");
                if (parts.length == 3) {
                    String name = parts[0];
                    String surname = parts[1];
                    int age = Integer.parseInt(parts[2]);
                    persons.add(new Person(name, surname, age));
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the persons.");
            e.printStackTrace();
        }
        return persons;
    }

    private static void viewAllPersons() {
        List<Person> persons = readAllPersons();
        if (persons.isEmpty()) {
            System.out.println("No persons found.");
        } else {
            for (Person person : persons) {
                System.out.println(person);
            }
        }
    }

    private static void updatePerson() {
        List<Person> persons = readAllPersons();
        System.out.print("Enter the name of the person to update: ");
        String nameToUpdate = scanner.nextLine();

        boolean found = false;
        for (Person person : persons) {
            if (person.getName().equals(nameToUpdate)) {
                found = true;
                System.out.print("Enter new name: ");
                String newName = scanner.nextLine();
                System.out.print("Enter new surname: ");
                String newSurname = scanner.nextLine();
                System.out.print("Enter new age: ");
                int newAge = scanner.nextInt();
                scanner.nextLine();  // Consume the newline

                person.setName(newName);
                person.setSurname(newSurname);
                person.setAge(newAge);
                break;
            }
        }

        if (found) {
            writeAllPersons(persons);
            System.out.println("Person updated successfully.");
        } else {
            System.out.println("Person not found.");
        }
    }

    private static void deletePerson() {
        List<Person> persons = readAllPersons();
        System.out.print("Enter the name of the person to delete: ");
        String nameToDelete = scanner.nextLine();

        boolean found = false;
        Person personToRemove = null;
        for (Person person : persons) {
            if (person.getName().equals(nameToDelete)) {
                found = true;
                personToRemove = person;
                break;
            }
        }

        if (found) {
            persons.remove(personToRemove);
            writeAllPersons(persons);
            System.out.println("Person deleted successfully.");
        } else {
            System.out.println("Person not found.");
        }
    }

    private static void writeAllPersons(List<Person> persons) {
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            for (Person person : persons) {
                writer.write(person.toString() + "\n");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while writing the persons.");
            e.printStackTrace();
        }
    }
}