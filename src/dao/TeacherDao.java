package dao;

import model.Teacher;

import java.sql.SQLException;

public interface TeacherDao extends SimpleDao<Teacher>{
    //查询教师
    Teacher findById(String id) throws SQLException;
    //修改密码
    int updatePw(Teacher t,String p);
}
