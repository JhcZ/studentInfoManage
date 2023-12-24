package service;

import model.Course;
import model.Student;
import model.Teacher;

import java.util.List;

public interface CourseService {
    Course get(int courseId);

    List<Course> get(Course condition, int page, int pageSize);

    int count(Course condition);

    boolean mod(Course course);

    boolean del(int courseId);

    boolean add(Course course);

    List<Student> get(Student condition, int page, int pageSize);

    List<Teacher> get(Teacher condition, int page, int pageSize);

    // 根据Id获取Course
    Course getById(int id);

    int addCourse(Course course);
}
