package service.impl;

import dao.TeacherDao;
import dao.impl.TeacherDaoImpl;
import model.Course;
import model.Teacher;
import service.TeacherService;
import util.Encrypt;

import java.sql.SQLException;
import java.util.List;

public class TeacherServiceImpl implements TeacherService {

    TeacherDao teacherDao = new TeacherDaoImpl();
    @Override
    public Teacher findById(String id) throws SQLException {
        return teacherDao.findById(id);
    }

    @Override
    public Teacher getLoginTeacher(String teacherId, String password) throws SQLException {
        if (teacherId == null || teacherId.equals("") || password == null || password.equals("")){
            return null;
        }
        Teacher teacher = teacherDao.findById(teacherId);
        if (teacher != null){
            if (teacher.getPassword().equals(Encrypt.toMd5(password))){
                return teacher;
            }
            System.out.println(getClass().getName() + " :  密码错误");
        }
        return null;
    }

    @Override
    public int updatePw(Teacher t, String p) {
        if (t == null || p == null){
            return 0;
        }
        return teacherDao.updatePw(t,p);
    }

    @Override
    public List<Course> queryCourse(String teacherId) {
        if (teacherId == null || teacherId.equals("")){
            System.out.println("queryCourse : teacherId为空");
            return null;
        }
        return teacherDao.queryCourse(teacherId);
    }
}
