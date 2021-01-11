package devinc.dits.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StatisticPageController {
    @GetMapping(value = "/goHome")
    public String goHome(){
        return "User/user";
    }
}
