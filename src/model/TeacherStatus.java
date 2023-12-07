package model;

public enum TeacherStatus {
    NORMAL("正常"),
    LEAVE("请假"),
    RESIGNATION("辞职"),
    ALL("");
    private String name;
    TeacherStatus(String name){this.name = name;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return NORMAL.name + "," +
                LEAVE.name + "," +
                RESIGNATION.name + "," +
                ALL.name;
    }
}
