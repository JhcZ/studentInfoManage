package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.StudentService;
import service.impl.StudentServiceImpl;

import java.io.IOException;

/**
 * @description: 处理学生注销功能
 * @author: JhcZ
 * @Email：2325947239@qq.com
 * @create: 2023-12-26 09:30
 **/
@WebServlet("/logout")
public class StudentLogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().removeAttribute("student");
        req.getSession().invalidate();
        resp.sendRedirect("student/login");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}