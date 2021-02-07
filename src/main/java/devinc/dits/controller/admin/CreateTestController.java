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

    @GetMapping("/saveNewTest")
    public String addTest(@RequestParam(name = "topic") String topicName, @RequestParam(name = "test") String testName, Model model) {
        Test completed = createNewTest(topicName, testName);
        model.addAttribute("topics", topicService.findAll());
        model.addAttribute("tests", testService.findAll());

        model.addAttribute("success", "Добавлен тест: " + completed.toString());
        return "Admin/createTest";
    }

    private Test createNewTest(String topicName, String testName) {
        Topic topic = topicService.createTopicByName(topicName);
        Test test = testService.createTestByName(testName, topic);

        return test;
    }


}
