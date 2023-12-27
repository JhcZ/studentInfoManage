package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Score;
import model.Student;
import model.Teacher;
import service.TeacherService;
import service.impl.TeacherServiceImpl;

import java.io.IOException;
import java.util.List;

@WebServlet("/teacher/query_stu_course")
public class TeacherInputScoreServlet extends HttpServlet {
    TeacherService teacherService = new TeacherServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        if (teacher == null){
            System.out.println("未登录");
            return;
        }
        int courseId = Integer.parseInt(req.getParameter("courseId"));
        List<Student> list = teacherService.queryStudentCourse(courseId);
        session.setAttribute("studentList",list);
        resp.sendRedirect("course_input.do");
    }
}
