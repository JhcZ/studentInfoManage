package model;

import java.util.Date;

public class Score {
    private int id;
    private int studentId;
    private int courseId;
    private double maxScore;  //某课程最高成绩
    private double minScore;  //某课程最低成绩
    private int passed;  //某课程及格人数
    private double grade;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
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

    public Double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Score{" +
                "id：" + id + "," +
                "studentId：" + studentId + "," +
                "courseId：" + courseId + "," +
                "grade：" + grade + "," +
                "maxScore：" + maxScore + "," +
                "minScore：" + minScore + "," +
                "passed：" + passed +
                "}";
    }
}
