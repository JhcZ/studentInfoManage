package controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import service.StudentService;
import service.impl.StudentServiceImpl;

/**
 * @description: 处理学生查询成绩功能
 * @author: JhcZ
 * @Email：2325947239@qq.com
 * @create: 2023-12-26 09:43
 **/
@WebServlet("/score")
public class StudentScoreServlet extends HttpServlet {
    StudentService studentService = new StudentServiceImpl();

}