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
@WebServlet("/admin/student/del")
public class StudentDelServlet extends HttpServlet {
    StudentService studentService = new StudentServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sId = req.getParameter("id");
        if(sId != null && !sId.isEmpty()){
            studentService.del(Integer.parseInt(sId));
        }
        resp.sendRedirect("list");
    }
}
