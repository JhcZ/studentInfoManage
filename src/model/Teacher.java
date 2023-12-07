package model;

import java.io.Serializable;
import java.util.Date;

public class Teacher implements Serializable {
    private int id;
    private String name;
    private int teacherId;
    private String department;
    private String degree;
    private char sex;
    private String phone;
    private String email;
    private long createTime;

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

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
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

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
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

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public void setCreateTime(Date createTime) {
        setCreateTime(createTime.getTime());
    }

    @Override
    public String toString() {
        return "Teacher{" +
                //"id=" + id + "," +
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
