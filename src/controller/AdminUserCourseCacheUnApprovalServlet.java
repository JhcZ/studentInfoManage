package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.CourseCacheService;
import service.impl.CourseCacheServiceImpl;

import java.io.IOException;

@WebServlet("/admin/courseCache/unApproval")
public class AdminUserCourseCacheUnApprovalServlet extends HttpServlet {
    CourseCacheService courseCacheService = new CourseCacheServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int courseId = Integer.parseInt(req.getParameter("id"));
        courseCacheService.unApproval(courseId);
    }
}
