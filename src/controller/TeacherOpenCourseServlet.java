package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Teacher;

import java.io.IOException;

@WebServlet("/teacher/openCourse")
public class TeacherOpenCourseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        if(teacher == null){
            System.out.println("未登录，无法查询");
            resp.sendRedirect("/teacher/login.do");
            return;
        }



    }
}
