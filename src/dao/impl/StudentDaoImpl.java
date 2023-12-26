package dao.impl;

import dao.BaseDao;
import dao.StudentDao;
import model.Course;
import model.Student;
import model.Teacher;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl extends BaseDao implements StudentDao {
    @Override
    public Student find(int studentId, String password) {
        Student student = null;
        String sql = "SELECT * FROM student_table WHERE studentId=? AND password=?";
        System.out.println("DAO验证学生find(studentId,password)：" + sql);
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,studentId);
            pstmt.setString(2,password);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                student = new Student();
                student.setStudentId(rs.getInt("studentId"));
                student.setName(rs.getString("name"));
                student.setMajor(rs.getString("major"));
                student.setDepartment(rs.getString("department"));
                student.setSex(rs.getString("sex"));
                student.setClassName(rs.getString("className"));
                student.setPhone(rs.getString("phone"));
                student.setStatus(rs.getString("status"));
            }
        }catch (SQLException e){
            System.out.println("DAO验证用户错误：" + sql + "," + e.getMessage());
        }
        return student;
    }

    @Override
    public int updatePwd(int studentId, String newPwd) {
        int rows = 0;
        String sql = "UPDATE student_table SET password=? WHERE studentId=?";
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,newPwd);
            pstmt.setInt(2,studentId);
            rows = pstmt.executeUpdate();
        }catch (SQLException e){
            System.out.println("DAO重置学生密码错误：" + sql + "," + e.getMessage());
        }
        return rows;
    }

    @Override
    public int updateStatus(int studentId, String status) {
        int rows = 0;
        String sql = "UPDATE student_table SET status=? WHERE studentId=?";
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,status);
            pstmt.setInt(2,studentId);
            rows = pstmt.executeUpdate();
        }catch (SQLException e){
            System.out.println("DAO更改学生状态错误：" + sql + "," + e.getMessage());
        }
        return rows;
    }

    @Override
    public Student findById(int id) {
        Student condition = new Student();
        condition.setStudentId(id);
        List<Student> studentList = query(condition);
        if(studentList != null && studentList.size() == 1)
            return studentList.get(0);
        return null;
    }

    @Override
    public List<Student> query(Student condition) {
        List<Student> studentList = new ArrayList<>();
        String sql = "SELECT * FROM student_table";
        if(condition !=null){
            sql += " WHERE 1=1";
            if(condition.getStudentId() != 0){
                sql += " AND studentId='" + condition.getStudentId() + "'";
            }
            if(condition.getName() != null && !condition.getName().isEmpty()){
                sql += " AND name LIKE '%" + condition.getName() + "%'";
            }
            if(condition.getMajor() != null && !condition.getMajor().isEmpty()){
                sql += " AND major LIKE '%" + condition.getMajor() + "%'";
            }
            if(condition.getDepartment() != null && !condition.getDepartment().isEmpty()){
                sql += " AND department LIKE '%" + condition.getDepartment() + "%'";
            }
        }
        sql += " ORDER BY studentId ASC";
        System.out.println("DAO查询学生query(condition)：" + sql);

        try{
            pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                Student student = new Student();
                student.setStudentId(rs.getInt("studentId"));
                student.setName(rs.getString("name"));
                student.setMajor(rs.getString("major"));
                student.setDepartment(rs.getString("department"));
                student.setSex(rs.getString("sex"));
                student.setClassName(rs.getString("className"));
                student.setPhone(rs.getString("phone"));
                student.setStatus(rs.getString("status"));
                studentList.add(student);
            }
        }catch (SQLException e){
            System.out.println("DAO查询学生query(condition)错误：" + sql + "," + e.getMessage());
        }
        return studentList;
    }

    @Override
    public List<Student> query(Student condition, int start, int num) {
        List<Student> studentList = new ArrayList<>();
        String sql = "SELECT * FROM student_table";
        if(condition !=null){
            sql += " WHERE =1=1";
            if(condition.getStudentId() != 0){
                sql += " AND studentId='" + condition.getStudentId() + "'";
            }
            if(condition.getName() != null && !condition.getName().isEmpty()){
                sql += " AND name LIKE '%" + condition.getName() + "%'";
            }
            if(condition.getMajor() != null && !condition.getMajor().isEmpty()){
                sql += " AND major LIKE '%" + condition.getMajor() + "%'";
            }
            if(condition.getDepartment() != null && !condition.getDepartment().isEmpty()){
                sql += " AND department LIKE '%" + condition.getDepartment() + "%'";
            }
        }
        sql += " ORDER BY studentId ASC LIMIT ?,?";
        System.out.println("DAO查询学生query(condition,start,num)：" + sql);

        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,start);
            pstmt.setInt(2,num);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                Student student = new Student();
                student.setStudentId(rs.getInt("studentId"));
                student.setName(rs.getString("name"));
                student.setMajor(rs.getString("major"));
                student.setDepartment(rs.getString("department"));
                student.setSex(rs.getString("sex"));
                student.setClassName(rs.getString("className"));
                student.setPhone(rs.getString("phone"));
                student.setStatus(rs.getString("status"));
                studentList.add(student);
            }
        }catch (SQLException e){
            System.out.println("DAO查询学生query(condition,start,num)错误：" + sql + "," + e.getMessage());
        }
        return studentList;
    }

    @Override
    public int insert(Student student) {
        int rows = 0;
        String sql = "INSERT INTO student_table(studentId,name,major,department,sex,className,phone,status)" +
                " VALUES(?,?,?,?,?,?,?,?)";
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,student.getStudentId());
            pstmt.setString(2,student.getName());
            pstmt.setString(3,student.getMajor());
            pstmt.setString(4,student.getDepartment());
            pstmt.setString(5,student.getSex());
            pstmt.setString(6,student.getClassName());
            pstmt.setString(7,student.getPhone());
            pstmt.setString(8,student.getStatus().getName());
            rows = pstmt.executeUpdate();
        }catch (SQLException e){
            System.out.println("DAO添加学生错误：" + sql + "," + e.getMessage());
        }
        return rows;
    }

    @Override
    public int update(Student student) {
        int rows = 0;
        String sql = "UPDATE student_table SET name=?,major=?,department=?,sex=?,className=?,phone=?,status=? WHERE studentId=?";

        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,student.getName());
            pstmt.setString(2,student.getMajor());
            pstmt.setString(3,student.getDepartment());
            pstmt.setString(4,student.getSex());
            pstmt.setString(5,student.getClassName());
            pstmt.setString(6,student.getPhone());
            pstmt.setString(7,student.getStatus().getName());
            pstmt.setInt(8,student.getStudentId());
            rows = pstmt.executeUpdate();
        }catch (SQLException e){
            System.out.println("DAO更新学生信息错误：" + sql + "," + e.getMessage());
        }
        return rows;
    }

    @Override
    public int delete(int id) {
        int rows = 0;
        String sql = "DELETE FROM student_table WHERE studentId=?";
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,id);
            rows = pstmt.executeUpdate();
        }catch (SQLException e){
            System.out.println("DAO删除学生错误：" + sql + "," + e.getMessage());
        }
        return rows;
    }

    @Override
    public int count() {
        return count(null);
    }

    @Override
    public int count(Student condition) {
        int num = 0;
        String sql = "SELECT count(*) FROM student_table WHERE (1=1)";
        if(condition != null){
            if(condition.getStudentId() != 0){
                sql += " AND studentId='" + condition.getStudentId() + "'";
            }
            if(condition.getName() != null && !condition.getName().isEmpty()){
                sql += " AND name LIKE '%" + condition.getName() + "%'";
            }
            if(condition.getMajor() != null && !condition.getMajor().isEmpty()){
                sql += " AND major LIKE '%" + condition.getMajor() + "%'";
            }
            if(condition.getDepartment() != null && !condition.getDepartment().isEmpty()){
                sql += " AND department LIKE '%" + condition.getDepartment() + "%'";
            }
        }
        System.out.println("DAO查询count(condition): " + sql);
        try{
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                num = rs.getInt(1);
            }
        }catch (SQLException e){
            System.out.println("DAO根据条件" + condition +
                    "查询学生数量错误：" + sql + "," + e.getMessage()
            );
        }
        return num;
    }
}
