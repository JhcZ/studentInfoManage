package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Teacher;
import service.TeacherService;
import service.impl.TeacherServiceImpl;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/teacher/login")
public class TeacherLoginServlet extends HttpServlet {

    TeacherService teacherService = new TeacherServiceImpl();

    //判断登录状态
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取当前会话
        HttpSession session = req.getSession(false);
        if (session == null){
            //说明当前是未登录状态
            System.out.println("没有会话");
            resp.setStatus(403);
            return;
        }

        //当登录后，注销登录就会导致没有会话
        Teacher teacher = (Teacher) session.getAttribute("user");
        if (teacher == null){
            //虽然有会话，但是没有对象
            System.out.println("没有user");
            resp.setStatus(403);
            return;
        }

        //2.返回200
        resp.setStatus(200);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        Teacher teacher = new Teacher();
        teacher.setTeacherId(req.getParameter("teacherId"));
        teacher.setPassword(req.getParameter("password"));
        if (teacher.getTeacherId() == null || teacher.getPassword() == null || teacher.getPassword().isEmpty() || teacher.getTeacherId().isEmpty()){
            System.out.println("登录失败");
            resp.sendRedirect("login");
            return;
        }
        Teacher t = null;
        try {
            t = teacherService.getLoginTeacher(teacher.getTeacherId(),teacher.getPassword());

        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (t == null){
            System.out.println("登录失败,账号或密码错误");
            //resp.sendRedirect("/login.html");
            return;
        }
        HttpSession session = req.getSession();
        session.setAttribute("teacher",t);
        System.out.println(t.getName() + " : 登录成功");
        resp.setContentType("text/html");
        String msg = "success";
        resp.getWriter().write(msg);

    }
}
