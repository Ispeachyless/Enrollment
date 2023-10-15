import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CourseCRUD extends Main {
    File courses;

    public CourseCRUD() {
        courses = new File("Course.txt");
        try {
            if (!courses.exists()) {
                courses.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Course> getAllCourse() {
        ArrayList<Course> course_list = new ArrayList<>();

        try {
            Scanner scan = new Scanner(new FileReader(courses));
            while (scan.hasNextLine()) {
                try {
                    Course course = new Course();
                    course.setCId(scan.nextInt());
                    course.setCourse(scan.next());
                    course.setPerUnit(scan.nextDouble());
                    course_list.add(course);
                } catch (Exception err) {
                    continue;
                }
            }
            scan.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return course_list;
    }

    public void createCourse(Course course) {
        boolean flag = true;
        List<Course> course_list = getAllCourse();

        for (Course record : course_list) {
            try {
                if (record.getCId() == course.getCId()) {
                    throw new ErrorHandler("Duplicate ID Error");
                }
            } catch (ErrorHandler error) {
                flag = false;
                System.out.println("Error: " + error.getMessage());
            }
        }

        if (flag) {
            try {
                FileWriter cs = new FileWriter("Course.txt", true);
                cs.write(course.getCId() + " ");
                cs.write(course.getCourse().trim() + " ");
                cs.write(course.getPerUnit() + " ");
                cs.write("\n");
                cs.close();
                // write to course file
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateCourse(int cId, String newCourseName) throws IOException {
        List<Course> courses = getAllCourse();
        boolean updated = false;
        for (Course course : courses) {
            if (course.getCId() == cId) {
                course.setCourse(newCourseName);
                updated = true;
                break;
            }
        }

        if (updated) {
            FileWriter writer = new FileWriter("Course.txt");
            for (Course course : courses) {
                writer.write(course.getCId() + " ");
                writer.write(course.getCourse() + " ");
                writer.write(course.getPerUnit() + "\n");
            }
            writer.close();
            System.out.println("Course name updated successfully!");
        } else {
            System.out.println("No course found with the given ID.");
        }
    }
}
