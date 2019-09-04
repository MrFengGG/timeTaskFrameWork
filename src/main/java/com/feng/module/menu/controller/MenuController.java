package com.feng.module.menu.controller;

import com.feng.module.menu.base.Menu;
import com.feng.module.menu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * Created by Feng
 */
@RestController
@RequestMapping(value = "menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public List<Menu> getAllMenus() throws IOException {
        return menuService.getMenus();
    }
}
