package com.zyl.web;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.zyl.po.Student;
import com.zyl.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Orine
 * @Date: 2018/10/25
 */
/*@Api()*/
@Controller
@RequestMapping("/index")
public class IndexController {

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "{string}",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String index(@PathVariable("string") String s, HttpServletRequest request) {
        Object ij = request.getAttribute("asd");
        String asd = request.getParameter("asd");
        System.out.println(asd);
        System.out.println(ij);
        logger.info(s);
        /*LOGGER.warn(s);
        LOGGER.error(s);*/
        return s;
    }

    @RequestMapping(value = "ip")
    @ResponseBody
    public String getIp(HttpServletRequest httpServletRequest){
        String ip = httpServletRequest.getRemoteAddr();
        String scheme = httpServletRequest.getScheme();
        logger.info("ip = "+ip+" scheme = "+scheme);
        return ip+scheme;
    }


    @RequestMapping(value = "add",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String add(){
        String name = "露娜";
        Student student = studentService.findStudentByName(name);
        Integer age = student.getAge();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("更新前age:");
        stringBuilder.append(age);
        age++;
        student.setAge(age);
        Map<String,Object> params = new HashMap<>();
        params.put("name",name);
        params.put("age",age);
        Integer integer = studentService.updateStudentByName(params);
        stringBuilder.append("\n\n更新后age:");
        stringBuilder.append(age);
        if (integer>0){
            stringBuilder.append("\n\n更新成功");
        }
        return stringBuilder.toString();
    }


    @RequestMapping(value = "get",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object get() {
        String name = "露娜";
        return studentService.findStudentByName(name);
    }
}

