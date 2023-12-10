package model;
public class Application {
    private String kind;  //申请类别
    private int applicantId;  //申请人id
    private int courseId;  //申请的课程号

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public int getApplicant() {
        return applicantId;
    }

    public void setApplicant(int applicant) {
        this.applicantId = applicantId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
}
