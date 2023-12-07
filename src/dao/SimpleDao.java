package dao;

import java.util.List;

public interface SimpleDao<T> {
    T findById(int id);  //通过id查找某一实体类的信息
    List<T> query(T condition);  //模糊查询
    List<T> query(T condition,int start,int num);  //模糊分页查询

    int insert(T t);  //增加某一实体类的某一数据
    int update(T t);  //更新某一实体类的某一数据
    int delete(int id);  //删除某一实体类的某一数据

    int count();  //对某一实体类的数据计数
    int count(T condition);  //根据某一条件对某一实体类的数据计数
}
