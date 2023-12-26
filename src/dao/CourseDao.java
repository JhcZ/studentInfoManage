package dao;

import model.Course;

import java.util.List;

public interface CourseDao extends SimpleDao<Course>{
    //根据教师号查找课程
    Course findByTId(int teacherId);

    //统计选课人数
    int getCourseSelectionCount(int courseId);

    //获取某一课程的平均成绩
    double getAverageScore(int courseId);

    //获取某一课程的最高成绩
    double getMaxScore(int courseId);

    //获取某一课程的最低成绩
    double getMinScore(int courseId);

    //通过学号查找该学生所学课程
    List<Course> findByStudentId(int studentId);
}
