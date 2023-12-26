package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Course;
import model.Student;
import model.User;
import service.CourseService;
import service.StudentService;
import service.impl.CourseServiceImpl;
import service.impl.StudentServiceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: 学生信息展示
 * @author: JhcZ
 * @Email：2325947239@qq.com
 * @create: 2023-12-25 10:21
 **/
@WebServlet("/student/info")
public class StudentInfoServlet extends HttpServlet {
    StudentService studentService = new StudentServiceImpl();
    CourseService courseService = new CourseServiceImpl();
    List<Course> courseList = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        Student student = (Student) req.getSession().getAttribute("student");
        courseList = courseService.getByStudentId(student.getStudentId());

        req.setAttribute("student", student);
        req.setAttribute("courseList",courseList);

        req.getRequestDispatcher("info.do").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }
}