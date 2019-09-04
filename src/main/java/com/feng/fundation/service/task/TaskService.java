package com.feng.fundation.service.task;

import com.feng.fundation.mod.Task;
import org.quartz.SchedulerException;

import java.util.List;

/**
 * Created by Feng
 */
public interface TaskService {
    public List<Task> getJob(String name, String group) throws SchedulerException;

    public void resumeJob(String name, String group) throws SchedulerException;

    public void resumeAll() throws SchedulerException;

    public void pauseJob(String name, String group) throws SchedulerException;

    public void pauseAll() throws SchedulerException;

    public void deleteJob(String name, String group) throws SchedulerException;

    public void addJob(Task job) throws SchedulerException, IllegalAccessException;

    public void updateJob(Task job) throws SchedulerException, IllegalAccessException;
}
