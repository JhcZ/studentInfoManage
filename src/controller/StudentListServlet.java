package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Student;
import org.apache.commons.fileupload2.core.DiskFileItem;
import org.apache.commons.fileupload2.core.DiskFileItemFactory;
import org.apache.commons.fileupload2.jakarta.JakartaServletFileUpload;
import service.StudentService;
import service.impl.StudentServiceImpl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//后台：管理员查询所有学生
@WebServlet(urlPatterns = {"/admin/student/list","/admin/student/query"})
public class StudentListServlet extends HttpServlet {
    StudentService studentService = new StudentServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8"); //处理中文乱码
        Student condition = new Student();
        condition.setName(req.getParameter("name"));
        // 从客户端获取分页信息
        int page = 1;
        String sPage = req.getParameter("p");
        if (sPage != null && !"".equals(sPage)) {
            page = Integer.parseInt(req.getParameter("p"));
        }
        int pageSize = 5;
        int usersCount = studentService.count(condition);
        int pageCount = usersCount % pageSize == 0 ? usersCount / pageSize : usersCount / pageSize + 1;
        // 从模型层获取到查询结果
        List<Student> studentList = studentService.get(condition, page, pageSize);
        // 在请求范围内保存用户列表数据
        req.setAttribute("studentList", studentList);
        req.setAttribute("p", page);
        req.setAttribute("pCount", pageCount);
        // 页面跳转：请求转发至列表页面
        req.getRequestDispatcher("list.do").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
