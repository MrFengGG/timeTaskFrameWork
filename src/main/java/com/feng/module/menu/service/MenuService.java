package com.feng.module.menu.service;

import com.feng.module.menu.base.Menu;

import java.io.IOException;
import java.util.List;

/**
 * Created by Feng
 */
public interface MenuService {
    public List<Menu> getMenus() throws IOException;

    public List<Menu> reloadMenus() throws IOException;
}
