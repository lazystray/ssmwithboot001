package com.zyl.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.zyl.po.Student;
import com.zyl.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    private Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/getAll",produces = "application/json;charset=utf-8")
    public void getAllStudent(HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        //查看当前系统的字符编码方式
        //System.out.println("========"+Charset.defaultCharset().name());
        //查看当前系统的编码方式
        System.out.println("========"+System.getProperty("file.encoding"));
        /*System.setProperty("file.encoding","UTF-8");
        System.out.println("========"+System.getProperty("file.encoding"));*/
        List<Student> studentList = studentService.findStudentList();
        JSONArray jsonArray= JSONArray.parseArray(JSON.toJSONString(studentList));
        logger.info("getAllStudent response {}",jsonArray);
        PrintWriter printWriter = response.getWriter();
        String s = JSON.toJSONString(jsonArray, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue,SerializerFeature.WriteDateUseDateFormat);
        printWriter.write(s);
        printWriter.flush();
        printWriter.close();
    }

    @RequestMapping(value = "/getOne",produces = "application/json;charset=utf-8")
    public void getOneStudent(HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        //查看当前系统的字符编码方式
        //System.out.println("========"+Charset.defaultCharset().name());
        //查看当前系统的编码方式
        System.out.println("========"+System.getProperty("file.encoding"));
        /*System.setProperty("file.encoding","UTF-8");
        System.out.println("========"+System.getProperty("file.encoding"));*/
        List<Student> studentList = studentService.findStudentByName("亚索");
        JSONArray jsonArray= JSONArray.parseArray(JSON.toJSONString(studentList));
        logger.info("getAllStudent response {}",jsonArray);
        PrintWriter printWriter = response.getWriter();
        String s = JSON.toJSONString(jsonArray, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue,SerializerFeature.WriteDateUseDateFormat);
        printWriter.write(s);
        printWriter.flush();
        printWriter.close();
    }

    @RequestMapping(value = "getHome")
    public String getHome(){
        /*ModelAndView modelAndView = new ModelAndView("forward:/WEB-INF/pages/home.jsp");*/
        /*modelAndView.addObject("name", "xxx");*/
        logger.info("home 打印了 ");
        return "jsp/home";
        /*return "home";*/
    }

}
