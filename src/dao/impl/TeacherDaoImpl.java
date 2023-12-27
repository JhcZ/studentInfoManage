package dao.impl;

import dao.BaseDao;
import dao.CourseDao;
import dao.TeacherDao;
import model.*;
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

    //查询教师的课程
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
                course.setClassDay(Integer.parseInt(rs.getString("classDay")));
                course.setClassTime(rs.getString("classTime"));
                course.setStartTime(rs.getString("startTime"));
                System.out.println(rs.getString("startTime"));
                course.setSemester(rs.getInt("semester"));
                course.setNumOfStu(rs.getInt("numOfStu"));
                courseList.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return courseList;
    }


    @Override
    public int modCourse(CourseApprovalUpdate course) {
        String sql = "INSERT INTO application_course_cache (courseId, name, teacher, location, " +
                "courseDuration, flag, classDay,classTime,startTime, semester, numOfStu, approval) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, course.getCourse().getCourseId());
            pstmt.setString(2, course.getCourse().getName());
            pstmt.setInt(3, course.getCourse().getTeacherId());
            pstmt.setString(4, course.getCourse().getLocation());
            pstmt.setString(5, course.getCourse().getCourseDuration());
            pstmt.setString(6, course.getCourse().getFlag());
            pstmt.setString(9, course.getCourse().getStartTime());
            pstmt.setInt(7,course.getCourse().getClassDay());
            pstmt.setString(8,course.getCourse().getClassTime());
            pstmt.setInt(10, course.getCourse().getSemester());
            pstmt.setInt(11, course.getCourse().getNumOfStu());
            pstmt.setInt(12, course.getApproval());
            // 执行插入操作
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("插入成功！");
                return 1;
            } else {
                System.out.println("插入失败！");
                return 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public boolean isApply(int courseId) {
        String sql = "SELECT courseId FROM application_course_cache where courseId = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,courseId);
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()){
                int rs = resultSet.getInt("courseId");
                return rs == courseId;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("该课程目前没有修改申请提交过");
        return false;
    }

    @Override
    public int courseApprovalUpdate(CourseApprovalUpdate course) {
        String sql = "UPDATE application_course_cache " +
                "SET name = ?, teacher = teacher, location = ?, courseDuration = ?, " +
                "flag = flag, classes = classes, classDay = ?,classTime = ?,startTime = ?, semester = ?, " +
                "numOfStu = numOfStu, approval = approval " +
                "WHERE courseId = ?";

        // 创建PreparedStatement对象
        try {
            pstmt = conn.prepareStatement(sql);
            Course course1 = course.getCourse();
            // 设置参数
            pstmt.setString(1, course1.getName());
            pstmt.setString(2, course1.getLocation());
            pstmt.setString(3, course1.getCourseDuration());
            pstmt.setInt(4,course1.getClassDay());
            pstmt.setString(5,course1.getClassTime());
            pstmt.setString(6, course1.getStartTime());
            pstmt.setInt(7, course1.getSemester());
            pstmt.setInt(8, course1.getCourseId());

            // 执行更新操作
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("更新修改申请成功！");
            } else {
                System.out.println("更新失败！找不到匹配的记录。");
            }
            return rowsAffected;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Course findCourseById(int courseId) {
        Course course = new Course();
        String sql = "SELECT * FROM course_table where courseId = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,courseId);
            ResultSet rs = pstmt.executeQuery();
            System.out.println("查询课程 :" + courseId);
            while (rs.next()){
                course.setCourseId(courseId);
                course.setName(rs.getString("name"));
                course.setTeacherId(rs.getInt("teacher"));
                course.setLocation(rs.getString("location"));
                course.setCourseDuration(rs.getString("courseDuration"));
                course.setFlag(rs.getString("flag"));
                course.setClassDay(Integer.parseInt(rs.getString("classDay")));
                course.setClassTime(rs.getString("classTime"));
                course.setStartTime(rs.getString("startTime"));
                course.setSemester(rs.getInt("semester"));
                course.setNumOfStu(rs.getInt("numOfStu"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return course;
    }

    @Override
    public List<CourseApprovalUpdate> queryApply(String teacherId) {
        List<CourseApprovalUpdate> list = new ArrayList<>();
        String sql = "SELECT * FROM application_course_cache WHERE teacher = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,teacherId);
            // 执行查询操作
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                CourseApprovalUpdate update = new CourseApprovalUpdate();
                Course course = new Course();
                course.setCourseId(resultSet.getInt("courseId"));
                course.setName(resultSet.getString("name"));
                course.setTeacherId(Integer.parseInt(teacherId));
                course.setLocation(resultSet.getString("location"));
                course.setCourseDuration(resultSet.getString("courseDuration"));
                course.setClassDay(resultSet.getInt("classDay"));
                course.setClassTime(resultSet.getString("classTime"));
                course.setStartTime(resultSet.getString("startTime"));
                course.setSemester(resultSet.getInt("semester"));
                update.setCourse(course);
                update.setApproval(resultSet.getInt("approval"));
                list.add(update);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int openCourse(CourseApprovalCache courseApprovalCache) {
        String sql = "INSERT INTO course_application (kind, tId, cName,approval) VALUES (?, ?, ?, ?)";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,courseApprovalCache.getKind());
            pstmt.setInt(2,courseApprovalCache.gettId());
            pstmt.setString(3,courseApprovalCache.getcName());
            pstmt.setInt(4,courseApprovalCache.getApproval());
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("openCourse  ： 插入成功！");
            } else {
                System.out.println("openCourse  ： 插入失败");
            }
            return rowsAffected;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<CourseApprovalCache> queryOpen(String teacherId) {
        String sql = "SELECT * FROM course_application where tid = ?";
        List<CourseApprovalCache> list = new ArrayList<>();
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, Integer.parseInt(teacherId));
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                CourseApprovalCache cache = new CourseApprovalCache();
                cache.setKind(rs.getInt("kind"));
                cache.setcName(rs.getString("cName"));
                cache.settId(rs.getInt("tId"));
                cache.setApplicantId(rs.getInt("applicantId"));
                cache.setApproval(rs.getInt("approval"));
                list.add(cache);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Student> queryStudentCourse(int courseId) {
        String sql = "SELECT student_table.studentId,student_table.name " +
                "FROM student_table " +
                "JOIN stu_choose ON student_table.studentId = stu_choose.stuId " +
                "JOIN course_table ON stu_choose.cId = course_table.courseId " +
                "WHERE course_table.courseId = ?";
        List<Student> list = new ArrayList<>();
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,courseId);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                Student student = new Student();
                student.setStudentId(rs.getInt("studentId"));
                student.setName(rs.getString("name"));
                list.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
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

}
