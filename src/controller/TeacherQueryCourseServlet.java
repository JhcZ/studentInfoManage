package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mysql.cj.xdevapi.JsonArray;
import com.mysql.cj.xdevapi.JsonString;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Course;
import model.Teacher;
import netscape.javascript.JSObject;
import service.TeacherService;
import service.impl.TeacherServiceImpl;
import java.io.IOException;
import java.util.List;

@WebServlet("/teacher/query")
public class TeacherQueryCourseServlet extends HttpServlet {

    TeacherService teacherService = new TeacherServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        if(teacher == null){
            System.out.println("未登录，无法查询");
            return;
        }
        List<Course> courseList = teacherService.queryCourse(teacher.getTeacherId());
        if (courseList == null || courseList.size() < 1) {
            System.out.println("没有属于该教师的课程");
            return;
        }
        resp.setContentType("application/json; charset=utf8");
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(courseList);
        resp.getWriter().write(json);
        System.out.println(json);

    }


}
