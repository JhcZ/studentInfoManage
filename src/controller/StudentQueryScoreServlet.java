package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Course;
import model.Student;
import service.CourseService;
import service.ScoreService;
import service.StudentService;
import service.TeacherService;
import service.impl.CourseServiceImpl;
import service.impl.ScoreServiceImpl;
import service.impl.StudentServiceImpl;
import service.impl.TeacherServiceImpl;

import java.io.IOException;
import java.sql.SQLException;

/**
 * @description:处理学生查询成绩功能
 * @author: JhcZ
 * @Email：2325947239@qq.com
 * @create: 2023-12-26 16:46
 **/
@WebServlet("/student/queryScore")
public class StudentQueryScoreServlet extends HttpServlet {
    StudentService studentService = new StudentServiceImpl();
    CourseService courseService = new CourseServiceImpl();
    ScoreService scoreService = new ScoreServiceImpl();
    TeacherService teacherService = new TeacherServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Student student = (Student) req.getSession().getAttribute("student");
        String studentId = req.getParameter("studentId");
        String courseId = req.getParameter("courseId");

        if(studentId == null && studentId.isEmpty()){
            System.out.println("输入学号为空，请重新输入！");
            resp.sendRedirect("score.do");
            return;
        }

        if(courseId == null && courseId.isEmpty()){
            System.out.println("输入学课程号为空，请重新输入！");
            resp.sendRedirect("score.do");
            return;
        }
        int stuId = Integer.parseInt(studentId);
        int cId = Integer.parseInt(courseId);

        Course course = courseService.get(cId);
        String cName = course.getName();
        String teacherId = String.valueOf(course.getTeacherId());
        try {
            String tName = teacherService.findById(teacherId).getName();
            req.setAttribute("tName",tName);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        double score = scoreService.findByStuIdAndCId(stuId,cId).getGrade();

        req.setAttribute("stuName",student.getName());
        req.setAttribute("cName",cName);
        req.setAttribute("score",score);

        req.getRequestDispatcher("score.do").forward(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}