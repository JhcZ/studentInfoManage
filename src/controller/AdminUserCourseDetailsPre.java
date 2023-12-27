package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Course;
import service.CourseService;
import service.impl.CourseServiceImpl;

import java.io.IOException;

@WebServlet("/admin/course/details")
public class AdminUserCourseDetailsPre extends HttpServlet {
    CourseService courseService = new CourseServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req , resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int courseId = Integer.parseInt(req.getParameter("id"));
        Course course = courseService.get(courseId);
        req.setAttribute("course" , course);
        req.getRequestDispatcher("details.do").forward(req , resp);
    }
}
