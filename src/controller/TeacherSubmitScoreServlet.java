package controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Score;
import model.Teacher;
import service.ScoreService;
import service.impl.ScoreServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/teacher/sub_score")
public class TeacherSubmitScoreServlet extends HttpServlet {

    ScoreService scoreService = new ScoreServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        if (teacher == null){
            System.out.println("未登录");
            return;
        }
        try {
            StringBuilder requestData = new StringBuilder();
            BufferedReader reader = req.getReader();
            String line;
            while ((line = reader.readLine()) != null) {
                requestData.append(line);
            }

            Gson gson = new Gson();
            StuScore[] data = gson.fromJson(requestData.toString(), StuScore[].class);
            List<Score> list = new ArrayList<>();
            for (int i = 0; i < data.length; i++) {
                StuScore formDataObject = data[i];
                int cId = formDataObject.getcId();
                int stuId = formDataObject.getStuId();
                int score = (int) formDataObject.getScore();
                System.out.println(formDataObject);
                Score score1 = new Score();
                score1.setCourseId(cId);
                score1.setStudentId(stuId);
                score1.setGrade(score);
                list.add(score1);
                // 在这里进行对学生数据的处理，例如保存到数据库或进行其他操作
            }
            scoreService.importScores(list);
            System.out.println(list);
            // 返回响应）
            resp.getWriter().write("success");
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");

        } catch (IOException e) {
            e.printStackTrace();
            // 返回错误响应
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write("{\"status\":\"error\"}");
        }

    }

    class StuScore{
        int stuId;
        int cId;
        double score;

        public int getcId() {
            return cId;
        }

        public void setcId(int cId) {
            this.cId = cId;
        }

        public int getStuId() {
            return stuId;
        }

        public void setStuId(int stuId) {
            this.stuId = stuId;
        }


        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }
    }
}
