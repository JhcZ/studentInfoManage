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

    int addCourse(Course course);

    List<Course> getByStudentId(int studentId);
}
