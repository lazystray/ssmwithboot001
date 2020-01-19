package com.zyl.dao.impl;

import com.zyl.dao.IStudentDao;
import com.zyl.dao.impl.base.MyBatisBaseDao;
import com.zyl.po.Student;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("studentDao")
public class StudentDaoImpl extends MyBatisBaseDao<Student> implements IStudentDao {

    public StudentDaoImpl() {
        super("com.zyl.dao.IStudentDao");
    }

    @Override
    public List<Student> findStudentList() {
        return super.queryForList("findStudentList",new HashMap<>());
    }

    @Override
    public List<Student> findStudentByName(String name) {
        Map<String,Object> params = new HashMap<>();
        params.put("name",name);
        return super.queryForList("findStudentByName",params);
    }
}
