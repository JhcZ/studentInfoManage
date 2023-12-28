package service.impl;

import dao.StudentDao;
import dao.impl.StudentDaoImpl;
import model.Student;
import model.StudentStatus;
import model.User;
import service.StudentService;
import util.Encrypt;

import java.util.List;

public class StudentServiceImpl implements StudentService {
    StudentDao studentDao = new StudentDaoImpl();
    @Override
    public Student get(int id) {
        return studentDao.findById(id);
    }

    @Override
    public Student check(User user) {
        user.setPassword(Encrypt.toMd5(user.getPassword()));
        return studentDao.find(user.getId(), user.getPassword());
    }

    @Override
    public List<Student> get(Student condition, int page, int pageSize) {
        return studentDao.query(condition,(page - 1) * pageSize,pageSize);
    }

    @Override
    public int count(Student condition) {
        return studentDao.count(condition);
    }

    @Override
    public boolean mod(Student student) {
        return studentDao.update(student) == 1;
    }

    @Override
    public boolean resetPwd(int studentId) {
        String newPwd = Encrypt.toMd5("123");
        return studentDao.updatePwd(studentId,newPwd) == 1;
    }

    @Override
    public boolean modStatus(int studentId, StudentStatus status) {
        return studentDao.updateStatus(studentId,status.getName()) == 1;
    }

    @Override
    public boolean checkStatus(Student student) {
        if(student.getStatus() != null && student.getStatus() == StudentStatus.NORMAL){
            return true;
        }
        return false;
    }

    @Override
    public boolean add(Student student) {
        return studentDao.insert(student) == 1;
    }

    @Override
    public boolean del(int studentId) {
        return studentDao.delete(studentId) == 1;
    }

    @Override
    public boolean modPwd(int studentId, String newPwd) {
        return studentDao.modPwd(studentId,newPwd);
    }
}
