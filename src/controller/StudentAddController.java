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

//后台：管理员添加学生
@WebServlet(urlPatterns = "/admin/student/add")
public class StudentAddController extends HttpServlet {
    StudentService studentService = new StudentServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Student student = new Student();
        student.setName(req.getParameter("name"));
        student.setPassword(req.getParameter("password"));

        studentService.add(student);
        req.getSession().setAttribute("action","添加学生" + student.getName());

        resp.sendRedirect("list");
    }
}
