package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Course {
    private int courseId;  //课程号
    private String name;  //课程名称

    private Teacher teacher; // 授课教师
    private int teacherId; //授课教师id
    private String location;  //上课地点
    private String courseDuration;  //上课周数
    private String flag;  //课程类别
    private String classes;  //开设班级
    private String startTime;  //课程开始时间
    private int semester;  //课程开设学期

    private int classDay; // 上课日期（周几） 1 2 3 4 5 6 7

    private String classTime; // 上课时间（哪几节课） "1,2,3"表示1，2，3节课

    private int numOfStu;  //选课人数

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCourseDuration() {
        return courseDuration;
    }

    public void setCourseDuration(String courseDuration) {
        this.courseDuration = courseDuration;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public Date getStartDate(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try{
            return format.parse(startTime);
        }catch (ParseException e){
            e.printStackTrace();
            return null;
        }
    }

    public void setDate(Date startTime){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        this.startTime = format.format(startTime);
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public int getNumOfStu() {
        return numOfStu;
    }

    public void setNumOfStu(int numOfStu) {
        this.numOfStu = numOfStu;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId" + courseId + "," +
                "name" + name + "," +
                "flag" + flag + "," +
                //"teacher" + teacher.getTeacherId() + "," +
                "location" + location + "," +
                "courseDuration" + courseDuration + "," +
                "classDay" + classDay + "," +
                "classTime" + classTime + "," +
                "startTime" + startTime + "," +
                "semester" + semester + "," +
                "numOfStu" + numOfStu + "," +
                "}";
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public int getClassDay() {
        return classDay;
    }

    public void setClassDay(int classDay) {
        this.classDay = classDay;
    }

    public String getClassTime() {
        return classTime;
    }

    public void setClassTime(String classTime) {
        this.classTime = classTime;
    }
}
