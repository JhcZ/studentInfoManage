package service;

import model.CourseApprovalUpdate;

import java.util.List;

public interface CourseCacheService {

    CourseApprovalUpdate findById(int courseId);

    // 获取所有信息
    List<CourseApprovalUpdate> getAll();

    int update(int courseId);

    int approval(int courseId);

    int unApproval(int courseId);
}
