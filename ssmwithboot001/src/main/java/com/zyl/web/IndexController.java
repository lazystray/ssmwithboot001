package com.zyl.web;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: Orine
 * @Date: 2018/10/25
 */
/*@Api()*/
@Controller
@RequestMapping("/index")
public class IndexController {

    private static final Logger LOGGER = Logger.getLogger(IndexController.class);

    @RequestMapping(value = "{string}",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String index(@PathVariable("string") String s) {
        LOGGER.info(s);
        /*LOGGER.warn(s);
        LOGGER.error(s);*/
        return s;
    }
}

