package com.feng.fundation.util;

import com.feng.fundation.mod.Task;
import org.quartz.*;

/**
 * Created by Feng
 */
public class QuartzUtil {
    public static JobDetail buildJobDetail(Task job) throws IllegalAccessException{
        JobDetail jobDetail = null;
        try {
            Class jobClass = Class.forName(job.getJobClass());
            jobDetail = JobBuilder.newJob(jobClass).withIdentity(job.getName(), job.getGroup()).withDescription(job.getDescription()).build();
        } catch (ClassNotFoundException e) {
            throw new IllegalAccessException("非法的工作类:"+job.getJobClass());
        }
        job.setStatus("1");
        jobDetail.getJobDataMap().put("jobDetail", job);
        return jobDetail;
    }

    public static CronTrigger buildCronTrigger(Task job) throws IllegalAccessException {
        return TriggerBuilder.newTrigger().withIdentity(job.getName(), job.getGroup()).withSchedule(buildCronScheduleBuilder(job.getCronExpress())).build();
    }

    public static CronScheduleBuilder buildCronScheduleBuilder(String cronExpress) throws IllegalAccessException{
        CronScheduleBuilder scheduleBuilder;
        try {
            scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpress);
        } catch (RuntimeException e) {
            throw new IllegalAccessException("非法的时间表达式:" + cronExpress);
        }
        return scheduleBuilder;
    }
}
