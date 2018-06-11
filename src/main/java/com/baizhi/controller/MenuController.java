package com.baizhi.controller;

import com.baizhi.entity.Menu;
import com.baizhi.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MenuController {
    @Autowired
    private MenuService menuService;
//菜单栏展示
    @RequestMapping(value="/showm",produces={MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseBody
    public List<Menu> show(){
        List<Menu> menus = menuService.selAll();
        return menus;
    }

}
