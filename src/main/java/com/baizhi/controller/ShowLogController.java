package com.baizhi.controller;

import com.baizhi.entity.Log;
import com.baizhi.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value="/log")
public class ShowLogController {
    @Autowired
    LogService logService;
    @RequestMapping(value="/show",produces ={MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseBody
    public List<Log> showLog(){
        List<Log> logs = logService.selAll();
        for (Log log : logs) {
            System.out.println(log);
        }
        return logs;
    }
}
