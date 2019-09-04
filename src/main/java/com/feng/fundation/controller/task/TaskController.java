package com.feng.fundation.controller.task;

import com.alibaba.fastjson.JSONObject;
import com.feng.fundation.mod.Task;
import com.feng.fundation.service.task.TaskService;
import com.feng.fundation.service.work.WorkService;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Feng
 */
@RestController
@RequestMapping(value = "/task")
public class TaskController {

    @Autowired
    private TaskService jobService;

    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public List<Task> getAll(String name, String group) throws SchedulerException {
        return jobService.getJob(name,group);
    }

    @RequestMapping(value = "/pause",method = RequestMethod.PATCH)
    public void pause(@RequestBody JSONObject payload) throws SchedulerException {
        jobService.pauseJob(payload.getString("name"),payload.getString("group"));
    }

    @RequestMapping(value = "/resume",method = RequestMethod.PATCH)
    public void resume(@RequestBody JSONObject payload) throws SchedulerException {
        jobService.resumeJob(payload.getString("name"),payload.getString("group"));
    }

    @RequestMapping(value = "/pause-all",method = RequestMethod.PATCH)
    public void pauseAll() throws SchedulerException {
        jobService.pauseAll();
    }

    @RequestMapping(value = "/resume-all",method = RequestMethod.PATCH)
    public void resumeJob() throws SchedulerException {
        jobService.resumeAll();
    }

    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    public void deleteJob(@RequestBody JSONObject payload) throws SchedulerException {
        jobService.deleteJob(payload.getString("name"),payload.getString("group"));
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public void addJob(@RequestBody Task job) throws SchedulerException, IllegalAccessException {
        jobService.addJob(job);
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public void updateJob(@RequestBody Task job) throws SchedulerException, IllegalAccessException {
        jobService.updateJob(job);
    }

}
