package service;

import model.Score;

import java.util.List;

/**
 * @description:
 * @author: JhcZ
 * @Email：2325947239@qq.com
 * @create: 2023-12-26 15:37
 **/
public interface ScoreService {
    boolean mod(Score score);
    boolean del(int scoreId);
    boolean add(Score score);

    //通过学号获取学生的所有课程成绩
    List<Score> getByStudentId(int studentId);
    //通过学号和课程号获取某一课程的成绩
    Score findByStuIdAndCId(int studentId,int courseId);
    //批量导入学生成绩
    void importScores(List<Score> scores);
    //分页查询得分
    List<Score> get(Score condition, int page, int pageSize);
}