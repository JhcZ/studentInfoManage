package dao.impl;

import dao.BaseDao;
import dao.TeacherDao;
import model.Teacher;
import util.Encrypt;


import java.sql.ResultSet;
import java.sql.SQLException;

public class TeacherDaoImpl extends BaseDao implements TeacherDao {


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
                teacher.setTeacherId(rs.getString("teacherId"));
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


}
