package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.CourseApprovalUpdate;
import model.Teacher;
import service.TeacherService;
import service.impl.TeacherServiceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/teacher/modlist")
public class TeacherModListServlet extends HttpServlet {
    TeacherService teacherService = new TeacherServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        if(teacher == null){
            System.out.println("未登录，无法查询");
            resp.sendRedirect("/teacher/login.do");
            return;
        }
        List<CourseApprovalUpdate> list = teacherService.queryApply(teacher.getTeacherId());
        resp.setContentType("application/json; charset=utf8");
        Gson gson = new GsonBuilder().create();
        StringBuilder msg = new StringBuilder();
        msg.append("[");
        for (CourseApprovalUpdate update : list){
            StringBuilder json = new StringBuilder(gson.toJson(update.getCourse()));
            json.delete(json.length()-1,json.length());
            msg.append(json).append(",\"approval\":0},");
        }
        msg.delete(msg.length()-1,msg.length());
        msg.append("]");
        System.out.println(msg);
        resp.getWriter().write(String.valueOf(msg));
    }
}
