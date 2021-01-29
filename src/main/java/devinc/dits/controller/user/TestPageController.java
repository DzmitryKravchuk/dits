package devinc.dits.controller.user;

import devinc.dits.entity.Answer;
import devinc.dits.entity.Question;
import devinc.dits.entity.Statistic;
import devinc.dits.entity.User;
import devinc.dits.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.ArrayList;
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
    ResultPageController resultController;

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

    private static List<Question> questionList = new ArrayList<>();
    private static int max;
    private static int counter;
    private static List<Statistic> statList = new ArrayList<>();

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
    public String nextTestPage1(@RequestParam(value = "chosenAns") int chosenAnswer, ModelMap modelMap) {
        Question previousQ = questionList.get(counter - 1);
        previousQ = questionService.getFullInfoById(previousQ.getQuestionId());
        if (counter < max) {
            Question actualQ = questionList.get(counter);
            statList.add(configureStatistic(chosenAnswer, previousQ));
            modelMap.addAttribute("question", actualQ.getDescription());
            modelMap.addAttribute("answers",
                    questionService.getAnswersByQuestion(actualQ.getQuestionId()));
            counter++;
            return "User/testPage";
        } else
            statList.add(configureStatistic(chosenAnswer, previousQ));
        return resultController.resultPage(statList, modelMap);
    }

    private Statistic configureStatistic(int chosenAnswer, Question question) {
        User user = userService.getLoggedUser();
        Answer ans = answerService.getById(chosenAnswer);
        Statistic newStatistic = new Statistic();
        newStatistic.setQuestion(question);
        newStatistic.setCorrect(ans.getCorrect());
        newStatistic.setUser(user);
        newStatistic.setDate(LocalDate.now());
        statisticService.save(newStatistic);
        return newStatistic;
    }
}

