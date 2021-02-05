package devinc.dits.controller.tutor;

import devinc.dits.entity.*;
import devinc.dits.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.management.loading.MLetContent;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TestOptionsController {
    private TestService testService;
    private QuestionService questionService;
    private LiteratureService literatureService;
    private LinkService linkService;
    private AnswerService answerService;

    @Autowired
    public void setTestService(TestService testService) {
        this.testService = testService;
    }

    @Autowired
    public void setQuestionService(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Autowired
    public void setLiteratureService(LiteratureService literatureService) {
        this.literatureService = literatureService;
    }

    @Autowired
    public void setLinkService(LinkService linkService) {
        this.linkService = linkService;
    }

    @Autowired
    public void setAnswerService(AnswerService answerService) {
        this.answerService = answerService;
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

    @GetMapping(value = "/backToQuestion4topic/{questionName}")
    public String backToQuestion4topic(Model model, @PathVariable("questionName") String questionName) {

        Question questionLazy = questionService.getQuestionByDescription(questionName);
        Question question = questionService.getFullInfoById(questionLazy.getQuestionId());
        Test test = testService.getById(question.getTest().getTestId());
        String testName = test.getName();
        List<Question> questions = getQuestionsByTestName(testName);
        model.addAttribute("questions", questions);
        return "Tutor/question4topicPage";
    }

    @GetMapping(value = "/editQuestion/{id}")
    public String editQuestion(Model model, @PathVariable("id") int id) {
        Question question = questionService.getFullInfoById(id);
        model.addAttribute("question", question);
        return "Tutor/questionEditPage";
    }

    @GetMapping(value = "/deleteLiterature/{id}")
    public String deleteLiterature(Model model, @PathVariable("id") int id, @RequestParam(name = "questionId") int questionId) {
        Literature lit = literatureService.getById(id);
        literatureService.delete(lit);
        Question question = questionService.getFullInfoById(questionId);
        model.addAttribute("question", question);

        return "Tutor/questionEditPage";
    }

    @GetMapping(value = "/saveNewLiterature")
    public String saveNewLiterature(Model model, @RequestParam(name = "questionId") int questionId, @RequestParam(name = "literature") String litDesc, @RequestParam(name = "link") String linkDesc) {
        Question question = new Question();
        question.setQuestionId(questionId);
        Literature lit = new Literature();
        lit.setDescription(litDesc);
        lit.setQuestion(question);

        Link link = new Link();
        link.setLink(linkDesc);
        link.setLiterature(lit);
        lit.setLink(link); // recursive

        literatureService.save(lit);
        //    linkService.save(link);

        question = questionService.getFullInfoById(question.getQuestionId());
        model.addAttribute("question", question);

        return "Tutor/questionEditPage";
    }

    @GetMapping(value = "/deleteAnswer/{id}")
    public String deleteAnswer(Model model, @PathVariable("id") int id, @RequestParam(name = "questionId") int questionId) {
        Answer answer = answerService.getById(id);
        answerService.delete(answer);
        Question question = questionService.getFullInfoById(questionId);
        model.addAttribute("question", question);

        return "Tutor/questionEditPage";
    }

    @GetMapping(value = "/saveNewAnswer")
    public String saveNewAnswer(Model model, @RequestParam(name = "questionId") int questionId, @RequestParam(name = "answer") String answer, HttpServletRequest request) {
        Question question = new Question();
        question.setQuestionId(questionId);
        Answer ans = new Answer();
        ans.setQuestion(question);
        ans.setDescription(answer);
        boolean correct= false;
        if (request.getParameter("correct")!=null) {
            correct = true;
        }
        ans.setCorrect(correct);

        answerService.save(ans);

        question = questionService.getFullInfoById(questionId);
        model.addAttribute("question", question);

        return "Tutor/questionEditPage";
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
