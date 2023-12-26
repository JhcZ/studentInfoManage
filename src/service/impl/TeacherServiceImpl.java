package service.impl;

import dao.TeacherDao;
import dao.impl.TeacherDaoImpl;
import model.Course;
import model.CourseApprovalCache;
import model.CourseApprovalUpdate;
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

    @Override
    public int modCourse(CourseApprovalUpdate course) {
        if (course == null){
            return 0;
        }
        int courseId = course.getCourse().getCourseId();
        if (teacherDao.isApply(courseId)){
            System.out.println("已经有过修改申请,直接覆盖");
            return teacherDao.courseApprovalUpdate(course);

        }
        return teacherDao.modCourse(course);
    }

    @Override
    public boolean isApply(int courseId) {
        return teacherDao.isApply(courseId);
    }

    @Override
    public Course findCourseById(int courseId) {
        if (courseId < 0){
            System.out.println("id非法");
            return null;
        }
        return teacherDao.findCourseById(courseId);
    }

    @Override
    public List<CourseApprovalUpdate> queryApply(String teacherId) {
        if (teacherId == null || teacherId.equals("")){
            return null;
        }
        return teacherDao.queryApply(teacherId);
    }

    @Override
    public int openCourse(CourseApprovalCache courseApprovalCache) {
        if (courseApprovalCache == null){
            return 0;
        }
        return teacherDao.openCourse(courseApprovalCache);

    }


    /**
     * 模糊查询
     * 如果无任何查询信息查找所有对象
     * @param condition 查询对象
     * @return 查询结果列表
     */
    @Override
    public List<Teacher> fuzzQuery(Teacher condition) {
        if(condition == null) {
            return null;
        }
        return teacherDao.query(condition);
    }
}
