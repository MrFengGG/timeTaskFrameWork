package com.feng.module.menu.service;

import com.alibaba.fastjson.JSONObject;
import com.feng.module.menu.base.Menu;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import sun.misc.IOUtils;

import java.io.IOException;
import java.util.List;

/**
 * Created by Feng
 */
@Component
public class MenuServiceImpl implements MenuService {
    @Value("classpath:menu/menu.json")
    private Resource menuResource;

    private static List<Menu> menus;

    public List<Menu> reloadMenus() throws IOException {
        String menuJson = new String(IOUtils.readFully(menuResource.getInputStream(),-1,true));
        menus = JSONObject.parseArray(menuJson,Menu.class);
        return menus;
    }

    public List<Menu> getMenus() throws IOException {
        if(menus == null){
            synchronized (this.getClass()){
                if(menus == null){
                    return reloadMenus();
                }
            }
        }
        return menus;
    }
}
