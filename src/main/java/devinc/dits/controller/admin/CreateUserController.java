package devinc.dits.controller.admin;

import devinc.dits.entity.Role;
import devinc.dits.entity.User;
import devinc.dits.service.RoleService;
import devinc.dits.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

@Controller
public class CreateUserController {
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

    @PostMapping("/createUser")
    public String addUser(Model model, User user, HttpServletRequest request) {
        Set<Role> roles = new HashSet<>();
        String s[] = request.getParameterValues("chosenRole");
        if (s != null && s.length != 0) {
            for (int i = 0; i < s.length; i++) {
                Role r = roleService.getRoleByName(s[i]);
                roles.add(r);
            }
        }

        user.setRoleList(roles);
        userService.save(user);
        user = new User();
        model.addAttribute("user", user);
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("success", "Пользователь добавлен");
        return "Admin/createUser";
    }

    @GetMapping("/createUser")
    public String createUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("roles", roleService.findAll());
        return "Admin/createUser";
    }
}
