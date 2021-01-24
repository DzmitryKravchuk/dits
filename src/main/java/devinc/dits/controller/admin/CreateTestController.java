package devinc.dits.controller.admin;

import devinc.dits.entity.Question;
import devinc.dits.entity.Test;
import devinc.dits.entity.Topic;
import devinc.dits.service.QuestionService;
import devinc.dits.service.TestService;
import devinc.dits.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CreateTestController {
    TopicService topicService;
    TestService testService;
    QuestionService questionService;

    @Autowired
    public void setTopicService(TopicService topicService) {
        this.topicService = topicService;
    }

    @Autowired
    public void setTestService(TestService testService) {
        this.testService = testService;
    }

    @Autowired
    public void setQuestionService(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping(value = "/createTest")
    public String createTest(Model model) {
        model.addAttribute("topics", topicService.findAll());
        model.addAttribute("tests", testService.findAll());
        model.addAttribute("questions", questionService.findAll());
        return "Admin/createTest";
    }

    @GetMapping("/saveNewQuestion")
    public String addTest(@RequestParam(name = "topic") String topicName, @RequestParam(name = "test") String testName, @RequestParam(name = "question") String questionName, Model model) {
        Question completed = createNewQuestion(topicName, testName, questionName);
        model.addAttribute("topics", topicService.findAll());
        model.addAttribute("tests", testService.findAll());
        model.addAttribute("questions", questionService.findAll());
        model.addAttribute("success", "Добавлен вопрос: " + completed.toString());
        return "Admin/createTest";
    }

    private Question createNewQuestion(String topicName, String testName, String questionName) {
        Topic topic = topicService.createTopicByName(topicName);
        Test test = testService.createTestByName(testName, topic);
        Question question = questionService.createQuestionByDescription(questionName, test);
        return question;
    }


}
