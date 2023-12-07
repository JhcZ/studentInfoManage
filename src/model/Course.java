package model;

public class Course {
    private int id;
    private String courseId;  //课程号
    private String name;  //课程名称
    private String teacher; //授课教师
    private String location;  //上课地点
    private String courseDuration;  //上课周数

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
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

    @Override
    public String toString() {
        return "Course{" +
                //"id" + id + "," +
                "name" + name + "," +
                "courseId" + courseId + "," +
                "teacher" + teacher + "," +
                "location" + location + "," +
                "courseDuration" + courseDuration +
                "}";
    }
}
