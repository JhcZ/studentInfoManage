package model;

public class CourseApprovalUpdate {
    private Course course;
    private int approval;  //课程审批状态

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getApproval() {
        return approval;
    }

    public void setApproval(int approval) {
        this.approval = approval;
    }
}
