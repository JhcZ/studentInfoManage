package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.CourseApprovalCache;
import model.Teacher;
import service.TeacherService;
import service.impl.TeacherServiceImpl;

import java.io.IOException;

@WebServlet("/teacher/open_course")
public class TeacherOpenCourseServlet extends HttpServlet {
    TeacherService teacherService = new TeacherServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        if(teacher == null){
            System.out.println("未登录，无法查询");
            resp.sendRedirect("/teacher/login.do");
            return;
        }

        CourseApprovalCache cache = new CourseApprovalCache();
        cache.setKind(Integer.parseInt(req.getParameter("kind")));
        cache.setcName(req.getParameter("cName"));
        cache.settId(Integer.parseInt(teacher.getTeacherId()));
        cache.setApproval(0);
        int rs = teacherService.openCourse(cache);
        if (rs > 0){
            System.out.println("申请发送成功");
            resp.getWriter().write("success");

        }else {
            System.out.println("申请发送失败");
        }
    }
}
