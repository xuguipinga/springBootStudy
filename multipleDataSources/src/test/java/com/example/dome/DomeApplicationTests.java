package com.example.dome;

import com.example.dome.common.entity.Role;
import com.example.dome.server.RoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import javax.annotation.Resource;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
@RunWith(SpringRunner.class)
@SpringBootTest
public class DomeApplicationTests {
 Logger log= LoggerFactory.getLogger(this.getClass());
    @Resource
    private RoleService roleService;

    @Test
    public void contextLoads() {
     Role role= roleService.selectId(5);
     log.info(role.getLineName());
//     assertThat(role.getRoleDesc(), is(""));
     Role role1=roleService.selectIdByBiz(5);
//        assertThat(role1.getRoleDesc(), is(""));
        log.info(role1.getLineName());
    }

}
