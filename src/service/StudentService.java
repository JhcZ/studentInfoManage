package service;

import model.*;

import java.util.List;

public interface StudentService {
    //查找指定id的学生
    Student get(int id);
    //获取登录的学生
    Student check(User user);
    //分页模糊查找学生列表
    List<Student> get(Student condition, int page, int pageSize);
    //获取模糊查询学生的记录数
    int count(Student condition);
    //修改学生信息
    boolean mod(Student student);
    //重置学生登录密码
    boolean resetPwd(int studentId);
    //修改学生状态
    boolean modStatus(int studentId, StudentStatus status);
    //检查学生状态
    boolean checkStatus(Student student);
    //添加学生
    boolean add(Student student);
    //删除学生
    boolean del(int studentId);
    //修改密码
    boolean modPwd(int studentId,String newPwd);
}
