package devinc.dits.controller.user;


import devinc.dits.entity.models.PersonalStatisticModel;
import devinc.dits.entity.Question;
import devinc.dits.entity.Statistic;
import devinc.dits.entity.User;
import devinc.dits.service.QuestionService;
import devinc.dits.service.StatisticService;
import devinc.dits.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Controller
public class PersonalStatisticController {

    StatisticService statisticService;

    UserService userService;

    QuestionService questionService;

    @Autowired
    public void setStatisticService(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setQuestionService(QuestionService questionService) {
        this.questionService = questionService;
    }


    @GetMapping(value = "/personalStatistic")
    public String personalStatisticPage(Model model) {
        User user = userService.getLoggedUser();
        String userName = user.getFirstName() + " " + user.getLastName();

        List<Statistic> userStatList = statisticService.getByUserId(user.getUserId());
        Set<Question> qSet = new HashSet<>();
        List<PersonalStatisticModel> personalStatList = new ArrayList<>();
        for (Statistic stat : userStatList) {
            qSet.add(stat.getQuestion());
        }
        for (Question q : qSet) {
            PersonalStatisticModel pSModel = new PersonalStatisticModel();
            int questionRate = 0;
            int answerCount = 0;
            double correctCount = 0;
            pSModel.setUserName(userName);
            pSModel.setTestName(q.getTest().getName());
            pSModel.setQuestionDescription(q.getDescription());
            for (Statistic stat : userStatList) {
                if (q.getQuestionId() == stat.getQuestion().getQuestionId())
                {
                    answerCount++;
                    if (stat.isCorrect() == true) {
                        correctCount++;
                    }
                }
            }
            questionRate = (int) ((correctCount / answerCount) * 100);
            pSModel.setNumberOfAnswer(answerCount);
            pSModel.setQuestionRate(questionRate);
            personalStatList.add(pSModel);

        }
        model.addAttribute("personalStatList", personalStatList);
        return "User/personalStatistic";
    }
}
