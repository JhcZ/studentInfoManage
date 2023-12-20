package service;

import model.*;

import java.util.List;

public interface CourseService {
    //查找指定id的课程
    Course get(int courseId);
    //分页模糊查找课程列表
    List<Course> get(Course condition, int page, int pageSize);
    //获取模糊查询课程的记录数
    int count(Course condition);
    //修改课程信息
    boolean mod(Course course);
    //删除课程
    boolean del(int courseId);
    //增加课程
    boolean add(Course course);
    //获取上某一课程的学生的列表
    List<Student> get(Student condition,int page,int pageSize);
    //获取执教某一课程的教师的列表
    List<Teacher> get(Teacher condition,int page,int pageSize);
}
