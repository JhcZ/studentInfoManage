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
import java.util.List;

//后台：管理员查询所有学生
@WebServlet(urlPatterns = {"/admin/customer/list","/admin/customer/query"})
public class StudentListController extends HttpServlet {
    StudentService studentService = new StudentServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DiskFileItemFactory factory = DiskFileItemFactory.builder().get();
        JakartaServletFileUpload upload =new JakartaServletFileUpload(factory);
        List<DiskFileItem> fileItems =upload.parseRequest(req);

        Student student = new Student();
        for(DiskFileItem item : fileItems){
            if(!item.isFormField()){
                // 获取表单中除文件以外的其他控件值
                if(item.getFieldName().equals("studentId")){
                    student.setStudentId(Integer.parseInt(item.getString()));
                }
                if(item.getFieldName().equals("name")){
                    student.setName(new String(item.getString().getBytes(StandardCharsets.ISO_8859_1),
                            StandardCharsets.UTF_8));
                }
                if(item.getFieldName().equals("major")){
                    new String(item.getString().getBytes(StandardCharsets.ISO_8859_1),
                            StandardCharsets.UTF_8);
                }
                if(item.getFieldName().equals("department")){
                    new String(item.getString().getBytes(StandardCharsets.ISO_8859_1),
                            StandardCharsets.UTF_8);
                }
                if(item.getFieldName().equals("sex")){
                    new String(item.getString().getBytes(StandardCharsets.ISO_8859_1),
                            StandardCharsets.UTF_8);
                }
                if(item.getFieldName().equals("className")){
                    new String(item.getString().getBytes(StandardCharsets.ISO_8859_1),
                            StandardCharsets.UTF_8);
                }
                if(item.getFieldName().equals("phone")){
                    new String(item.getString().getBytes(StandardCharsets.ISO_8859_1),
                            StandardCharsets.UTF_8);
                }
                if(item.getFieldName().equals("status")){
                    new String(item.getString().getBytes(StandardCharsets.ISO_8859_1),
                            StandardCharsets.UTF_8);
                }
            }
            if(studentService.mod(student)){
                // 在会话范围内保存action属性，记录当前管理员的管理行为
                // 监听器将监听到action属性的值发生变化这一事件，从而将管理员的管理行为记入日志文件中
                req.getSession().setAttribute("action","修改学生信息" + student);

                //修改成功，页面重定向至列表界面
                resp.sendRedirect("list");
            }else{
                //修改失败，将请求转发至学生信息修改界面
                req.setAttribute("student",student);
                req.getRequestDispatcher("mod.do").forward(req,resp);
            }
        }
    }
}
