package dao;

import model.Student;

public interface StudentDao extends SimpleDao<Student>{
    //用于登录验证
    Student find(int studentId, String password);
    //密码重置
    int updatePwd(int studentId, String newPwd);
    //更新帐号状态
    int updateStatus(int studentId, String status);
}
