package model;

public class Score {
    private int id;
    private int courseId;
    private int numOfStu;  //某课程选课人数
    private double maxScore;  //某课程最高成绩
    private double minScore;  //某课程最低成绩
    private double avgScore;  //某课程平均成绩
    private int passed;  //某课程及格人数

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getNumOfStu() {
        return numOfStu;
    }

    public void setNumOfStu(int numOfStu) {
        this.numOfStu = numOfStu;
    }

    public double getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }

    public double getMinScore() {
        return minScore;
    }

    public void setMinScore(int minScore) {
        this.minScore = minScore;
    }

    public double getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(int avgScore) {
        this.avgScore = avgScore;
    }

    public int getPassed() {
        return passed;
    }

    public void setPassed(int passed) {
        this.passed = passed;
    }

    @Override
    public String toString() {
        return "Score{" +
                //"id" + id + "," +
                "courseId" + courseId + "," +
                "numOfStu" + numOfStu + "," +
                "maxScore" + maxScore + "," +
                "minScore" + minScore + "," +
                "avgScore" + avgScore + "," +
                "passed" + passed +
                "}";
    }
}
