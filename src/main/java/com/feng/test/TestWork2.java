package com.feng.test;

import com.feng.fundation.mod.annonation.AvailableWork;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Created by Feng
 */
@AvailableWork(name = "测试扫描工作类",description = "测试工作扫描")
public class TestWork2 implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println(("测试工作扫描"));
    }


}
