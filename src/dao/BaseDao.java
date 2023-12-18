package dao;

import util.DBHelper;

import java.sql.*;

//数据库连接实体类
public class BaseDao {
    protected Connection conn;
    protected PreparedStatement pstmt;
    protected Statement stmt;
    protected ResultSet rs;

    public BaseDao(){
        connect();
    }

    //数据库连接
    public void connect(){
        try {
            conn = DBHelper.getConnection();
        } catch (Exception e) {
            throw new RuntimeException("DAO连接数据库异常：" + e);
        }
    }

    public void close(){
        try{
            DBHelper.close(conn , stmt , rs , pstmt);
        }catch (SQLException e){
            throw new RuntimeException("DAO关闭数据库异常：" + e);
        }
    }

    public void beginTransaction() {
        try {
            DBHelper.beginTransaction();
        } catch (SQLException e) {
            throw new RuntimeException("DAO启动事务异常: " + e);
        }
    }


    public void commitTransaction() {
        try {
            DBHelper.commitTransaction();
        } catch (SQLException e) {
            throw new RuntimeException("DAO提交事务异常: " + e);
        }
    }

    public void rollbackTransaction() {
        try {
            DBHelper.rollbackTransaction();
        } catch (SQLException e) {
            throw new RuntimeException("DAO回滚事务异常: " + e);
        }
    }
}
