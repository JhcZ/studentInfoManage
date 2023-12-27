package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.CourseApprovalUpdate;
import service.CourseCacheService;
import service.impl.CourseCacheServiceImpl;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/courseCache/query")
public class AdminUserQueryCourseCacheServlet extends HttpServlet {
    CourseCacheService courseCacheService = new CourseCacheServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req , resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<CourseApprovalUpdate> list = courseCacheService.getAll();
        req.setAttribute("caches" , list);
        req.getRequestDispatcher("modifyRequest.do").forward(req , resp);
    }
}
