package service.impl;

import dao.CourseDao;
import dao.impl.CourseDaoImpl;
import model.Course;
import model.Student;
import model.Teacher;
import service.CourseService;

import java.util.List;

public class CourseServiceImpl implements CourseService {
    CourseDao courseDao = new CourseDaoImpl();
    @Override
    public Course get(int courseId) {
        return courseDao.findById(courseId);
    }

    @Override
    public List<Course> get(Course condition, int page, int pageSize) {
        return courseDao.query(condition,(page -1) * pageSize,pageSize);
    }

    @Override
    public int count(Course condition) {
        return courseDao.count(condition);
    }

    @Override
    public boolean mod(Course course) {
        return courseDao.update(course) == 1;
    }

    @Override
    public boolean del(int courseId) {
        return courseDao.delete(courseId) == 1;
    }

    @Override
    public boolean add(Course course) {
        return courseDao.insert(course) == 1;
    }

    @Override
    public List<Course> getByStudentId(int studentId) {
        return courseDao.findByStudentId(studentId);
    }

    @Override
    public int addCourse(Course course) {
        return courseDao.insert(course);
    }
}
