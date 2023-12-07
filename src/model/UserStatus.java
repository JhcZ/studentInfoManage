package model;
    //用户状态枚举
public enum UserStatus {
    NORMAL("正常"),
    FREEZE("冻结"),
    UNKNOWN("异常"),
    GRADUATION("毕业"),
    LEAVE("请假"),
    BREAK("休学"),
    ALL("");
    UserStatus(String name) {
        this.name = name;
    }
    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return NORMAL.name + "," +
                FREEZE.name + "," +
                UNKNOWN.name + "," +
                GRADUATION.name + "," +
                LEAVE.name + "," +
                BREAK.name + "," +
                ALL.name;
    }
}

