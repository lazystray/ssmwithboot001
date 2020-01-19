package com.zyl.service.impl;

import com.zyl.dao.IStudentDao;
import com.zyl.po.Student;
import com.zyl.service.StudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("studentService")
public class StudentServiceImpl implements StudentService {

    @Resource
    private IStudentDao studentDao;

    @Override
    public List<Student> findStudentList() {
        return studentDao.findStudentList();
    }

    @Override
    public List<Student> findStudentByName(String name) {
        return studentDao.findStudentByName(name);
    }


}
