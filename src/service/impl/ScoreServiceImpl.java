package service.impl;

import dao.ScoreDao;
import dao.impl.ScoreDaoImpl;
import model.Course;
import model.Score;
import service.ScoreService;

import java.util.List;

/**
 * @description:
 * @author: JhcZ
 * @Email：2325947239@qq.com
 * @create: 2023-12-26 15:37
 **/
public class ScoreServiceImpl implements ScoreService {
    ScoreDao scoreDao = new ScoreDaoImpl();

    @Override
    public boolean mod(Score score) {
        return scoreDao.update(score) == 1;
    }

    @Override
    public boolean del(int scoreId) {
        return scoreDao.delete(scoreId) == 1;
    }

    @Override
    public boolean add(Score score) {
        return scoreDao.insert(score) == 1;
    }

    @Override
    public List<Score> getByStudentId(int studentId) {
        return scoreDao.findByStudentId(studentId);
    }

    @Override
    public Score findByStuIdAndCId(int studentId, int courseId) {
        return scoreDao.findBySIdAndCId(studentId,courseId);
    }

    @Override
    public void importScores(List<Score> scores) {
        scoreDao.importScores(scores);
    }

    @Override
    public List<Score> get(Score condition, int page, int pageSize) {
        return scoreDao.query(condition,(page - 1) * pageSize,pageSize);
    }
}