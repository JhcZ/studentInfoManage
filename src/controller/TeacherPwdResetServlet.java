package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Teacher;
import service.TeacherService;
import service.impl.TeacherServiceImpl;

import java.io.IOException;

/**
 * @description: 处理重置教师密码功能
 * @author: JhcZ
 * @Email：2325947239@qq.com
 * @create: 2023-12-28 02:43
 **/
@WebServlet(urlPatterns = {"/admin/teacher/reset","/teacher/reset"})
public class TeacherPwdResetServlet extends HttpServlet {
    TeacherService teacherService = new TeacherServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sId = req.getParameter("id");
        Teacher teacher = new Teacher();
        teacher.setTeacherId(sId);
        if(sId != null && !sId.isEmpty()){
            teacherService.updatePw(teacher,sId);
            System.out.println("重置密码成功！");
        }
        if(req.getRequestURI().contains("admin")){
            resp.sendRedirect("list");
        }else{
            resp.sendRedirect("login.do");
        }
    }
}