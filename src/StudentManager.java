import java.io.*;
import java.util.*;

public class StudentManager {
    private static final String CSV_FILE_PATH = "students.csv"; // File containing student records
    private static List<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        loadStudentData();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nStudent Record Management");
            System.out.println("1. Search Student");
            System.out.println("2. Update Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Exit");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    searchStudent(scanner);
                    break;
                case 2:
                    updateStudent(scanner);
                    break;
                case 3:
                    deleteStudent(scanner);
                    break;
                case 4:
                    displayAllStudents();
                    break;
                case 5:
                    saveStudentData();
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option, try again.");
            }
        }
    }

    // Method to load student data from the CSV file
    private static void loadStudentData() {
        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                String name = data[0];
                int rollNumber = Integer.parseInt(data[1]);
                int marks = Integer.parseInt(data[2]);

                students.add(new Student(name, rollNumber, marks));
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    // Method to save the updated student data back to the CSV file
    private static void saveStudentData() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(CSV_FILE_PATH))) {
            for (Student student : students) {
                bw.write(student.getName() + "," + student.getRollNumber() + "," + student.getMarks());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    // Method to search a student by roll number
    private static void searchStudent(Scanner scanner) {
        System.out.print("Enter Roll Number to search: ");
        int rollNumber = scanner.nextInt();

        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                System.out.println("Student found: " + student);
                return;
            }
        }
        System.out.println("Student with Roll Number " + rollNumber + " not found.");
    }

    // Method to update a student's details
    private static void updateStudent(Scanner scanner) {
        System.out.print("Enter Roll Number to update: ");
        int rollNumber = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                System.out.print("Enter new name: ");
                String newName = scanner.nextLine();
                System.out.print("Enter new marks: ");
                int newMarks = scanner.nextInt();

                student.setName(newName);
                student.setMarks(newMarks);
                System.out.println("Student record updated: " + student);
                return;
            }
        }
        System.out.println("Student with Roll Number " + rollNumber + " not found.");
    }

    // Method to delete a student record
    private static void deleteStudent(Scanner scanner) {
        System.out.print("Enter Roll Number to delete: ");
        int rollNumber = scanner.nextInt();

        Iterator<Student> iterator = students.iterator();
        while (iterator.hasNext()) {
            Student student = iterator.next();
            if (student.getRollNumber() == rollNumber) {
                iterator.remove();
                System.out.println("Student with Roll Number " + rollNumber + " deleted.");
                return;
            }
        }
        System.out.println("Student with Roll Number " + rollNumber + " not found.");
    }

    // Method to display all students
    private static void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No student records available.");
        } else {
            System.out.println("All Student Records:");
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }
}

