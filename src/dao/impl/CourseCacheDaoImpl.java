package dao.impl;

import dao.BaseDao;
import dao.CourseCacheDao;
import model.Course;
import model.CourseApprovalUpdate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseCacheDaoImpl extends BaseDao implements CourseCacheDao {
    @Override
    public List<CourseApprovalUpdate> getAll() {
        List<CourseApprovalUpdate> list = new ArrayList<>();
        String sql = "SELECT * FROM application_course_cache";
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                CourseApprovalUpdate courseCache = new CourseApprovalUpdate();
                courseCache.setCourse(new Course());
                Course course = courseCache.getCourse();
                course.setCourseId(rs.getInt("courseId"));
                course.setName(rs.getString("name"));
                course.setTeacherId(rs.getInt("teacher"));
                course.setLocation(rs.getString("location"));
                course.setCourseDuration(rs.getString("courseDuration"));
                course.setClassDay(rs.getInt("classDay"));
                course.setClassTime(rs.getString("classTime"));
                course.setStartTime(rs.getString("startTime"));
                course.setSemester(rs.getInt("semester"));
                course.setNumOfStu(rs.getInt("numOfStu"));
                courseCache.setApproval(rs.getInt("approval"));
                list.add(courseCache);
            }
        } catch (SQLException e) {
            throw new RuntimeException("CourseCacheDao查询所有信息出错:" + e + " " + sql);
        }
        return list;
    }

    @Override
    public CourseApprovalUpdate findById(int id) {
        CourseApprovalUpdate cache = new CourseApprovalUpdate();
        String sql = "SELECT * FROM application_course_cache WHERE courseId=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,  id);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                cache.setCourse(new Course());
                Course course = cache.getCourse();
                course.setCourseId(rs.getInt("courseId"));
                course.setName(rs.getString("name"));
                course.setTeacherId(rs.getInt("teacher"));
                course.setLocation(rs.getString("location"));
                course.setCourseDuration(rs.getString("courseDuration"));
                course.setClassDay(rs.getInt("classDay"));
                course.setClassTime(rs.getString("classTime"));
                course.setStartTime(rs.getString("startTime"));
                course.setSemester(rs.getInt("semester"));
                course.setNumOfStu(rs.getInt("numOfStu"));
                cache.setApproval(rs.getInt("approval"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("CourseCacheGetById finished");
        return cache;
    }

    @Override
    public List<CourseApprovalUpdate> query(CourseApprovalUpdate condition) {
        return null;
    }

    @Override
    public List<CourseApprovalUpdate> query(CourseApprovalUpdate condition, int start, int num) {
        return null;
    }

    @Override
    public int insert(CourseApprovalUpdate courseApprovalUpdate) {
        return 0;
    }

    @Override
    public int update(CourseApprovalUpdate course) {
        String sql = "UPDATE application_course_cache " +
                "SET name = ?, teacher = teacher, location = ?, courseDuration = ?, " +
                "flag = flag, classes = classes, classDay = ?,classTime = ?,startTime = ?, semester = ?, " +
                "numOfStu = numOfStu, approval = ? " +
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
            pstmt.setInt(8, course.getApproval());
            pstmt.setInt(9, course1.getCourseId());

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
        System.out.println("update finished");
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
    public int count(CourseApprovalUpdate condition) {
        return 0;
    }
}
