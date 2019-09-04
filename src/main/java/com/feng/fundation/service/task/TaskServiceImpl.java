package com.feng.fundation.service.task;

import com.feng.fundation.mod.Task;
import com.feng.fundation.util.QuartzUtil;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Feng
 */
@Component
public class TaskServiceImpl implements TaskService {
    @Autowired
    private Scheduler scheduler;

    private final Logger logger= LoggerFactory.getLogger(this.getClass());

    @Override
    public List<Task> getJob(String name, String group) throws SchedulerException {
        List<Task> jobs = new ArrayList<>();
        GroupMatcher<JobKey> matcher = StringUtils.isEmpty(group) ? GroupMatcher.anyJobGroup() : GroupMatcher.groupEndsWith(group);
        Set<JobKey> jobKeySet = scheduler.getJobKeys(matcher);
        for (JobKey jobKey : jobKeySet){
            JobDetail jobDetail = scheduler.getJobDetail(jobKey);
            Task job = (Task) jobDetail.getJobDataMap().get("jobDetail");
            job.setStatus(scheduler.getTriggerState(TriggerKey.triggerKey(jobKey.getName(),jobKey.getGroup())).toString());
            if(StringUtils.isEmpty(name) || name.equals(job.getName())) {
                jobs.add((Task) jobDetail.getJobDataMap().get("jobDetail"));
            }
        }
        return jobs;
    }

    @Override
    public void pauseJob(String name, String group) throws SchedulerException {
        if(!isEmpty(name,group)){
            scheduler.pauseJob(JobKey.jobKey(name,group));
        }
    }

    @Override
    public void pauseAll() throws SchedulerException {
        scheduler.pauseAll();
    }

    @Override
    public void resumeJob(String name, String group) throws SchedulerException {
        scheduler.resumeJob(JobKey.jobKey(name,group));
    }

    @Override
    public void resumeAll() throws SchedulerException {
        scheduler.resumeAll();
    }

    @Override
    public void deleteJob(String name, String group) throws SchedulerException {
        scheduler.deleteJob(JobKey.jobKey(name,group));
    }

    @Override
    public void addJob(Task job) throws SchedulerException, IllegalAccessException {
        JobDetail jobDetail = QuartzUtil.buildJobDetail(job);
        Trigger trigger = QuartzUtil.buildCronTrigger(job);
        scheduler.scheduleJob(jobDetail,trigger);
    }

    @Override
    public void updateJob(Task job) throws SchedulerException, IllegalAccessException {
        Trigger trigger = QuartzUtil.buildCronTrigger(job);
        TriggerKey triggerKey = TriggerKey.triggerKey(job.getName(), job.getGroup());
        scheduler.rescheduleJob(triggerKey,trigger);
    }

    private boolean isEmpty(String... values){
        boolean isEmpty = false;
        for(String value:values){
            if(StringUtils.isEmpty(value)){
                isEmpty = true;
            }
        }
        return isEmpty;
    }

}
