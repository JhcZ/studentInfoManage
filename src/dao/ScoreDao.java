package dao;

import dao.impl.StudentDaoImpl;
import model.Score;

import java.util.List;

/**
 * @description:
 * @author: JhcZ
 * @Email：2325947239@qq.com
 * @create: 2023-12-26 10:04
 **/
public interface ScoreDao extends SimpleDao<Score>{
    //通过学号查找对应课程成绩
    List<Score> findByStudentId(int studentId);
    //通过学号和课程号查找对应课程成绩
    Score findBySIdAndCId(int studentId,int courseId);
    //通过表单控件批量导入成绩
    void importScores(List<Score> scores);
}