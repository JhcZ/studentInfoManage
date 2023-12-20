package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.StudentService;
import service.impl.StudentServiceImpl;

import java.io.IOException;

//后台：管理员删除学生
@WebServlet(urlPatterns = "/admin/student/del")
public class StudentDelController extends HttpServlet {
    StudentService studentService = new StudentServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sId = req.getParameter("studentId");
        if(sId != null && !sId.isEmpty()){
            studentService.del(Integer.parseInt(sId));

            req.getSession().setAttribute("action","删除学生id=" + sId);
        }
        resp.sendRedirect("list");
    }
}
