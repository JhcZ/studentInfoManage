package service;

import model.*;

import java.sql.SQLException;
import java.util.List;

public interface TeacherService {
    //通过id查找teacher
    Teacher findById(String id) throws SQLException;
    //获取登录的teacher
    Teacher getLoginTeacher(String teacherId,String password) throws SQLException;

    //修改密码
    int updatePw(Teacher t,String p);
    //分页模糊查找学生列表
    List<Teacher> get(Teacher condition, int page, int pageSize);
    //获取模糊查询学生的记录数
    int count(Teacher condition);

    //查询开课课程
    List<Course> queryCourse(String teacherId);
    //课程修改申请
    int modCourse(CourseApprovalUpdate course);

    //该课程是否提交过修改申请
    boolean isApply(int courseId);
    //通过id查询课程
    Course findCourseById(int courseId);
    //查询教师的修改课程申请
    List<CourseApprovalUpdate> queryApply(String teacherId);

    //开课申请
    int openCourse(CourseApprovalCache courseApprovalCache);
    //开课申请列表
    List<CourseApprovalCache> queryOpen(String teacherId);
    //查询选课的学生
    List<Score> queryStudentCourse(int courseId);

    List<Score> queryStudScore(int courseId);
    List<Teacher> fuzzQuery(Teacher condition);

    boolean del(int teacherId);
    boolean add(Teacher teacher);
}
