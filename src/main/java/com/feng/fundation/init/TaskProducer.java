package com.feng.fundation.init;

import com.feng.fundation.mod.Task;

import java.util.List;

/**
 * Created by Feng
 * must realize to provide beginning job
 */
public interface TaskProducer {
    public List<Task> getInitialJob();
}
