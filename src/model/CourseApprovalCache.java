package model;

public class CourseApprovalCache {
    private int kind; //课程类别
    private String applicantId; //申请人
    private String cName; //课程名
    private int approval;  //课程审批状态

    public int getKind() {
        return kind;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }

    public String getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(String applicantId) {
        this.applicantId = applicantId;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public int getApproval() {
        return approval;
    }

    public void setApproval(int approval) {
        this.approval = approval;
    }
}
