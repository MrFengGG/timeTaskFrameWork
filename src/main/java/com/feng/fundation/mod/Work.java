package com.feng.fundation.mod;

/**
 * Created by Feng
 * 工作类模型
 */
public class Work {
    private String name;
    private String className;
    private String description;

    public Work(String name, String className, String description) {
        this.name = name;
        this.className = className;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
