package model;

public class Score {
    private int id;
    private Student student;
    private Course course;
    private double maxScore;  //某课程最高成绩
    private double minScore;  //某课程最低成绩
    private int passed;  //某课程及格人数
    private int grade;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public double getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(double maxScore) {
        this.maxScore = maxScore;
    }

    public double getMinScore() {
        return minScore;
    }

    public void setMinScore(double minScore) {
        this.minScore = minScore;
    }

    public int getPassed() {
        return passed;
    }

    public void setPassed(int passed) {
        this.passed = passed;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Score{" +
                "id" + id + "," +
                "studentId" + student.getStudentId() + "," +
                "courseId" + course.getCourseId() + "," +
                "grade" + grade + "," +
                "maxScore" + maxScore + "," +
                "minScore" + minScore + "," +
                "passed" + passed +
                "}";
    }
}
