package dao.impl;

import dao.BaseDao;
import dao.ScoreDao;
import model.Course;
import model.Score;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: JhcZ
 * @Email：2325947239@qq.com
 * @create: 2023-12-26 10:41
 **/
public class ScoreDaoImpl extends BaseDao implements ScoreDao {
    @Override
    public List<Score> findByStudentId(int studentId) {
        List<Score> scoreList = new ArrayList<>();
        Score score = null;
        String sql = "SELECT sc.id FROM score_table sc " +
                "JOIN stu_choose sco ON " +
                "sc.sId = sco.stuId AND sc.cId = sco.cId " +
                "WHERE sco.stuId=?";
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,studentId);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                score = findById(rs.getInt(1));
                scoreList.add(score);
            }
        }catch (SQLException e){
            System.out.println("DAO通过学号查找课程分数错误：" + sql + "," + e.getMessage());
        }
        return scoreList;
    }


    @Override
    public Score findBySIdAndCId(int studentId, int courseId) {
        Score score = null;
        String sql = "SELECT sc.id FROM score_table sc " +
                "JOIN stu_choose sco ON " +
                "sc.sId = sco.stuId AND sc.cId = sco.cId " +
                "WHERE sco.stuId=? AND sco.cId=?";
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,studentId);
            pstmt.setInt(2,courseId);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                score = findById(rs.getInt(1));
            }
        }catch (SQLException e){
            System.out.println("DAO通过学号和课程号查找课程分数错误：" + sql + "," + e.getMessage());
        }
        return score;
    }

    @Override
    public void importScores(List<Score> scores) {
        String sql = "INSERT INTO score_table(sId,cId,grade)" +
                " VALUES(?,?,?)";
        try {
            pstmt = conn.prepareStatement(sql);
            for(Score score : scores){
                pstmt.setInt(1, score.getStudentId());
                pstmt.setInt(2, score.getCourseId());
                pstmt.setDouble(3, score.getGrade());
                pstmt.addBatch();
            }
            pstmt.executeBatch();
        }catch (SQLException e){
            System.out.println("DAO批量导入成绩错误：" + sql + "," + e.getMessage());
        }
    }

    @Override
    public Score findById(int id) {
        Score condition = new Score();
        condition.setId(id);
        List<Score> scoreList = query(condition);
        if(scoreList != null && scoreList.size() == 1){
            return scoreList.get(0);
        }
        return null;
    }

    @Override
    public List<Score> query(Score condition) {
        List<Score> scoreList = new ArrayList<>();
        String sql = "SELECT * FROM score_table";
        if(scoreList != null){
            sql += " WHERE 1=1";
            if(condition.getId() != 0){
                sql += " AND Id='" + condition.getId() + "'";
            }
            if(condition.getStudentId() != 0){
                sql += " AND sId='" + condition.getStudentId() + "'";
            }
            if(condition.getCourseId() != 0){
                sql += " AND cId='" + condition.getCourseId() + "'";
            }
        }
        sql += " ORDER BY id ASC";
        System.out.println("DAO查询课程分数query(condition)：" + sql);
        try{
            pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                Score score = new Score();
                score.setId(rs.getInt("id"));
                score.setStudentId(rs.getInt("sId"));
                score.setCourseId(rs.getInt("cId"));
                score.setMaxScore(rs.getDouble("maxScore"));
                score.setMinScore(rs.getDouble("minScore"));
                score.setPassed(rs.getInt("passed"));
                score.setGrade(rs.getDouble("grade"));
                scoreList.add(score);
            }
        }catch (SQLException e){
            System.out.println("DAO查询课程分数query(condition)错误：" + sql + "," + e.getMessage());
        }
        return scoreList;
    }

    @Override
    public List<Score> query(Score condition, int start, int num) {
        List<Score> scoreList = new ArrayList<>();
        String sql = "SELECT * FROM score_table";
        if(scoreList != null){
            sql += " WHERE 1=1";
            if(condition.getId() != 0){
                sql += " AND Id='" + condition.getId() + "'";
            }
            if(condition.getStudentId() != 0){
                sql += " AND sId='" + condition.getStudentId() + "'";
            }
            if(condition.getCourseId() != 0){
                sql += " AND cId='" + condition.getCourseId() + "'";
            }
        }
        sql += " ORDER BY id ASC LIMIT ?,?";
        System.out.println("DAO查询课程分数query(condition,start,num)：" + sql);
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,start);
            pstmt.setInt(2,num);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                Score score = new Score();
                score.setId(rs.getInt("id"));
                score.setStudentId(rs.getInt("sId"));
                score.setCourseId(rs.getInt("cId"));
                score.setMaxScore(rs.getDouble("maxScore"));
                score.setMinScore(rs.getDouble("minScore"));
                score.setPassed(rs.getInt("passed"));
                score.setGrade(rs.getDouble("grade"));
                scoreList.add(score);
            }
        }catch (SQLException e){
            System.out.println("DAO查询课程分数query(condition)错误：" + sql + "," + e.getMessage());
        }
        return scoreList;
    }

    @Override
    public int insert(Score score) {
        int rows = 0;
        String sql = "INSERT INTO score_table(sId,cId,maxScore,minScore,passed,grade)" +
                " VALUES(?,?,?,?,?,?)";
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,score.getStudentId());
            pstmt.setInt(2,score.getCourseId());
            pstmt.setDouble(3,score.getMaxScore());
            pstmt.setDouble(4,score.getMinScore());
            pstmt.setInt(5,score.getPassed());
            pstmt.setDouble(6,score.getGrade());
            rows = pstmt.executeUpdate();
        }catch (SQLException e){
            System.out.println("DAO添加课程分数错误：" + sql + "," + e.getMessage());
        }
        return rows;
    }

    @Override
    public int update(Score score) {
        int rows = 0;
        String sql = "UPDATE score_table SET id=?,sId=?,cId=?,maxScore=?,minScore=?,passed=?,grade=?";
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,score.getId());
            pstmt.setInt(2,score.getStudentId());
            pstmt.setInt(3,score.getCourseId());
            pstmt.setDouble(4,score.getMaxScore());
            pstmt.setDouble(5,score.getMinScore());
            pstmt.setInt(6,score.getPassed());
            pstmt.setDouble(7,score.getGrade());
            rows = pstmt.executeUpdate();
        }catch (SQLException e){
            System.out.println("DAO更新课程分数错误：" + sql + "," + e.getMessage());
        }
        return rows;
    }

    @Override
    public int delete(int id) {
        int rows = 0;
        String sql = "DELETE FROM score_table WHERE id=?";

        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,id);
            rows = pstmt.executeUpdate();
        }catch (SQLException e){
            System.out.println("DAO删除课程分数错误：" + sql + "," + e.getMessage());
        }
        return rows;
    }

    @Override
    public int count() {
        return count(null);
    }

    @Override
    public int count(Score condition) {
        int num = 0;
        String sql = "SELECT COUNT(*) FROM score_table WHERE (1=1)";
        if(condition != null){
            sql += " WHERE 1=1";
            if(condition.getId() != 0){
                sql += " AND Id='" + condition.getId() + "'";
            }
            if(condition.getStudentId() != 0){
                sql += " AND sId='" + condition.getStudentId() + "'";
            }
            if(condition.getCourseId() != 0){
                sql += " AND cId='" + condition.getCourseId() + "'";
            }
        }
        sql += " ORDER BY id ASC";
        System.out.println("DAO查询课程分数query(condition)：" + sql);
        try{
            pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                num = rs.getInt(1);
            }
        }catch (SQLException e){
            System.out.println("DAO根据条件" + condition +
                    "查询课程分数数量错误：" + sql + "," + e.getMessage()
            );
        }
        return num;
    }
}