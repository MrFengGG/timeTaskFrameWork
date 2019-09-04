package com.feng.fundation.config;

import com.feng.fundation.base.AutowiredJobFactory;
import com.feng.fundation.init.TaskProducer;
import com.feng.fundation.mod.Task;
import com.feng.fundation.util.QuartzUtil;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Feng
 */
@Configuration
public class SchedledConfiguration {
    @Autowired
    private AutowiredJobFactory autowiredJobFactory;
    @Autowired
    private TaskProducer initialJobProducer;

    private final Logger logger= LoggerFactory.getLogger(this.getClass());

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setOverwriteExistingJobs(true);
        schedulerFactoryBean.setJobFactory(autowiredJobFactory);
        return schedulerFactoryBean;
    }

    @Bean
    public Scheduler scheduler() throws SchedulerException {
        List<Task> jobs = initialJobProducer.getInitialJob();
        Scheduler scheduler = schedulerFactoryBean().getScheduler();
        for (Task job : jobs) {
            TriggerKey triggerKey = TriggerKey.triggerKey(job.getName(), job.getGroup());
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            try{
                if (null == trigger) {
                    scheduler.scheduleJob(QuartzUtil.buildJobDetail(job), QuartzUtil.buildCronTrigger(job));
                } else {
                    trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(QuartzUtil.buildCronScheduleBuilder(job.getCronExpress())).build();
                    scheduler.rescheduleJob(triggerKey, trigger);
                }
            }catch (IllegalAccessException e) {
                logger.error("新建工作"+job+"异常,reason:"+e.getMessage());
                continue;
            }
        }
        scheduler.start();
        initialJobProducer = null;
        return scheduler;
    }
}
