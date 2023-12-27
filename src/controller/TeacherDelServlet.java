package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.CourseService;
import service.TeacherService;
import service.impl.CourseServiceImpl;
import service.impl.TeacherServiceImpl;

import java.io.IOException;

/**
 * @description:
 * @author: JhcZ
 * @Email：2325947239@qq.com
 * @create: 2023-12-28 01:19
 **/
@WebServlet("/admin/teacher/del")
public class TeacherDelServlet extends HttpServlet {
    TeacherService teacherService = new TeacherServiceImpl();
    CourseService courseService = new CourseServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sId = req.getParameter("id");
        if(sId != null && !sId.isEmpty()){
            //两表之间存在外键关系
            courseService.del(Integer.parseInt(sId));
            teacherService.del(Integer.parseInt(sId));
        }
        resp.sendRedirect("list");
    }
}