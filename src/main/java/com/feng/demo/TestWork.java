package com.feng.demo;

import com.feng.fundation.mod.annonation.AvailableWork;
    import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * Created by Feng
 */
@AvailableWork(name = "测试工作类",description = "日志打印测试工作工作")
public class TestWork implements Job {
    private final Logger logger= LoggerFactory.getLogger(this.getClass());
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println(new Date() + "测试工作1");
    }
}
