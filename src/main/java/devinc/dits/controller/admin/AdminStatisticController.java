package devinc.dits.controller.admin;

import devinc.dits.service.QuestionService;
import devinc.dits.service.StatisticService;
import devinc.dits.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminStatisticController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private TestService testService;

    @Autowired
    private StatisticService statisticService;

    @GetMapping(value="/adminStatistic")
    public String showStatisticPage(){
        return "Admin/adminStatistic";
    }

    @GetMapping(value = "/goBackToStatistic")
    public String goBackToStatistic(){
        return "Admin/adminStatistic";
    }

    @GetMapping(value = "/statistic/questionStatisticPage")
    public String showQuestionStatistic(Model model) {
        model.addAttribute("questionStatistic", questionService.getQuestionStatisticList());
        return "Admin/statistic/questionStatisticPage";
    }

    @GetMapping(value = "/statistic/testStatisticPage")
    public String showTestStatistic(Model model) {
        model.addAttribute("testStatistic", testService.getTestStatisticList());
        return "Admin/statistic/testStatisticPage";
    }

    @GetMapping(value = "/statistic/userStatisticPage")
    public String showUserStatistic(Model model) {
        model.addAttribute("userStatistic", statisticService.getAllUserStatistic());
        return "Admin/statistic/userStatisticPage";
    }

}
