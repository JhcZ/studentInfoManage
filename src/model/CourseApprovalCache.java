package model;

public class CourseApprovalCache {
    private Course course;
    private String approval;  //课程审批状态

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getApproval() {
        return approval;
    }

    public void setApproval(String approval) {
        this.approval = approval;
    }

}
