package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Teacher;
import service.TeacherService;
import service.impl.TeacherServiceImpl;
import util.Encrypt;

import java.io.IOException;

@WebServlet("/teacher/update")
public class TeacherUpdateServlet extends HttpServlet {
    TeacherService teacherService = new TeacherServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        //获取当前用户
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        if (teacher == null){
            System.out.println("未登录");
            return;
        }
        String initial = req.getParameter("initial_password");
        if (!teacher.getPassword().equals(Encrypt.toMd5(initial))){
            System.out.println("初始密码错误");
            return;
        }
        String up = req.getParameter("up_password1");
        int res = teacherService.updatePw(teacher,up);
        if (res < 1){
            System.out.println("修改失败");
            return;
        }
        System.out.println("修改成功");
        resp.setContentType("text/html");
        String msg = "success";
        resp.getWriter().write(msg);
    }
}
