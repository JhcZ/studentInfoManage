package controller;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Course;
import service.CourseService;
import service.impl.CourseServiceImpl;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/course/query")
public class AdminUserQueryCourse extends HttpServlet {
    CourseService courseService = new CourseServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Result result = new Result();
        int page = Integer.parseInt(req.getParameter("page"));
        Course course = Course.emtpyCourse;
        int pageSize = 5;
        result.courses = courseService.get(course , page , pageSize);
        result.totalPages = courseService.count(course);
        String json = new Gson().toJson(result);
        resp.getWriter().write(json);
    }
    private static class Result {
       private List<Course>  courses;
       private int totalPages;

        public List<Course> getCourses() {
            return courses;
        }

        public void setCourses(List<Course> courses) {
            this.courses = courses;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }
    }
}
