package com.feng.fundation.controller.work;

import com.feng.fundation.mod.Work;
import com.feng.fundation.service.work.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * Created by Feng
 */
@RestController
@RequestMapping(value = "/work")
public class WorkController {
    @Autowired
    private WorkService workService;

    @RequestMapping("/get")
    public Set<Work> getWorks() throws ClassNotFoundException {
        return workService.getWorks();
    }
}
