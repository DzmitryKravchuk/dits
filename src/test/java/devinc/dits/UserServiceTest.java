package devinc.dits;

import devinc.dits.config.HibernateConfig;
import devinc.dits.config.WebConfig;
import devinc.dits.entity.Role;
import devinc.dits.entity.User;
import devinc.dits.repository.UserRepository;
import devinc.dits.service.RoleService;
import devinc.dits.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;

@ComponentScan(basePackages = "devinc.dits")
@ContextConfiguration(classes = {WebConfig.class, HibernateConfig.class})
@WebAppConfiguration
@PropertySource("classpath:db.properties")
@PropertySource(value = "classpath:db.properties")
public class UserServiceTest extends AbstractTestNGSpringContextTests {

    private UserService userService;
    private RoleService roleService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Test
    public void createUser() {

        User q;
        User qFromBase;

        Role role1 = new Role();
        HashSet<Role> set = new HashSet<Role>();
        role1.setRoleId(1);
        set.add(role1);
        Role role2 = new Role();
        role2.setRoleId(2);
        set.add(role2);

        q = new User();
        q.setFirstName("UserName");
        q.setLastName("LastName");
        q.setLogin("Login");
        q.setPassword("1111");
        q.setRoleList(set);
        userService.save(q);  // save

        Role roleFromBase1 = roleService.getRoleByName("admin");
        Role roleFromBase2 = roleService.getById(1);
        assert (roleFromBase1.equals(roleFromBase2));


        List<User> list = userService.findAll(); // findAll
        int list1Size = list.size();

        //qFromBase = userService.getById(q.getUserId()); //get
        qFromBase = userService.getByLogin("Login");
        assert (q.equals(qFromBase));

        q.setFirstName("new name");
        userService.update(q); //update
        qFromBase = userService.getById(q.getUserId()); //get2
        assert (q.equals(qFromBase));

        userService.delete(q);
        list = userService.findAll(); // findAll2
        int list2Size = list.size();
        assert (list1Size == list2Size + 1);
    }
}
