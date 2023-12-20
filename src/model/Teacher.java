package model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Teacher implements Serializable {
    private String name;
    private String teacherId;
    private String department;
    private String degree;
    private String sex;
    private String phone;
    private String email;
    private Date createTime;
    private TeacherStatus teacherStatus;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TeacherStatus getTeacherStatus() {
        return teacherStatus;
    }

    public void setTeacherStatus(TeacherStatus teacherStatus) {
        this.teacherStatus = teacherStatus;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setCreateTime(String createTime) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            setCreateTime(format.parse(createTime));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "name" + name + "," +
                "sex" + sex + "," +
                "teacherId" + teacherId + "," +
                "department" + department + "," +
                "degree" + degree + "," +
                "phone" + phone + "," +
                "email" + email + "," +
                "createTime" + createTime +
                "}";
    }
}
