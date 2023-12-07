package model;

//学生状态实体枚举类
public enum StudentStatus {
    GRADUATION("毕业"),
    LEAVE("请假"),
    NORMAL("在读"),
    BREAK("休学"),
    ALL("");

    private String name;
    StudentStatus(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return GRADUATION.name + "," +
                LEAVE.name + "," +
                NORMAL.name + "," +
                BREAK.name + "," +
                ALL.name;
    }
}


