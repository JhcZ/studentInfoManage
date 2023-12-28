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

/**
 * @description: 处理学生端修改密码逻辑
 * @author: JhcZ
 * @Email：2325947239@qq.com
 * @create: 2023-12-28 09:12
 **/
@WebServlet("/modPwd")
public class StudentModPwdServlet extends HttpServlet {
    StudentService studentService = new StudentServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Student student = new Student();
        String sId = req.getParameter("studentId");
        String newPwd = req.getParameter("newPwd");
        student.setStudentId(Integer.parseInt(sId));
        student.setPassword(newPwd);

        if(studentService.modPwd(Integer.parseInt(sId),newPwd)){
            System.out.println("密码更改成功！");
            resp.sendRedirect("student/login");
        }else{
            System.out.println("密码修改失败！");
            req.getRequestDispatcher("info.do").forward(req,resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}