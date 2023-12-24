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
import java.io.PrintWriter;

@WebServlet("/admin/addCourse")
public class AdminUserAddCourseServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req , resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CourseService courseService = new CourseServiceImpl();

        Course course = new Course();
        course.setName(req.getParameter("courseName"));
        course.setCourseId(1);
        course.setTeacherId(Integer.parseInt(req.getParameter("teacherId")));
        course.setLocation(req.getParameter("location"));
        course.setCourseDuration(req.getParameter("weeks"));
        course.setStartTime(req.getParameter("startDate"));
        course.setClassDay(Integer.parseInt(req.getParameter("classDay")));
        String[] classTime = req.getParameterValues("classTime");
        course.setClassTime(String.join("," , classTime));
        course.setSemester(Integer.parseInt(req.getParameter("semester")));
        course.setNumOfStu(Integer.parseInt(req.getParameter("enrollment")));

        System.out.println(course);
        int count =  courseService.addCourse(course);
        PrintWriter out = resp.getWriter();
        if(count < 1) {
            out.println("<script>alert('添加课程失败');parent.window.location.href='dashBoard.do'</script>");
        }else {
            out.println("<script>alert('添加课程成功');parent.window.location.href='dashBoard.do'</script>");
        }
    }
}
