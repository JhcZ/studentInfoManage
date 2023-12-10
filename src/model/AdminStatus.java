package model;
//管理员状态枚举
public enum AdminStatus {
    NORMAL("正常"),
    FREEZE("冻结"),
    UNKNOWN("异常"),
    ALL("");
    AdminStatus(String name) {
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
                ALL.name;
    }
}

