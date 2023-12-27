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
import java.sql.SQLException;

/**
 * @description:
 * @author: JhcZ
 * @Email：2325947239@qq.com
 * @create: 2023-12-28 01:39
 **/
@WebServlet("/admin/teacher/add")
public class TeacherAddServlet extends HttpServlet {
    TeacherService teacherService = new TeacherServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Teacher teacher = new Teacher();
        teacher.setTeacherId(req.getParameter("updateTeacherId"));
        teacher.setName(req.getParameter("updateName"));
        teacher.setDepartment(req.getParameter("updateDepartment"));
        teacher.setDegree(req.getParameter("updateDegree"));
        teacher.setSex(req.getParameter("updateSex"));
        teacher.setPhone(req.getParameter("updatePhone"));
        teacher.setEmail(req.getParameter("updateEmail"));
        teacher.setCreateTime(req.getParameter("updateCreateTime"));
        teacher.setPassword(req.getParameter("updatePwd"));
        req.setAttribute("teacher",teacher);

        if(teacherService.add(teacher)){
            System.out.println("更新成功！");
            resp.sendRedirect(req.getContextPath() + "/admin/teacher/list");
        }else{
            System.out.println("更新失败！");
            resp.sendRedirect(req.getContextPath() + "/admin/teacher/list");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}