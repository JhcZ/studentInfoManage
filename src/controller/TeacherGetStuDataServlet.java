package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Student;

import java.io.IOException;
import java.util.List;

@WebServlet("/teacher/get_stu_info")
public class TeacherGetStuDataServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        List<Student> list = (List<Student>) session.getAttribute("studentList");
        if (list == null) {
            System.out.println("TeacherGetStuDataServlet  : 查询出错");
            return;
        }
        resp.setContentType("application/json; charset=utf8");
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(list);
        resp.getWriter().write(json);
        System.out.println(json);
    }
}
