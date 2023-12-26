package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Course;
import model.CourseApprovalUpdate;
import model.Teacher;
import service.TeacherService;
import service.impl.TeacherServiceImpl;

import java.io.IOException;

@WebServlet("/teacher/mod")
public class TeacherModCourseServlet extends HttpServlet {
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
        Course course = teacherService.findCourseById(courseId);
        course.setName(req.getParameter("name"));
        course.setLocation(req.getParameter("location"));
        course.setCourseDuration(req.getParameter("courseDuration"));
        course.setStartTime(req.getParameter("startTime"));
        course.setSemester(Integer.parseInt(req.getParameter("semester")));
        CourseApprovalUpdate nCourse = new CourseApprovalUpdate();
        nCourse.setCourse(course);
        nCourse.setApproval(0);
        int rs = teacherService.modCourse(nCourse);
        if (rs > 0){
            resp.setContentType("text/html");
            String msg = "success";
            resp.getWriter().write(msg);
        }else {
            System.out.println("修改申请提交失败");
        }
    }
}
