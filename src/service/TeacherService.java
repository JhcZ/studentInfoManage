package service;

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

    List<Teacher> fuzzQuery(Teacher condition);
}
