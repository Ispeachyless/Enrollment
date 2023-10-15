import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static Scanner input = new Scanner(System.in); // scanner
    public static Menu menu; // contents of file
    public static CourseCRUD courseManager; // crud for courses
    public static SubjectCRUD subjectManager; // crud for subjects
    public static StudentCRUD studentManager; // crud for students
    // CRUD

    public static File course_ = new File("Course.txt"); // file for course
    public static File student_ = new File("Student.txt"); // file for student
    public static File subject_ = new File("Subject.txt"); // file for subject
    // Files

    public static String subject, name, course; // subject_name, student_name, course_name
    public static int units, stId, sId; // units, student_id, subject_id
    public static double rPerUnit; // rate per unit
    // Variable declaration

    public static void main(String[] args) throws IOException, ErrorHandler {
        menu = new Menu(); // use to call Menu
        courseManager = new CourseCRUD(); // use to call CourseCRUD
        subjectManager = new SubjectCRUD(); // use to call SubjectCRUD
        studentManager = new StudentCRUD(); // use to call StudentCRUD
        System.out.println("====================================================");
        displayMenu(); // displays the admin menu
    }

    public static void displayMenu() throws IOException, ErrorHandler, InputMismatchException {
        boolean flag = true;
        while (flag) {
            System.out.println("ADMIN");
            System.out.println("[1] View list of courses and students enrolled"); // 2
            System.out.println("[2] Enroll new student"); // 3
            System.out.println("[3] Insert student course"); // 1
            System.out.println("[4] Update course name"); // 5
            System.out.println("[5] Search student information"); // 4
            System.out.println("[6] Exit");
            System.out.print("Enter the number of your choice: ");
            String choice = input.next(); // string so the user can input a word
            switch (choice) {
                case "1":
                    Menu.readData(courseManager, studentManager);
                    break;
                case "2":
                    Menu.writeData(courseManager);
                    break;
                case "3":
                    Menu.insertData();
                    break;
                case "4":
                    Menu.updateData(courseManager);
                    break;
                case "5":
                    Menu.searchData();
                    break;
                case "6":
                    System.exit(0); // immediately exits the program
                default:
                    System.out.println("Invalid choice"); // if the choice is not valid
            }
            System.out.println("==================================================");
            System.out.print("Do you want to Continue? [Y/N]: "); // loop for displayMenu()
            flag = isContinue(input.next());
            System.out.println("==================================================");
        }
    }
    // End of Menu

    public static boolean isContinue(String choice) {
        if (choice.equalsIgnoreCase("y"))
            return true;
        else
            return false;
    }
    // Loop for continue

}