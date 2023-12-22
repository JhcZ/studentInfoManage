package controller;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Teacher;
import service.TeacherService;
import service.impl.TeacherServiceImpl;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/getTeachers")
public class AdminUserFuzzyQueryTeachers extends HttpServlet {

    TeacherService teacherService = new TeacherServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req , resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");

        Teacher fuzzy = new Teacher();
        List<Teacher> teachers = null;
        String query = req.getParameter("q");
        fuzzy.setName(query);
        teachers = teacherService.fuzzQuery(fuzzy);
        String suggestions = new Gson().toJson(teachers);
        resp.getWriter().write(suggestions);
    }
}
