package devinc.dits.controller.tutor;

import devinc.dits.entity.*;
import devinc.dits.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TestOptionsController {
    private TestService testService;
    private QuestionService questionService;

    @Autowired
    public void setTestService(TestService testService) {
        this.testService = testService;
    }

    @Autowired
    public void setQuestionService(QuestionService questionService) {
        this.questionService = questionService;
    }


    @GetMapping(value = "/testOptions")
    public String testOptionsPage(Model model) {
        List<Test> tests = testService.findAll();
        model.addAttribute("tests", tests);
        return "Tutor/testOptionsPage";
    }

    @GetMapping(value = "/question4topic")
    public String question4topic(Model model, HttpServletRequest request) {
        String testName = request.getParameter("testName");
        model.addAttribute("testName", testName);
        List<Question> questions = getQuestionsByTestName(testName);
        model.addAttribute("questions", questions);
        return "Tutor/question4topicPage";
    }

    @GetMapping(value = "/saveQuestion")
    public String saveQuestion(Model model, HttpServletRequest request) {
        String testName = request.getParameter("testName");
        String questionDesc = request.getParameter("question");
        Test test = testService.getByName(testName);
        Question question = new Question();
        question.setTest(test);
        question.setDescription(questionDesc);
        questionService.save(question);

        model.addAttribute("question", question);
        return "Tutor/questionEditPage";
    }

    @GetMapping(value = "/backToQuestion4topic/{questionName}")
    public String backToQuestion4topic(Model model, @PathVariable("questionName") String questionName) {

        Question questionLazy = questionService.getQuestionByDescription(questionName);
        Question question = questionService.getFullInfoById(questionLazy.getQuestionId());
        Test test = testService.getById(question.getTest().getTestId());
        String testName = test.getName();
        List<Question> questions = getQuestionsByTestName(testName);
        model.addAttribute("questions", questions);
        model.addAttribute("testName", testName);
        return "Tutor/question4topicPage";
    }

      private List<Question> getQuestionsByTestName(String testName) {
        List<Question> questionsLazy = testService.getQuestionsByTest(testName);
        List<Question> questions = new ArrayList<>();
        for (Question q : questionsLazy) {
            q = questionService.getFullInfoById(q.getQuestionId());
            questions.add(q);
        }
        return questions;
    }
}
