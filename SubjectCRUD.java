import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SubjectCRUD {
    File student;

    public SubjectCRUD() {
        student = new File("Subject.txt");
        try {
            if (!student.exists()) {
                student.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Student> getAllSubjects() {
        ArrayList<Student> subject_list = new ArrayList<>();

        try {
            Scanner scan = new Scanner(new FileReader("Subject.txt"));
            while (scan.hasNextLine()) {
                try {
                    Student student = new Student();
                    student.setStId(scan.nextInt());
                    student.setSubject(scan.next());
                    student.setUnit(scan.nextInt());
                    subject_list.add(student);
                } catch (Exception e) {
                    continue;
                }
            }
            scan.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return subject_list;
    }

    public void createSubject(Student subject) {
        boolean flag = true;
        List<Student> subject_list = getAllSubjects();

        for (Student record : subject_list) {
            try {
                if (record.getSId() == subject.getSId()) {
                    throw new ErrorHandler("Duplicate ID Error");
                }
            } catch (ErrorHandler error) {
                flag = false;
                System.out.println("Error: " + error.getMessage());
            }
        }
        if (flag) {
            try {
                FileWriter wr = new FileWriter("Subject.txt", true);
                wr.write(subject.getStId() + " ");
                wr.write(subject.getSubject().trim() + " ");
                wr.write(subject.getUnit() + " ");
                wr.write("\n");
                wr.close();
                // write to file
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}