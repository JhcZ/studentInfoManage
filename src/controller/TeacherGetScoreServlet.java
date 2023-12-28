package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.TeacherDao;
import dao.impl.TeacherDaoImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Score;
import model.Teacher;
import service.TeacherService;
import service.impl.TeacherServiceImpl;

import java.io.IOException;
import java.util.List;

@WebServlet("/teacher/get_score_list")
public class TeacherGetScoreServlet extends HttpServlet {
    TeacherService service = new TeacherServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        if (teacher == null){
            System.out.println("未登录");
            return;
        }
        int courseId = (int) session.getAttribute("courseId");
        List<Score> list = service.queryStudScore(courseId);
        resp.setContentType("application/json; charset=utf8");
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(list);
        resp.getWriter().write(json);
        System.out.println(json);
    }
}
