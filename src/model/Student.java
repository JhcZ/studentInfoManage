package model;

import java.io.Serializable;

public class Student implements Serializable {
    private int id;
    private String name;  //学生姓名
    private String department;  //学生所属学院
    private String phone;  //学生手机号
    private char sex;  //学生性别
    private String major;  //学生所选专业
    private int studentId;  //学号
    private String className;  //学生所在班级
    private StudentStatus status;  //学生状态

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public StudentStatus getStatus() {
        return status;
    }

    public void setStatus(StudentStatus status) {
        this.status = status;
    }
    public void setStatus(String statusName){
        if(statusName == "" || "".equals(statusName)){
            setStatus(StudentStatus.ALL);
            return;
        }
        switch (statusName){
            case "毕业":
                setStatus(StudentStatus.GRADUATION);
                break;
            case"在读":
                setStatus(StudentStatus.NORMAL);
                break;
            case "请假":
                setStatus(StudentStatus.LEAVE);
                break;
            case "休学":
                setStatus(StudentStatus.BREAK);
                break;
            default:
                setStatus(StudentStatus.ALL);
        }
    }

    @Override
    public String toString() {
        return "Student{" +
                //"id" + id + "," +
                "name" + name + "," +
                "studentId" + studentId + "," +
                "department" + department + "," +
                "major" + major + "," +
                "className" + className + "," +
                "sex" + sex + "," +
                "phone" + phone + "," +
                '}';
    }
}
