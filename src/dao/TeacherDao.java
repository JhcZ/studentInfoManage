package dao;

import model.Course;
import model.Teacher;

import java.sql.SQLException;
import java.util.List;

public interface TeacherDao extends SimpleDao<Teacher>{
    //查询教师
    Teacher findById(String id) throws SQLException;
    //修改密码
    int updatePw(Teacher t,String p);

    //查询教师的课程
    List<Course> queryCourse(String teacherId);
}
