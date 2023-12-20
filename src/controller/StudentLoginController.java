package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Student;
import model.User;
import service.StudentService;
import service.impl.StudentServiceImpl;

import java.io.IOException;

//前台：学生登录
@WebServlet(urlPatterns = {"/student/login"})
public class StudentLoginController extends HttpServlet {
    StudentService studentService = new StudentServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        user.setId(Integer.parseInt(req.getParameter("studentId")));
        user.setPassword(req.getParameter("password"));

        //登录失败
        if(user.getId() == 0 || user.getPassword() == null || user.getPassword().isEmpty()){
            System.out.println("Servlet登录失败：用户名或密码错误：" + user);
            resp.sendRedirect("login.do");
            return;
        }
        //验证码错误
        String inputCode =req.getParameter("inputCode");
        if(inputCode == null && inputCode.isEmpty()){
            System.out.println("Servlet登录失败：验证码为空：");
            resp.sendRedirect("login.do");
            return;
        }
        HttpSession session = req.getSession();

        String validCode = (String) session.getAttribute("validCode");
        if(!inputCode.equalsIgnoreCase(validCode)){
            System.out.println("Servlet登录失败：验证码错误");
            System.out.println("输入的验证码：" + inputCode);
            System.out.println("会话中的验证码：" + validCode);
            resp.sendRedirect("login.do");
            return;
        }

        //检查数据库中是否存在该学生信息
        Student student = studentService.check(user);
        if(student == null){
            System.out.println("Servlet用户登录失败: 数据库查询失败" + student);
            resp.sendRedirect("login.do");
            return;
        }
        System.out.println("Servlet学生登录成功" + student);

        session.setAttribute("student",student);

        resp.sendRedirect(req.getContextPath() + "/student/info");
    }
}
