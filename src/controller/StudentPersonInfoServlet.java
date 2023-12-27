package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Student;

import java.io.IOException;

/**
 * @description: 处理个人信息
 * @author: JhcZ
 * @Email：2325947239@qq.com
 * @create: 2023-12-26 18:37
 **/
@WebServlet("/student/personInfo")
public class StudentPersonInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Student student = (Student) req.getSession().getAttribute("student");

        req.setAttribute("student",student);
        req.getRequestDispatcher("personInfo.do").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}