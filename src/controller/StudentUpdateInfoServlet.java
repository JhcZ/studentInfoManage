package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Student;
import service.StudentService;
import service.impl.StudentServiceImpl;

import java.io.IOException;

@WebServlet("/updateStudentInfo")
public class StudentUpdateInfoServlet extends HttpServlet {
    StudentService studentService = new StudentServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Student student = new Student();
        student.setStudentId(Integer.parseInt(req.getParameter("updateStudentId")));
        student.setName(req.getParameter("updateName"));
        student.setMajor(req.getParameter("updateMajor"));
        student.setDepartment(req.getParameter("updateDepartment"));
        student.setSex(req.getParameter("updateSex"));
        student.setClassName(req.getParameter("updateClassName"));
        student.setPhone(req.getParameter("updatePhone"));
        student.setStatus(req.getParameter("updateStatus"));
        req.setAttribute("student",student);

        if(studentService.mod(student)){
            System.out.println("更新成功！");
            resp.sendRedirect(req.getContextPath() + "/student/personInfo");
        }else{
            System.out.println("更新失败！");
            resp.sendRedirect(req.getContextPath() + "/student/personInfo");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
