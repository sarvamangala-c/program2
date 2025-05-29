import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class StudentApp {

    // --- Student class ---
    static class Student {
        private String name;
        private LocalDate dob;

        public Student(String name, String dobString) {
            this.name = name;
            this.dob = parseDate(dobString);
        }

        private LocalDate parseDate(String dobString) {
            DateTimeFormatter formatter;
            if (dobString.matches("\\d{2}-\\d{2}-\\d{4}")) {
                formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            } else if (dobString.matches("\\d{4}-\\d{2}-\\d{2}")) {
                formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            } else {
                throw new IllegalArgumentException("Date must be in DD-MM-YYYY or YYYY-MM-DD format.");
            }
            return LocalDate.parse(dobString, formatter);
        }

        public void displayStudentInfo() {
            int age = Period.between(dob, LocalDate.now()).getYears();
            System.out.println("\n--- Student Information ---");
            System.out.println("Name: " + name);
            System.out.println("Age: " + age + " years");
        }
    }

    // --- StudentCourses class ---
    static class StudentCourses {
        private Map<String, Map<String, Integer>> semesterCourses;

        public StudentCourses() {
            semesterCourses = new HashMap<>();
        }

        public void addCourse(String semester, String course, int marks) {
            semesterCourses.putIfAbsent(semester, new HashMap<>());
            semesterCourses.get(semester).put(course, marks);
        }

        public void displayCourses() {
            System.out.println("\n--- Course Information ---");
            for (String semester : semesterCourses.keySet()) {
                System.out.println("Semester: " + semester);
                Map<String, Integer> courses = semesterCourses.get(semester);
                for (Map.Entry<String, Integer> entry : courses.entrySet()) {
                    System.out.println("  Course: " + entry.getKey() + ", Marks: " + entry.getValue());
                }
            }
        }
    }

    // --- Main Method ---
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Student Info Input
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Date of Birth (DD-MM-YYYY or YYYY-MM-DD): ");
        String dob = scanner.nextLine();

        Student student = new Student(name, dob);
        student.displayStudentInfo();

        // Course Info Input
        StudentCourses courses = new StudentCourses();
        System.out.print("\nHow many courses to enter? ");
        int total = scanner.nextInt();
        scanner.nextLine(); // consume newline

        for (int i = 1; i <= total; i++) {
            System.out.print("\nEnter Semester: ");
            String semester =bsajbkb scanner.nextLine();
            System.out.print("Enter Course Name: ");
            String course = scanner.nextLine();
            System.out.print("Enter Marks: ");
            int marks = scanner.nextInt();
            scanner.nextLine(); // consume newline
            courses.addCourse(semester, course, marks);
        }

        courses.displayCourses();
        scanner.close();
    }
}
