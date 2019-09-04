import com.SpringBootWebApplication;
import com.feng.fundation.mod.Work;
import com.feng.fundation.service.work.WorkService;
import com.feng.module.menu.service.MenuServiceImpl;
import com.feng.module.menu.base.Menu;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * Created by Feng
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootWebApplication.class)
public class RestfulTest {
    @Autowired
    private WebApplicationContext wac;
    @Autowired
    private WorkService workService;

    private MockMvc mockMvc;

    @Autowired
    private MenuServiceImpl menuService;

    @Before
    public void setUp (){
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void testAddJob() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/addJob")
                .param("name","测试添加任务")
                .param("cronExpress","1/1 * * * * ?")
                .param("status","1")
                .param("group","测试任务")
                .param("description","测试任务1")
                .param("jobClass","com.feng.fundation.test.TestJob"));
    }

    @Test
    public void testMenuService() throws IOException {
        List<Menu> menuList = menuService.getMenus();
        System.out.println(menuList);
    }

    @Test
    public void testTaskClassService(){
        Set<Work> taskClassSet = null;
        try {
            taskClassSet = workService.getWorks();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(taskClassSet);
    }
}
