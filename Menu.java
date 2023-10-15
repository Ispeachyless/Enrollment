import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Menu extends Main {
    public static void readData(CourseCRUD courseManager, StudentCRUD studentManager)
            throws IOException, IllegalStateException {
        List<Course> course_List = courseManager.getAllCourse();
        System.out.println("\n================== Course List ===================");
        for (Course course : course_List) {
            int qty = getQty(studentManager, course.getCId());
            System.out.println("Course name: | " + course.getCourse() + "\tStudents enrolled: | " + qty);
        }
        // print list of courses and students enrolled in the course
        int count = 0;
        Scanner scan = new Scanner(new FileReader(student_));
        while (scan.hasNextLine()) {
            scan.nextLine();
            count++;
        }
        // counts how many students there are in the list
        System.out.println("================== Student List ==================");
        System.out.println("Total number of students enrolled: | " + (count));
    }

    public static void writeData(CourseCRUD courseManager) throws IOException, ErrorHandler {
        List<Course> courses = courseManager.getAllCourse();
        boolean flag = true;
        System.out.println("\n================== Enroll Student ==================");
        int stId = 1000; // default ID
        Scanner scan = new Scanner(new FileReader(student_));
        while (scan.hasNextLine()) {
            scan.nextLine();
            stId++;
        }
        stId = stId + 1;
        System.out.println("Student ID: | " + (stId));
        scan.close();
        // counts how many students there are in the file then adds it to the default ID
        System.out.print("Enter name: | ");
        name = input.next().trim().toUpperCase();
        if (name.isBlank()) {
            throw new ErrorHandler("Name must have a value. Thank you!");
        }
        System.out.print("Do you want to see the available courses to pick? [Y/N]: ");
        String option = input.next();
        if (option.equalsIgnoreCase("y")) {
            System.out.println("==================================================");
            System.out.println("Available courses to pick");
            for (Course course : courses) {
                System.out.println("Course ID : | " + course.getCId() + "\t Course name: | " + course.getCourse());
            }
            // loop to print all available course ID and course name
            System.out.println(" ");
        } else if (option.equalsIgnoreCase("n")) {
            flag = false;
        } else {
            System.out.println("Invalid input\nTerminating program...");
            System.exit(0);
        }
        System.out.print("Enter Course ID: | ");
        int cId = input.nextInt();
        if (cId == 0) {
            throw new ErrorHandler("Course ID should have a value. Thank you!");
        }
        flag = false;
        for (Course course : courses) {
            if (cId == course.getCId()) {
                System.out.println("The student is enrolled in " + course.getCourse());
                flag = true;
            }
        }
        // Verify if the course ID is present in the file
        if (flag == false) {
            System.out.println("Course is not found! \nThe Student cannot be enrolled!");
            System.out.println("ADMIN");
            System.out.println("[1] Enroll new student");
            System.out.println("[2] Insert new course");
            System.out.print("Enter the number of your choice: ");
            String choice = input.next();
            switch (choice) {
                case "1":
                    writeData(courseManager);
                    break;
                case "2":
                    insertData();
                    break;
                default:
                    System.out.println("Invalid choice");
                    System.out.println("Terminating program...");
                    System.exit(0);
            }
        }

        flag = true;
        while (flag) {
            System.out.print("Enter Subject ID : | ");
            sId = input.nextInt();
            System.out.print("Enter Subject : | ");
            subject = input.next().trim().toUpperCase();
            if (subject.isBlank()) {
                throw new ErrorHandler("Subject must have a value. Thank you!");
            }
            System.out.print("Enter number of units: | ");
            units = input.nextInt();
            if (units > 5) {
                throw new ErrorHandler("Units should not be more than 5. Thank you!");
            }
            if (units == 0) {
                throw new ErrorHandler("Units should have a value. Thank you!");
            }
            createSubject(stId, sId, subject, units);
            System.out.println("===================================================");
            System.out.print("Do you want to add more subjects? [Y/N]: ");
            flag = isContinue(input.next());
            System.out.println("===================================================");
            if (flag == false) {
                System.out.println("Student has successfully enrolled!");
            }
            // loop to insert more subjects
        }
        createStudent(stId, name, cId);
    }

    public static void insertData()
            throws IOException, ErrorHandler {
        System.out.println("================== Add Course ===================");
        System.out.print("Enter course ID: | ");
        int cId = input.nextInt();
        if (cId == 0) {
            throw new ErrorHandler("Course ID name must have a value. Thank you!");
        }
        if (cId < 0) {
            throw new ErrorHandler("Course ID name must not be negative. Thank you!");
        }
        System.out.print("Enter course name: | ");
        String course = input.next().toUpperCase().trim();
        if (course.isBlank()) {
            throw new ErrorHandler("Course name must have a value. Thank you!");
        }
        System.out.print("Enter rate per unit: | ");
        rPerUnit = input.nextDouble();
        if (rPerUnit <= 799) {
            throw new ErrorHandler("The minimum rate per unit is 800. Thank you!");
        }
        createCourse(cId, course, rPerUnit); // sends the user input cId, course, rPerUnit to the createCourse
    }

    public static void updateData(CourseCRUD courseManager) throws IOException {
        List<Course> courses = courseManager.getAllCourse();
        System.out.println("=============== Update Course Name ===============");
        if (courses.isEmpty()) {
            System.out.println("No courses available.");
        } else {
            System.out.println("Available courses:");
            for (Course course : courses) {
                System.out.println("Course ID: " + course.getCId() + "\tCourse Name: " + course.getCourse());
            }
            // prints all available courses

            System.out.print("\nEnter the Course ID to update its name: | ");
            int cId = input.nextInt();
            System.out.print("Enter the new course name: | ");
            String newCourseName = input.next().toUpperCase();

            courseManager.updateCourse(cId, newCourseName);
        }
    }

    public static void searchData() throws IOException {
        List<Student> studentList = studentManager.getAllStudents(); // list to retrieve students from arraylist
        List<Course> courseList = courseManager.getAllCourse(); // list to retrieve course from arraylist
        List<Student> subjectList = subjectManager.getAllSubjects(); // list to retrieve subject from arraylist

        boolean flag = false;
        int tUnit = 0;
        System.out.println("=============== Search Student Data ==============");
        System.out.print("Enter student ID: | ");
        stId = input.nextInt();
        System.out.println("==================================================");
        for (Student student : studentList) {
            if (student.getStId() == stId) {
                System.out.println("Name: | " + student.getName());
                flag = true;
            }
        }
        if (flag == false) {
            System.out.println("Student not found. Try again");
            searchData();
        }
        if (flag == true) {
            System.out.println("================= Subject List ===================");
            for (Student subject : subjectList) {
                if (subject.getStId() == stId) {
                    System.out.println("Subject : | " + subject.getSubject() + "\tUnit : | " + subject.getUnit());
                    tUnit = tUnit + subject.getUnit();
                }
            }
            System.out.println("================== Total Balance =================");
            double totalUnit = tUnit;
            double totalBalance = 0;
            for (Student student : studentList) {
                if (student.getStId() == stId) {
                    for (Course course : courseList) {
                        if (student.getCId() == course.getCId()) {
                            totalBalance = course.getPerUnit() * totalUnit;
                            System.out.println("Total number of units: | " + tUnit);
                            System.out
                                    .println("Rate per unit of " + course.getCourse() + " : | " + course.getPerUnit());
                            System.out.println("Total balance : | " + totalBalance);
                        }
                    }
                }
            }
        }
    }

    public static void createStudent(int stId, String name, int cId)
            throws IOException {
        Student student = new Student();
        student.setStId(stId);
        student.setName(name);
        student.setCId(cId);
        // setters for student

        studentManager.createStudent(student);
        // pass contents of student to CRUD
    }

    public static void createSubject(int stId, int sId, String subject, int units) throws IOException {
        Student student = new Student();
        student.setStId(stId);
        student.setSId(sId);
        student.setSubject(subject);
        student.setUnit(units);
        // setters for subject

        subjectManager.createSubject(student);
        // pass contents of student to CRUD
    }

    public static void createCourse(int cId, String course, double rPerUnit) throws IOException {
        Course courses = new Course();
        courses.setCId(cId);
        courses.setCourse(course);
        courses.setPerUnit(rPerUnit);
        // setters for course

        courseManager.createCourse(courses);
        // pass contents of courses to CRUD
    }

    public static int getQty(StudentCRUD studentManager, int CId) {
        List<Student> student_List = studentManager.getAllStudents();
        int qty = 0;
        for (Student student : student_List) {
            if (student.getCId() == CId) {
                qty++;
            }
        }
        return qty;
        // scans how many students are there in the course
    }
}
