package dao.impl;

import dao.BaseDao;
import dao.CourseDao;
import model.Course;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDaoImpl extends BaseDao implements CourseDao{
    @Override
    public int getCourseSelectionCount(int courseId) {
        int num = 0;
        String sql = "SELECT COUNT(*) FROM stu_choose WHERE cId=?";
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,courseId);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                num = rs.getInt(1);
            }
        }catch (SQLException e){
            System.out.println("DAO查询选课人数错误：" + sql + "," + e.getMessage());
        }
        return num;
    }

    @Override
    public double getAverageScore(int courseId) {
        double avgScore = 0;
        int counts = getCourseSelectionCount(courseId);
        String sql = "SELECT grade FROM score_table WHERE cId=?";
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,courseId);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                avgScore += rs.getDouble("grade");
            }
        }catch (SQLException e){
            System.out.println("DAO查询平均成绩错误：" + sql + "," + e.getMessage());
        }
        return avgScore / counts;
    }

    @Override
    public double getMaxScore(int courseId) {
        double highestScore = 0;
        String sql = "SELECT MAX(grade) FROM score_table WHERE cId=?";
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,courseId);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                highestScore = rs.getDouble(1);
            }
        }catch (SQLException e){
            System.out.println("DAO查询最高分错误：" + sql + "," + e.getMessage());
        }
        return highestScore;
    }

    @Override
    public double getMinScore(int courseId) {
        double lowestScore = 0;
        String sql = "SELECT MIN(grade) FROM score_table WHERE cId=?";
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,courseId);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                lowestScore = rs.getDouble(1);
            }
        }catch (SQLException e){
            System.out.println("DAO查询最低分错误：" + sql + "," + e.getMessage());
        }
        return lowestScore;
    }

    @Override
    public List<Course> findByStudentId(int studentId) {
        List<Course> courseList = new ArrayList<>();
        Course course = new Course();
        String sql = "SELECT cId " +
                "FROM student_table st " +
                "JOIN stu_choose sc ON st.studentId = sc.stuId " +
                "WHERE sc.stuId = ?";
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,studentId);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                course = findById(rs.getInt(1));
                courseList.add(course);
            }
        }catch (SQLException e){
            System.out.println("DAO通过学号查找课程错误：" + sql + "," + e.getMessage());
        }
        return courseList;
    }

    @Override
    public Course findByTId(int teacherId) {
        Course condition = new Course();
        condition.setTeacherId(teacherId);
        List<Course> courseList = query(condition);
        if(courseList != null && courseList.size() == 1)
            return courseList.get(0);
        return null;
    }

    @Override
    public Course findById(int id) {
        Course condition = new Course();
        condition.setCourseId(id);
        List<Course> courseList = query(condition);
        if(courseList != null && courseList.size() == 1)
            return courseList.get(0);
        return null;
    }

    @Override
    public List<Course> query(Course condition) {
        List<Course> courseList = new ArrayList<>();
        String sql = "SELECT * FROM course_table";
        if(courseList != null){
            sql += " WHERE 1=1";
            if(condition.getCourseId() != 0){
                sql += " AND courseId='" + condition.getCourseId() + "'";
            }
            if(condition.getName() != null && !condition.getName().isEmpty()){
                sql += " AND name LIKE '%" + condition.getName() + "%'";
            }
            if(condition.getTeacherId() != 0){
                sql += "AND teacher='" + condition.getTeacherId() + "'";
            }
            if(condition.getFlag() != null && !condition.getFlag().isEmpty()){
                sql += " AND flag LIKE '%" + condition.getFlag() + "%'";
            }
        }
        sql += " ORDER BY courseId ASC";
        System.out.println("DAO查询课程query(condition)：" + sql);

        try{
            pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                Course course = new Course();
                course.setCourseId(rs.getInt("courseId"));
                course.setName(rs.getString("name"));
                course.setTeacherId(rs.getInt("teacher"));
                course.setLocation(rs.getString("location"));
                course.setCourseDuration(rs.getString("courseDuration"));
                course.setFlag(rs.getString("flag"));
                course.setClassDay(rs.getInt("classDay"));
                course.setClassTime(rs.getString("classTime"));
                course.setStartTime(String.valueOf(rs.getTime("startTime")));
                course.setSemester(rs.getInt("semester"));
                course.setNumOfStu(getCourseSelectionCount(rs.getInt("courseId")));
                courseList.add(course);
            }
        }catch (SQLException e){
            System.out.println("DAO查询课程query(condition)错误：" + sql + "," + e.getMessage());
        }
        return courseList;
    }

    @Override
    public List<Course> query(Course condition, int start, int num) {
        List<Course> courseList = new ArrayList<>();
        String sql = "SELECT * FROM course_table";
        if(courseList != null){
            sql += " WHERE 1=1";
            if(condition.getCourseId() != 0){
                sql += " AND courseId='" + condition.getCourseId() + "'";
            }
            if(condition.getName() != null && !condition.getName().isEmpty()){
                sql += " AND name LIKE '%" + condition.getName() + "%'";
            }
            if(condition.getTeacherId() != 0){
                sql += "AND teacher='" + condition.getTeacherId() + "'";
            }
            if(condition.getFlag() != null && !condition.getFlag().isEmpty()){
                sql += " AND flag LIKE '%" + condition.getFlag() + "%'";
            }
        }
        sql += " ORDER BY courseId ASC LIMIT ?,?";
        System.out.println("DAO查询课程query(condition,start,num)：" + sql);

        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,start);
            pstmt.setInt(2,num);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                Course course = new Course();
                course.setCourseId(rs.getInt("courseId"));
                course.setName(rs.getString("name"));
                course.setTeacherId(rs.getInt("teacher"));
                course.setLocation(rs.getString("location"));
                course.setCourseDuration(rs.getString("courseDuration"));
                course.setFlag(rs.getString("flag"));
                course.setClassDay(rs.getInt("classDay"));
                course.setClassTime(rs.getString("classTime"));
                course.setStartTime(String.valueOf(rs.getTime("startTime")));
                course.setSemester(rs.getInt("semester"));
                course.setNumOfStu(getCourseSelectionCount(rs.getInt("courseId")));
                courseList.add(course);
            }
        }catch (SQLException e){
            System.out.println("DAO查询课程query(condition,start,num)错误：" + sql + "," + e.getMessage());
        }
        return courseList;
    }

    @Override
    public int insert(Course course) {
        int rows = 0;
        String sql = "INSERT INTO course_table(courseId,name,teacher,location,courseDuration,flag,classDay,classTime,startTime,semester,numOfStu)" +
                " VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,course.getCourseId());
            pstmt.setString(2,course.getName());
            pstmt.setInt(3,course.getTeacherId());
            pstmt.setString(4,course.getLocation());
            pstmt.setString(5,course.getCourseDuration());
            pstmt.setString(6,course.getFlag());
            pstmt.setInt(7,course.getClassDay());
            pstmt.setString(8 , course.getClassTime());
            pstmt.setString(9,course.getStartTime());
            pstmt.setInt(10,course.getSemester());
            pstmt.setInt(11,course.getNumOfStu());
            rows = pstmt.executeUpdate();
        }catch (SQLException e){
            System.out.println("DAO添加课程出错：" + sql + "," + e.getMessage());
        }
        return rows;
    }

    @Override
    public int update(Course course) {
        int rows = 0;
        String sql = "UPDATE course_table SET name=?,teacher=?,location=?,courseDuration=?,flag=?,classDay=?,classTime=?,startTime=?,semester=?,numOfStu=? WHERE courseId=?";

        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,course.getName());
            pstmt.setInt(2,course.getTeacherId());
            pstmt.setString(3,course.getLocation());
            pstmt.setString(4,course.getCourseDuration());
            pstmt.setString(5,course.getFlag());
            pstmt.setInt(6,course.getClassDay());
            pstmt.setString(7,course.getClassTime());
            pstmt.setString(8,course.getStartTime());
            pstmt.setInt(9,course.getSemester());
            pstmt.setInt(10,course.getNumOfStu());
            pstmt.setInt(11,course.getCourseId());
        }catch (SQLException e){
            System.out.println("DAO更新课程错误：" + sql + "," + e.getMessage());
        }
        return rows;
    }

    @Override
    public int delete(int id) {
        int rows = 0;
        String sql = "DELETE FROM course_table WHERE courseId=?";

        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,id);
            rows = pstmt.executeUpdate();
        }catch (SQLException e){
            System.out.println("DAO删除课程错误：" + sql + "," + e.getMessage());
        }
        return rows;
    }

    @Override
    public int count() {
        return count(null);
    }

    @Override
    public int count(Course condition) {
        int num = 0;
        String sql = "SELECT count(*) FROM course_table WHERE (1=1)";
        if(condition != null){
            sql += " WHERE 1=1";
            if(condition.getCourseId() != 0){
                sql += " AND courseId='" + condition.getCourseId() + "'";
            }
            if(condition.getName() != null && !condition.getName().isEmpty()){
                sql += " AND name LIKE '%" + condition.getName() + "%'";
            }
            if(condition.getTeacherId() != 0){
                sql += "AND teacher='" + condition.getTeacherId() + "'";
            }
            if(condition.getFlag() != null && !condition.getFlag().isEmpty()){
                sql += " AND flag LIKE '%" + condition.getFlag() + "%'";
            }
        }
        System.out.println("DAO查询课程count(condition)：" + sql);
        try{
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                num = rs.getInt(1);
            }
        }catch (SQLException e){
            System.out.println("DAO根据条件" + condition +
                    "查询课程数量错误：" + sql + "," + e.getMessage()
            );
        }
        return num;
    }
}
