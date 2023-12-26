package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Score;
import model.Student;
import service.ScoreService;
import service.StudentService;
import service.impl.ScoreServiceImpl;
import service.impl.StudentServiceImpl;

import java.io.IOException;
import java.util.List;

/**
 * @description: 处理学生查询成绩功能
 * @author: JhcZ
 * @Email：2325947239@qq.com
 * @create: 2023-12-26 09:43
 **/
@WebServlet("/student/score")
public class StudentScoreServlet extends HttpServlet {
    StudentService studentService = new StudentServiceImpl();
    ScoreService scoreService = new ScoreServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Student student = (Student) req.getSession().getAttribute("student");

        req.setAttribute("student",student);
        req.getRequestDispatcher("score.do").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}