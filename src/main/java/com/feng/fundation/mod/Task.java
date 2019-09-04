package com.feng.fundation.mod;

/**
 * Created by Feng
 * 任务模型
 */
public class Task {
    private String name;

    private String cronExpress;

    private String status;

    private String group;

    private String description;

    private String jobClass;

    public Task() {
    }

    public Task(String name, String cronExpress, String status, String group, String description, String jobClass) {
        this.name = name;
        this.cronExpress = cronExpress;
        this.status = status;
        this.group = group;
        this.description = description;
        this.jobClass = jobClass;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCronExpress() {
        return cronExpress;
    }

    public void setCronExpress(String cronExpress) {
        this.cronExpress = cronExpress;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getJobClass() {
        return jobClass;
    }

    public void setJobClass(String jobClass) {
        this.jobClass = jobClass;
    }
}
