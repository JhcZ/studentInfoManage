package dao;

import model.*;

import java.sql.SQLException;
import java.util.List;

public interface TeacherDao extends SimpleDao<Teacher>{
    //查询教师
    Teacher findById(String id) throws SQLException;
    //修改密码
    int updatePw(Teacher t,String p);

    //查询教师的课程
    List<Course> queryCourse(String teacherId);

    //课程修改申请（向表中插入申请数据）
    int modCourse(CourseApprovalUpdate course);

    //查询该课程是否已经提交申请
    boolean isApply(int courseId);
    //有过修改申请，更新申请表
    int courseApprovalUpdate(CourseApprovalUpdate course);
    //根据id查询课程
    Course findCourseById(int courseId);
    //查询修改课程申请
    List<CourseApprovalUpdate> queryApply(String teacherId);

    //开课申请
    int openCourse(CourseApprovalCache courseApprovalCache);

    //查询开课申请
    List<CourseApprovalCache> queryOpen(String teacherId);

    //查询哪些学生选修了一门课
    List<Student> queryStudentCourse(int courseId);
}
