package service;

import model.Course;
import model.CourseApprovalCache;
import model.CourseApprovalUpdate;
import model.Teacher;

import java.sql.SQLException;
import java.util.List;

public interface TeacherService {
    //通过id查找teacher
    Teacher findById(String id) throws SQLException;
    //获取登录的teacher
    Teacher getLoginTeacher(String teacherId,String password) throws SQLException;

    //修改密码
    int updatePw(Teacher t,String p);

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
    List<CourseApprovalCache> queryOpen(String teacherId);
    List<Teacher> fuzzQuery(Teacher condition);
}
