public class Course {
    private int cId;
    private String course;
    private double rPerUnit;

    public void setCourse(String course) {
        this.course = course;
    }

    public String getCourse() {
        return course;
    }

    public void setCId(int cId) {
        this.cId = cId;
    }

    public int getCId() {
        return cId;
    }

    public void setPerUnit(double rPerUnit) {
        this.rPerUnit = rPerUnit;
    }

    public double getPerUnit() {
        return rPerUnit;
    }
}
