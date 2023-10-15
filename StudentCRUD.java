import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentCRUD {
    File student;

    public StudentCRUD() {
        student = new File("Student.txt");
        try {
            if (!student.exists()) {
                student.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Student> getAllStudents() {
        ArrayList<Student> student_list = new ArrayList<>();
        // arraylist for students

        try {
            Scanner scan = new Scanner(new FileReader(student));
            while (scan.hasNextLine()) {
                try {
                    Student student = new Student();
                    student.setStId(scan.nextInt());
                    student.setName(scan.next());
                    student.setCId(scan.nextInt());
                    student_list.add(student);
                } catch (Exception err) {
                    continue;
                }
            }
            scan.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return student_list;
    }

    public void createStudent(Student student) {
        boolean flag = true;
        List<Student> student_list = getAllStudents();

        for (Student record : student_list) {
            try {
                if (record.getStId() == student.getStId()) {
                    throw new ErrorHandler("Duplicate ID Error");
                }
            } catch (ErrorHandler error) {
                flag = false;
                System.out.println("Error: " + error.getMessage());
            }
        }
        if (flag) {
            try {
                FileWriter wr = new FileWriter("Student.txt", true);
                wr.write(student.getStId() + " ");
                wr.write(student.getName().trim() + " ");
                wr.write(student.getCId() + " ");
                wr.write("\n");
                wr.close();
                // write to file
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
