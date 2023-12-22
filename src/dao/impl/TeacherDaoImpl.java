package dao.impl;

import dao.BaseDao;
import dao.CourseDao;
import dao.TeacherDao;
import model.Course;
import model.Teacher;
import util.Encrypt;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherDaoImpl extends BaseDao implements TeacherDao {

    CourseDao courseDao = new CourseDaoImpl();

    @Override
    public Teacher findById(String id){
        Teacher teacher = null;
        String sql = "select * from teacher_table where teacherId = ?";

        try {
            pstmt =  conn.prepareStatement(sql);
            pstmt.setInt(1, Integer.parseInt(id));
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                teacher = new Teacher();
                teacher.setTeacherId(String.valueOf(rs.getInt("teacherId")));
                teacher.setName(rs.getString("name"));
                teacher.setPassword(rs.getString("password"));
                teacher.setDepartment(rs.getString("department"));
                teacher.setDegree(rs.getString("degree"));
                teacher.setSex(rs.getString("sex"));
                teacher.setPhone(rs.getString("phone"));
                teacher.setEmail(rs.getString("email"));
                teacher.setCreateTime(rs.getString("createTime"));
                System.out.println("教师：" + teacher.getName());
            }
        } catch (SQLException e) {
            System.out.println("查询错误");
            e.printStackTrace();
        }
        return teacher;
    }

    @Override
    public int updatePw(Teacher t, String p) {
        String sql = "update teacher_table set password = ? where teacherId=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, Encrypt.toMd5(p));
            pstmt.setInt(2, Integer.parseInt(t.getTeacherId()));
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("修改错误");
            e.printStackTrace();
        }
        return 0;
    }

    //调用coursedao的查询方法，返回根据teacherId查询到的课程
    @Override
    public List<Course> queryCourse(String teacherId) {
        List<Course> courseList = new ArrayList<>();
        String sql = "SELECT * FROM course_table where teacher = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, Integer.parseInt(teacherId));
            ResultSet rs = pstmt.executeQuery();
            System.out.println("查询教师课程");
            while(rs.next()){
                Course course = new Course();
                course.setCourseId(rs.getInt("courseId"));
                course.setName(rs.getString("name"));
                course.setTeacherId(rs.getInt("teacher"));
                course.setLocation(rs.getString("location"));
                course.setCourseDuration(rs.getString("courseDuration"));
                course.setFlag(rs.getString("flag"));
                course.setClasses(rs.getString("classes"));
                course.setStartTime(String.valueOf(rs.getTime("startTime")));
                course.setSemester(rs.getInt("semester"));
                course.setNumOfStu(rs.getInt("courseId"));
                courseList.add(course);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return courseList;
    }


    @Override
    public List<Teacher> query(Teacher condition) {
        List<Teacher> TeacherList = new ArrayList<>();
        String sql = "SELECT * FROM teacher_table";
        if (condition != null) {
            sql += " WHERE 1=1";
            if (condition.getTeacherId() != null && !condition.getTeacherId().isEmpty()) {
                sql += " AND id='" + condition.getTeacherId() + "'";
            }
            if (condition.getName() != null && !condition.getName().isEmpty()) {
                sql += " AND name LIKE '%" + condition.getName() + "%'";
            }
        }
        sql += " ORDER BY teacherId DESC";
        System.out.println("DAO查询find(condition) : " + sql);
        try {
            pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Teacher teacher = new Teacher();
                teacher.setTeacherId(String.valueOf(rs.getInt("teacherId")));
                teacher.setName(rs.getString("name"));
                teacher.setPassword(rs.getString("password"));
                teacher.setDepartment(rs.getString("department"));
                teacher.setDegree(rs.getString("degree"));
                teacher.setSex(rs.getString("sex"));
                teacher.setPhone(rs.getString("phone"));
                teacher.setEmail(rs.getString("email"));
                teacher.setCreateTime(rs.getString("createTime"));
                TeacherList.add(teacher);
            }
        } catch (SQLException e) {
            System.out.println("DAO查询管理员出错：" + sql + "," + e.getMessage());
        }
        return TeacherList;
    }


    @Override
    public Teacher findById(int id) {
        return null;
    }

    // 提供分页查询的dao功能
    @Override
    public List<Teacher> query(Teacher condition, int start, int num) {
        List<Teacher> TeacherList = new ArrayList<>();
        String sql = "SELECT * FROM teacher_table";
        if (condition != null) {
            sql += " WHERE 1=1";
            if (condition.getTeacherId() != null && !condition.getTeacherId().isEmpty()) {
                sql += " AND id='" + condition.getTeacherId() + "'";
            }
            if (condition.getName() != null && !condition.getName().isEmpty()) {
                sql += " AND name LIKE '%" + condition.getName() + "%'";
            }
        }
        sql += " ORDER BY teacherId DESC `LIMIT` ? , ?";
        System.out.println("DAO查询find(condition, start, num) : " + sql);
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, start);
            pstmt.setInt(2, num);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Teacher teacher = new Teacher();
                teacher.setTeacherId(String.valueOf(rs.getInt("teacherId")));
                teacher.setName(rs.getString("name"));
                teacher.setPassword(rs.getString("password"));
                teacher.setDepartment(rs.getString("department"));
                teacher.setDegree(rs.getString("degree"));
                teacher.setSex(rs.getString("sex"));
                teacher.setPhone(rs.getString("phone"));
                teacher.setEmail(rs.getString("email"));
                teacher.setCreateTime(rs.getString("createTime"));
                TeacherList.add(teacher);
            }
        } catch (SQLException e) {
            System.out.println("DAO查询管理员出错：" + sql + "," + e.getMessage());
        }
        return TeacherList;
    }

    @Override
    public int insert(Teacher teacher) {
        return 0;
    }

    @Override
    public int update(Teacher teacher) {
        return 0;
    }

    @Override
    public int delete(int id) {
        return 0;
    }

    @Override
    public int count() {
        return 0;
    }

    @Override
    public int count(Teacher condition) {
        return 0;
    }


    public static void main(String[] args) {
        Teacher teacher = new Teacher();

        System.out.println(new TeacherDaoImpl().query(teacher));
    }
}
