package AttendenceSystem;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Student {
    String name;
    String rollNumber;

    public Student(String name, String rollNumber) {
        this.name = name;
        this.rollNumber = rollNumber;
    }
}

class AttendanceManagementSystem {
    HashMap<String, ArrayList<Boolean>> attendanceRecords;
    ArrayList<Student> students;

    public AttendanceManagementSystem() {
        attendanceRecords = new HashMap<>();
        students = new ArrayList<>();
    }

    public void addStudent(String name) {
        // Generate roll number based on the initial letters of the student's name
        String rollNumber = name.substring(0, 1).toUpperCase() + String.format("%03d", students.size() + 1);

        Student newStudent = new Student(name, rollNumber);
        students.add(newStudent);
        attendanceRecords.put(rollNumber, new ArrayList<>());

        System.out.println("Student added with roll number: " + rollNumber);
    }

    public void markAttendance(String rollNumber, boolean isPresent) {
        if (attendanceRecords.containsKey(rollNumber)) {
            attendanceRecords.get(rollNumber).add(isPresent);
            System.out.println("Attendance marked for student with roll number " + rollNumber);
        } else {
            System.out.println("Invalid roll number");
        }
    }

    public void viewAttendanceReport(String rollNumber) {
        if (attendanceRecords.containsKey(rollNumber)) {
            ArrayList<Boolean> attendanceList = attendanceRecords.get(rollNumber);
            int totalClasses = attendanceList.size();
            int presentClasses = (int) attendanceList.stream().filter(Boolean::booleanValue).count();
            double attendancePercentage = (presentClasses / (double) totalClasses) * 100;

            System.out.println("Attendance Report for Student with Roll Number " + rollNumber);
            System.out.println("Total Classes: " + totalClasses);
            System.out.println("Present Classes: " + presentClasses);
            System.out.println("Attendance Percentage: " + attendancePercentage + "%");
            System.out.println("Remaining Leave Percentage: " + remainingLeavePercentage(rollNumber) + "%");
        } else {
            System.out.println("Invalid roll number");
        }
    }

    public double remainingLeavePercentage(String rollNumber) {
        if (attendanceRecords.containsKey(rollNumber)) {
            ArrayList<Boolean> attendanceList = attendanceRecords.get(rollNumber);
            int totalClasses = attendanceList.size();
            int presentClasses = (int) attendanceList.stream().filter(Boolean::booleanValue).count();
            int remainingClasses = totalClasses - presentClasses;
            double remainingLeavePercentage = (remainingClasses / (double) totalClasses) * 100;

            return remainingLeavePercentage;
        } else {
            return 0.0;
        }
    }

    public void showAllStudentsAttendanceReport() {
        System.out.println("\nAttendance Report for All Students:");
        for (Student student : students) {
            System.out.println("------------------------------------------------------------");
            System.out.println("Student Name: " + student.name);
            System.out.println("Roll Number: " + student.rollNumber);
            viewAttendanceReport(student.rollNumber);
        }
        System.out.println("------------------------------------------------------------");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AttendanceManagementSystem attendanceSystem = new AttendanceManagementSystem();

        while (true) {
            System.out.println("\nAttendance Management System Menu:");
            System.out.println("1. Add Student");
            System.out.println("2. Mark Attendance");
            System.out.println("3. View Attendance Report");
            System.out.println("4. Show All Students Attendance Report");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter student name: ");
                    String name = scanner.nextLine();
                    attendanceSystem.addStudent(name);
                    break;

                case 2:
                    System.out.print("Enter student roll number: ");
                    String rollNumber = scanner.nextLine();
                    System.out.print("Is the student present? (true/false): ");
                    boolean isPresent = scanner.nextBoolean();
                    attendanceSystem.markAttendance(rollNumber, isPresent);
                    break;

                case 3:
                    System.out.print("Enter student roll number: ");
                    rollNumber = scanner.nextLine();
                    attendanceSystem.viewAttendanceReport(rollNumber);
                    break;

                case 4:
                    attendanceSystem.showAllStudentsAttendanceReport();
                    break;

                case 5:
                    System.out.println("Exiting Attendance Management System. Goodbye!");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}
