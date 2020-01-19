package com.zyl.service;

import com.zyl.po.Student;

import java.util.List;

public interface StudentService {

    List<Student> findStudentList();

    List<Student> findStudentByName(String name);

}
