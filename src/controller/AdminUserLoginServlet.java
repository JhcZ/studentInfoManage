package controller;

/**
 * @description:
 * @author: JhcZ
 * @Email：2325947239@qq.com
 * @create: 2023-12-27 18:04
 **/
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.AdminUser;
import service.AdminUserService;
import service.impl.AdminUserServiceImpl;

import java.io.IOException;

@WebServlet("/admin/login")
public class AdminUserLoginServlet extends HttpServlet{
    AdminUserService adminUserService = new AdminUserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req , resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 从客户端获取用户登录信息
        AdminUser user = new AdminUser();
        user.setName(req.getParameter("name"));
        user.setPassword(req.getParameter("password"));
        // 服务器端输入验证
        if(user.getName() == null || user.getPassword() == null || user.getName().isEmpty() || user.getPassword().isEmpty()) {
            // 登录失败
            System.out.println("Servlet 管理员登录失败：验证失败" + user);
            resp.sendRedirect("login.do"); // .do标识明确处理请求
            return;
        }
        // 验证码输入验证
        String inputCode = req.getParameter("validCode");
        if(inputCode == null || inputCode.isEmpty()) {
            // 登录失败
            System.out.println("Servlet 管理员登录失败：验证码为空");
            resp.sendRedirect("login.do");
            return;
        }
        // 获取会话对象
        HttpSession session = req.getSession();
        // 验证验证码
        String validCode = (String)session.getAttribute("validCode");
        if(!inputCode.equalsIgnoreCase(validCode)) { // 忽略大小写比较
            // 登录失败
            System.out.println("Servlet 管理员登录失败：验证码错误");
            System.out.println("用户中输入的验证码：" + inputCode);
            System.out.println("会话中的验证码：" + validCode);
            resp.sendRedirect("login.do");
            return;
        }
        // 检查登录用户
        AdminUser admin = adminUserService.getLoginUser(user);
        if(admin == null) {
            // 登录失败
            System.out.println("Servlet 管理员登录失败：数据库查询失败 查询结果为null");
            resp.sendRedirect("login.do");
            return;
        }
        // 检测账号状态是否正常
        if(!adminUserService.checkStatus(admin)) {
            // 登录失败
            System.out.println("Servlet 管理员登录失败：账户状态异常"  + admin);
            resp.sendRedirect("login.do");
            return;
        }
        // 登录成功
        System.out.println("Servlet 管理员登录成功" + admin);
        // 将对象保存在session中
        session.setAttribute("admin" , admin);
        //页面重定向至后台管理
        resp.sendRedirect("dashBoard.do");
    }
}