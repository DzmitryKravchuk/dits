package devinc.dits.controller.user;

import devinc.dits.entity.Question;
import devinc.dits.entity.Statistic;
import devinc.dits.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TestPageController {
    UserService userService;

    TestService testService;

    QuestionService questionService;

    AnswerService answerService;

    StatisticService statisticService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setTestService(TestService testService) {
        this.testService = testService;
    }

    @Autowired
    public void setQuestionService(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Autowired
    public void setAnswerService(AnswerService answerService) {
        this.answerService = answerService;
    }

    @Autowired
    public void setStatisticService(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    private static List<Question> questionList;
    private static int max;
    private static int counter;
    private static Map<String, Statistic> statList = new LinkedHashMap<>();

    @GetMapping(value = "/goTest")
    public String goTest(@RequestParam String testName, ModelMap modelMap) {
        questionList = testService.getQuestionsByTest(testName);
        max = questionList.size();
        counter = 0;
        modelMap.addAttribute("question", questionList.get(counter).getDescription());
        modelMap.addAttribute("answers",
                questionService.getAnswersByQuestion(questionList.get(counter).getQuestionId()));
        counter++;
        return "User/testPage";
    }

    @GetMapping(value = "/nextTestPage")
    public String nextTestPage1(@RequestParam(value = "chosenAns") String chosenAnswer, ModelMap modelMap) {
        if (counter < max) {
            statList.put(questionList.get(counter - 1).getDescription(),configureStatistic(chosenAnswer));
            modelMap.addAttribute("question", questionList.get(counter).getDescription());
            modelMap.addAttribute("answers",
                    questionService.getAnswersByQuestion(questionList.get(counter).getQuestionId()));
            counter++;
            return "User/testPage";
        } else
            return resultPageFill(chosenAnswer, modelMap);
    }

    private String resultPageFill(String chosenAnswer, ModelMap modelMap) {
        return  null;
    }

    private Statistic configureStatistic(String chosenAnswer) {
        return null;
    }
}

