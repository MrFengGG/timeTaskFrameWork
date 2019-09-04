package com.feng.demo;

import com.feng.fundation.init.TaskProducer;
import com.feng.fundation.mod.Task;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Feng
 */
@Component
public class TestTaskProducer implements TaskProducer {
    @Override
    public List<Task> getInitialJob() {
        List<Task> jobs = new ArrayList();
        Task testJob = new Task("test1","* * * ? * * *","0","test","测试任务1","com.feng.demo.TestWork");
        jobs.add(testJob);
        return jobs;
    }
}
