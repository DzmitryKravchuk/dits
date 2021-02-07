package devinc.dits.controller.tutor;

import devinc.dits.entity.Answer;
import devinc.dits.entity.Link;
import devinc.dits.entity.Literature;
import devinc.dits.entity.Question;
import devinc.dits.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class EditQuestionController {

    private QuestionService questionService;
    private LiteratureService literatureService;
    private AnswerService answerService;

       @Autowired
    public void setQuestionService(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Autowired
    public void setLiteratureService(LiteratureService literatureService) {
        this.literatureService = literatureService;
    }

    @Autowired
    public void setAnswerService(AnswerService answerService) {
        this.answerService = answerService;
    }

    @GetMapping(value = "/editQuestion/{id}")
    public String editQuestion(Model model, @PathVariable("id") int id) {
        Question question = questionService.getFullInfoById(id);
        model.addAttribute("question", question);
        return "Tutor/questionEditPage";
    }

    @GetMapping(value = "/deleteLiterature/{id}")
    public String deleteLiterature(Model model, @PathVariable("id") int id) {
        Literature lit = literatureService.getById(id);
        int questionId = lit.getQuestion().getQuestionId();
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

        question = questionService.getFullInfoById(question.getQuestionId());
        model.addAttribute("question", question);

        return "Tutor/questionEditPage";
    }

    @GetMapping(value = "/deleteAnswer/{id}")
    public String deleteAnswer(Model model, @PathVariable("id") int id) {
        Answer answer = answerService.getById(id);
        int questionId = answer.getQuestion().getQuestionId();
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
}
