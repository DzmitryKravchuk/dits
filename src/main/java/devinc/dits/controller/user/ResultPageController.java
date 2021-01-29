package devinc.dits.controller.user;

import devinc.dits.entity.Literature;
import devinc.dits.entity.Statistic;
import devinc.dits.entity.User;
import devinc.dits.service.LiteratureService;
import devinc.dits.service.QuestionService;
import devinc.dits.service.StatisticService;
import devinc.dits.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
public class ResultPageController {

    @Autowired
    LiteratureService literatureService;

    @GetMapping(value = "/goHome")
    public String goHome() {
        return "User/user";
    }

    @GetMapping(value = "/result")
    public String resultPage(List<Statistic> statList, ModelMap modelMap) {
        modelMap.addAttribute("statistic", statList);

        return "User/resultPage";
    }


}
