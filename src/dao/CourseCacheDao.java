package dao;

import model.CourseApprovalUpdate;

import java.util.List;

public interface  CourseCacheDao extends SimpleDao<CourseApprovalUpdate>{
    // 查询所有记录
    List<CourseApprovalUpdate> getAll();

    // 更新
    int update(CourseApprovalUpdate course);
}
