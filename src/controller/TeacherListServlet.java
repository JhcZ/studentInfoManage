package controller;

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

/**
 * @description:
 * @author: JhcZ
 * @Email：2325947239@qq.com
 * @create: 2023-12-28 00:26
 **/
@WebServlet(urlPatterns = {"/admin/teacher/list","/admin/teacher/query"})
public class TeacherListServlet extends HttpServlet {
    TeacherService teacherService = new TeacherServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8"); //处理中文乱码
        Teacher condition = new Teacher();
        condition.setTeacherId("");
        condition.setName(req.getParameter("name"));
        // 从客户端获取分页信息
        int page = 1;
        String sPage = req.getParameter("p");
        if (sPage != null && !"".equals(sPage)) {
            page = Integer.parseInt(req.getParameter("p"));
        }
        int pageSize = 5;
        int usersCount = teacherService.count(condition);
        int pageCount = usersCount % pageSize == 0 ? usersCount / pageSize : usersCount / pageSize + 1;
        // 从模型层获取到查询结果
        List<Teacher> teacherList = teacherService.get(condition, page, pageSize);
        // 在请求范围内保存用户列表数据
        req.setAttribute("teacherList", teacherList);
        req.setAttribute("p", page);
        req.setAttribute("pCount", pageCount);
        // 页面跳转：请求转发至列表页面
        req.getRequestDispatcher("list.do").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}