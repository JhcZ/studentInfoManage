package service.impl;

import dao.CourseCacheDao;
import dao.CourseDao;
import dao.impl.CourseCacheDaoImpl;
import dao.impl.CourseDaoImpl;
import model.CourseApprovalUpdate;
import service.CourseCacheService;

import java.util.List;

public class CourseCacheServiceImpl implements CourseCacheService {
    CourseCacheDao cacheDao = new CourseCacheDaoImpl();

    CourseDao courseDao = new CourseDaoImpl();

    @Override
    public CourseApprovalUpdate findById(int courseId) {
        return cacheDao.findById(courseId);
    }

    @Override
    public List<CourseApprovalUpdate> getAll() {
        return cacheDao.getAll();
    }

    @Override
    public int update(int courseId) {
        CourseApprovalUpdate course = findById(courseId);
        return cacheDao.update(course);
    }

    @Override
    public int approval(int courseId) {
        System.out.println("approval ing");
        CourseApprovalUpdate cache = findById(courseId);
        cache.setApproval(1);
        courseDao.update(cache.getCourse());
        return cacheDao.update(cache);
    }

    @Override
    public int unApproval(int CourseId) {
        CourseApprovalUpdate cache = findById(CourseId);
        cache.setApproval(-1);
        return cacheDao.update(cache);
    }
}
