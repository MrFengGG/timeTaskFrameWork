## timeTaskFrameWork
基于quartz的定时任务框架,提供动态查询,新增,修改,删除任务的接口和前端页面
## 构建
构建timeTaskFrameWork需要maven工具
### 依赖
* Java JDK1.8  
* Maven,version 3+
### 构建
    $ mvn clear install
   
## 运行
    $ java -jar xxx
默认访问路径为localhost:8001

## 开发
### 定义任务
定义一个任务非常简单,只需要实现`org.quartz.Job`接口,并使用`AvailableWork`注解用于将任务纳入管理中,就可以对这个任务进行动态管理了  
例：
    
    @AvailableWork(name = "测试工作类",description = "日志打印测试工作工作")
    public class TestWork implements Job {
        private final Logger logger= LoggerFactory.getLogger(this.getClass());
        @Override
        public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
            System.out.println(new Date() + "测试工作1");
        }
    }
### 初始化任务
用户需要实现`TaskProducer`接口来初始化想要在项目启动时就开始调度的任务,未被初始化的任务也可以调用接口来创建  
例：

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
   

