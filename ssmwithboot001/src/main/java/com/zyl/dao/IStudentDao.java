package com.zyl.dao;

import com.zyl.po.Student;

import java.util.List;
import java.util.Map;


/**
 * 注意两种查询中获取sqlsession的方法，this.sqlSessionTemplate和this.getSqlSession()
 */
public interface IStudentDao {

    List<Student> findStudentList();

    List<Student> findStudentsByName(String name);

    Student findStudentByName(String name);

    Integer updateStudentByName(Map<String,Object> params);

}
