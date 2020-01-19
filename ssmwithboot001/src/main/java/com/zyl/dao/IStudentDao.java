package com.zyl.dao;

import com.zyl.po.Student;

import java.util.List;

public interface IStudentDao {

    List<Student> findStudentList();

    List<Student> findStudentByName(String name);

}
