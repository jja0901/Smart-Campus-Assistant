import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class SmartCampusAssistant {

    // Lists to store data
    private static List<Student> students = new ArrayList<>();
    private static List<Course> courses = new ArrayList<>();
    private static List<Book> books = new ArrayList<>();
    private static List<Event> events = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            displayMainMenu();
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        handleStudentModule(scanner);
                        break;
                    case 2:
                        handleCourseFacultyModule(scanner);
                        break;
                    case 3:
                        handleLibraryModule(scanner);
                        break;
                    case 4:
                        handleUtilityTools(scanner);
                        break;
                    case 5:
                        displayAboutHelp();
                        break;
                    case 6:
                        System.out.println("Exiting Smart Campus Assistant. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 6.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear the invalid input
                choice = 0; // Reset choice to loop again
            }
            System.out.println(); // Add a blank line for readability
        } while (choice != 6);

        scanner.close();
    }

    private static void displayMainMenu() {
        System.out.println("===== Smart Campus Assistant =====");
        System.out.println("1. Student Module");
        System.out.println("2. Course / Faculty Module");
        System.out.println("3. Library Module");
        System.out.println("4. Utility Tools");
        System.out.println("5. About / Help");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }

    // Module Handlers
    private static void handleStudentModule(Scanner scanner) {
        int choice;
        do {
            System.out.println("\n--- Student Module ---");
            System.out.println("1. Add/Search Student Records");
            System.out.println("2. Display All Students");
            System.out.println("3. GPA Calculator");
            System.out.println("4. Back to Main Menu");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addSearchStudent(scanner);
                    break;
                case 2:
                    displayAllStudents();
                    break;
                case 3:
                    calculateGPA(scanner);
                    break;
                case 4:
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 4);
    }

    private static void handleCourseFacultyModule(Scanner scanner) {
        int choice;
        do {
            System.out.println("\n--- Course/Faculty Module ---");
            System.out.println("1. Add New Course");
            System.out.println("2. Search Course");
            System.out.println("3. Display All Courses");
            System.out.println("4. Back to Main Menu");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addNewCourse(scanner);
                    break;
                case 2:
                    searchCourse(scanner);
                    break;
                case 3:
                    displayAllCourses();
                    break;
                case 4:
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 4);
    }

    private static void handleLibraryModule(Scanner scanner) {
        int choice;
        do {
            System.out.println("\n--- Library Module ---");
            System.out.println("1. Add/Search Books");
            System.out.println("2. Issue/Return Books");
            System.out.println("3. Display Available Books");
            System.out.println("4. Back to Main Menu");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addSearchBook(scanner);
                    break;
                case 2:
                    issueReturnBook(scanner);
                    break;
                case 3:
                    displayAvailableBooks();
                    break;
                case 4:
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 4);
    }

    private static void handleUtilityTools(Scanner scanner) {
        int choice;
        do {
            System.out.println("\n--- Utility Tools ---");
            System.out.println("1. Simple Calculator");
            System.out.println("2. Campus Event Scheduler");
            System.out.println("3. Back to Main Menu");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    simpleCalculator(scanner);
                    break;
                case 2:
                    campusEventScheduler(scanner);
                    break;
                case 3:
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 3);
    }

    // Student Module Methods
    private static void addSearchStudent(Scanner scanner) {
        System.out.print("Enter student name or ID to search, or type 'add' to create a new student: ");
        String input = scanner.nextLine();
        if ("add".equalsIgnoreCase(input)) {
            System.out.print("Enter Student ID: ");
            String id = scanner.nextLine();
            System.out.print("Enter Student Name: ");
            String name = scanner.nextLine();
            students.add(new Student(id, name));
            System.out.println("Student added successfully!");
        } else {
            boolean found = false;
            for (Student s : students) {
                if (s.getStudentId().equalsIgnoreCase(input) || s.getName().toLowerCase().contains(input.toLowerCase())) {
                    System.out.println("Found: " + s);
                    found = true;
                }
            }
            if (!found) {
                System.out.println("No student found with that name or ID.");
            }
        }
    }

    private static void displayAllStudents() {
        System.out.println("\n--- All Students ---");
        if (students.isEmpty()) {
            System.out.println("No student records found.");
        } else {
            for (Student s : students) {
                System.out.println(s);
            }
        }
    }

    private static void calculateGPA(Scanner scanner) {
        System.out.print("Enter the number of courses to calculate GPA for: ");
        int numCourses = scanner.nextInt();
        scanner.nextLine();

        double totalPoints = 0;
        int totalCredits = 0;

        for (int i = 0; i < numCourses; i++) {
            System.out.printf("--- Course %d ---\n", i + 1);
            System.out.print("Enter grade (A, B, C, D, F): ");
            String gradeStr = scanner.nextLine().toUpperCase();
            System.out.print("Enter course credits: ");
            int credits = scanner.nextInt();
            scanner.nextLine();

            double gradePoint = 0;
            switch (gradeStr) {
                case "A": gradePoint = 4.0; break;
                case "B": gradePoint = 3.0; break;
                case "C": gradePoint = 2.0; break;
                case "D": gradePoint = 1.0; break;
                case "F": gradePoint = 0.0; break;
                default: System.out.println("Invalid grade entered. Skipping this course."); continue;
            }
            totalPoints += gradePoint * credits;
            totalCredits += credits;
        }

        if (totalCredits > 0) {
            double gpa = totalPoints / totalCredits;
            System.out.printf("Your calculated GPA is: %.2f\n", gpa);
        } else {
            System.out.println("No credits entered. Cannot calculate GPA.");
        }
    }

    // Course/Faculty Module Methods
    private static void addNewCourse(Scanner scanner) {
        System.out.print("Enter Course Code: ");
        String code = scanner.nextLine();
        System.out.print("Enter Course Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Instructor Name: ");
        String instructor = scanner.nextLine();
        courses.add(new Course(code, name, instructor));
        System.out.println("Course added successfully!");
    }

    private static void searchCourse(Scanner scanner) {
        System.out.print("Enter course code or instructor name to search: ");
        String query = scanner.nextLine().toLowerCase();
        boolean found = false;
        for (Course c : courses) {
            if (c.getCourseCode().toLowerCase().contains(query) || c.getInstructor().toLowerCase().contains(query)) {
                System.out.println("Found: " + c);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No course found matching the query.");
        }
    }
    
    private static void displayAllCourses() {
        System.out.println("\n--- All Courses ---");
        if (courses.isEmpty()) {
            System.out.println("No courses found.");
        } else {
            for (Course c : courses) {
                System.out.println(c);
            }
        }
    }

    // Library Module Methods
    private static void addSearchBook(Scanner scanner) {
        System.out.print("Enter book title or author to search, or type 'add' to create a new book: ");
        String input = scanner.nextLine();
        if ("add".equalsIgnoreCase(input)) {
            System.out.print("Enter ISBN: ");
            String isbn = scanner.nextLine();
            System.out.print("Enter Title: ");
            String title = scanner.nextLine();
            System.out.print("Enter Author: ");
            String author = scanner.nextLine();
            books.add(new Book(isbn, title, author));
            System.out.println("Book added successfully!");
        } else {
            boolean found = false;
            for (Book b : books) {
                if (b.getTitle().toLowerCase().contains(input.toLowerCase()) || b.getAuthor().toLowerCase().contains(input.toLowerCase())) {
                    System.out.println("Found: " + b);
                    found = true;
                }
            }
            if (!found) {
                System.out.println("No book found with that title or author.");
            }
        }
    }
    
    private static void issueReturnBook(Scanner scanner) {
        System.out.print("Enter ISBN of the book to issue/return: ");
        String isbn = scanner.nextLine();
        Book foundBook = null;
        for (Book b : books) {
            if (b.getIsbn().equalsIgnoreCase(isbn)) {
                foundBook = b;
                break;
            }
        }

        if (foundBook != null) {
            if (foundBook.isAvailable()) {
                foundBook.setAvailable(false);
                System.out.println("Book '" + foundBook.getTitle() + "' has been issued.");
            } else {
                foundBook.setAvailable(true);
                System.out.println("Book '" + foundBook.getTitle() + "' has been returned.");
            }
        } else {
            System.out.println("No book found with that ISBN.");
        }
    }

    private static void displayAvailableBooks() {
        System.out.println("\n--- Available Books ---");
        boolean anyAvailable = false;
        for (Book b : books) {
            if (b.isAvailable()) {
                System.out.println(b);
                anyAvailable = true;
            }
        }
        if (!anyAvailable) {
            System.out.println("No books are currently available.");
        }
    }
    
    // Utility Tools Methods
    private static void simpleCalculator(Scanner scanner) {
        System.out.print("Enter first number: ");
        double num1 = scanner.nextDouble();
        System.out.print("Enter operator (+, -, *, /): ");
        char operator = scanner.next().charAt(0);
        System.out.print("Enter second number: ");
        double num2 = scanner.nextDouble();
        scanner.nextLine(); // consume newline

        double result;
        switch (operator) {
            case '+':
                result = num1 + num2;
                System.out.println("Result: " + result);
                break;
            case '-':
                result = num1 - num2;
                System.out.println("Result: " + result);
                break;
            case '*':
                result = num1 * num2;
                System.out.println("Result: " + result);
                break;
            case '/':
                if (num2 != 0) {
                    result = num1 / num2;
                    System.out.println("Result: " + result);
                } else {
                    System.out.println("Error: Cannot divide by zero.");
                }
                break;
            default:
                System.out.println("Invalid operator.");
        }
    }

    private static void campusEventScheduler(Scanner scanner) {
        System.out.print("Type 'add' to add an event or 'view' to see all events: ");
        String choice = scanner.nextLine();
        if ("add".equalsIgnoreCase(choice)) {
            System.out.print("Enter event name: ");
            String name = scanner.nextLine();
            System.out.print("Enter event date (e.g., YYYY-MM-DD): ");
            String date = scanner.nextLine();
            events.add(new Event(name, date));
            System.out.println("Event added successfully!");
        } else if ("view".equalsIgnoreCase(choice)) {
            System.out.println("\n--- Campus Events ---");
            if (events.isEmpty()) {
                System.out.println("No events scheduled.");
            } else {
                for (Event e : events) {
                    System.out.println(e);
                }
            }
        } else {
            System.out.println("Invalid choice.");
        }
    }

    // About/Help Method
    private static void displayAboutHelp() {
        System.out.println("\n--- About / Help ---");
        System.out.println("Smart Campus Assistant v1.0");
        System.out.println("This application helps manage student, course, and library records.");
        System.out.println("Navigate through the menus by entering the corresponding number.");
        System.out.println("For assistance, contact the development team.");
    }
}