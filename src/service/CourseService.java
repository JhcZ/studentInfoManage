package service;

import model.Course;

public interface CourseService {
    // 根据Id获取Course
    Course getById(int id);
}
