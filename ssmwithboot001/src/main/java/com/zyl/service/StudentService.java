package com.zyl.service;

import com.zyl.po.Student;

import java.util.List;
import java.util.Map;

public interface StudentService {

    List<Student> findStudentList();

    List<Student> findStudentsByName(String name);

    Student findStudentByName(String name);

    Integer updateStudentByName(Map<String,Object> params);



}
