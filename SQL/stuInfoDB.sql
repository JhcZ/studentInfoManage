# create database stuinfodb;

use stuinfodb;

create table if not exists admin_table
(
    id             int auto_increment
        primary key,
    name           varchar(10) not null comment '管理员名称',
    password       varchar(40) not null comment '管理员密码',
    createTime     bigint      null comment '创建时间',
    lastAccessTime bigint      null comment '最近一次登录时间',
    status         varchar(10) null comment '状态'
)
    charset = utf8;

create table if not exists application_course_cache
(
    courseId       int          not null,
    name           varchar(10)  null,
    teacher        int          null,
    location       varchar(15)  null,
    courseDuration varchar(15)  null,
    flag           varchar(5)   null,
    classes        varchar(100) null,
    startTime      date         null,
    semester       int          null,
    numOfStu       int          null,
    approval       int          null,
    primary key (courseId)
);

create table if not exists course_application
(
    kind        varchar(10) null comment '申请类别',
    applicantId int         not null comment '申请人',
    cId         int         null comment '课程号',
    primary key (applicantId)
);

create table if not exists student_table
(
    studentId  int         not null comment '学号',
    name       varchar(15) not null comment '学生姓名',
    major      varchar(10) not null comment '专业',
    department varchar(15) null comment '所属学院',
    sex        varchar(2)  null comment '性别',
    className  varchar(10) null comment '学生所在班级',
    phone      varchar(15) null comment '联系电话',
    status     varchar(5)  null comment '状态',
    primary key (studentId)
);

create table if not exists teacher_table
(
    teacherId  int     auto_increment    not null comment '教师号',
    password   varchar(32) not null comment '密码',
    name       varchar(10) not null comment '教师姓名',
    department varchar(15) not null comment '所属学院',
    degree     varchar(10) null comment '学位',
    sex        varchar(2)  null comment '性别',
    phone      varchar(15) null comment '联系电话',
    email      varchar(20) null comment '邮箱',
    createTime date        null comment '述职时间',
    primary key (teacherId)
);

create table if not exists course_table
(
    courseId       int          not null comment '课程号',
    name           varchar(10)  null comment '课程名称',
    teacher        int          null comment '授课教师',
    location       varchar(15)  null comment '上课地点',
    courseDuration varchar(15)  null comment '上课周数',
    flag           varchar(5)   null comment '判断课程类别',
    classDay       int          null comment '上课日期(周几)',
    classTime      varchar(32)  null comment '上课时间(哪几节)',
    startTime      date         null comment '开课时间',
    semester       int          null comment '开课学期',
    numOfStu       int          null comment '选课人数',
    primary key (courseId),
    constraint course_table_teacher_table_teacherId_fk
        foreign key (teacher) references teacher_table (teacherId)
);

create table if not exists score_table
(
    id       int auto_increment
        primary key,
    sId      int    null comment '学号',
    cId      int    not null comment '课程号',
    maxScore double null comment '课程最高分数',
    minScore double null comment '课程最低分数',
    passed   int    null comment '课程通过人数',
    grade    int    null comment '成绩',
    constraint score_table_course_table_courseId_fk
        foreign key (cId) references course_table (courseId),
    constraint score_table_student_table_studentId_fk
        foreign key (sId) references student_table (studentId)
);

create table if not exists stu_choose
(
    id    int auto_increment
        primary key,
    stuId int null,
    cId   int null,
    sId   int null comment '成绩号',
    constraint stu_choose_course_table_courseId_fk
        foreign key (cId) references course_table (courseId),
    constraint stu_choose_score_table_id_fk
        foreign key (sId) references score_table (id),
    constraint stu_choose_student_table_studentId_fk
        foreign key (stuId) references student_table (studentId)
);


