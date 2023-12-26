package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.StudentService;
import service.impl.StudentServiceImpl;

import java.io.IOException;

//前台：学生重置密码
//后台：管理员重置学生密码
@WebServlet(urlPatterns = {"/admin/student/reset","/student/reset"})
public class StudentPwdResetServlet extends HttpServlet {
    StudentService studentService = new StudentServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sId = req.getParameter("studentId");
        if(sId != null && !sId.isEmpty()){
            studentService.resetPwd(Integer.parseInt(sId));
        }
        if(req.getRequestURI().contains("admin")){
            req.getSession().setAttribute("action","重置学生密码，学生id=" + sId);

            resp.sendRedirect("list");
        }else{
            resp.sendRedirect("login.do");
        }
    }
}
