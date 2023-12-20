package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.StudentStatus;
import service.StudentService;
import service.impl.StudentServiceImpl;

import java.io.IOException;

//后台修改学生状态,已毕业或请假或休学
@WebServlet(urlPatterns = {"/admin/student/graduation","/admin/student/leave","/admin/student/break"})
public class StudentStatusModController extends HttpServlet {
    StudentService studentService = new StudentServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sId = req.getParameter("studentId");
        if(sId != null && !sId.isEmpty()){
            int studentId = Integer.parseInt(sId);
            if(req.getServletPath().contains("graduation")){
                studentService.modStatus(studentId, StudentStatus.GRADUATION);
                req.getSession().setAttribute("action","学生毕业，id=" + studentId);
            }else if(req.getServletPath().contains("leave")){
                studentService.modStatus(studentId,StudentStatus.LEAVE);
                req.getSession().setAttribute("action","学生请假，id=" + studentId);
            }else if(req.getServletPath().contains("break")){
                studentService.modStatus(studentId,StudentStatus.BREAK);
                req.getSession().setAttribute("action","学生休学，id=" + studentId);
            }else{
                studentService.modStatus(studentId,StudentStatus.NORMAL);
            }
        }
        resp.sendRedirect("list");
    }
}
