package devinc.dits;

import devinc.dits.config.HibernateConfig;
import devinc.dits.config.WebConfig;
import devinc.dits.entity.Answer;
import devinc.dits.entity.Question;
import devinc.dits.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;

import java.util.List;

@ComponentScan(basePackages = "devinc.dits")
@ContextConfiguration(classes = {WebConfig.class, HibernateConfig.class})
@WebAppConfiguration
@PropertySource("classpath:db.properties")
@PropertySource(value = "classpath:db.properties")
public class AnswerServiceTest extends AbstractTestNGSpringContextTests {
    private AnswerService answerService;

    @Autowired
    public void setAnswerService(AnswerService answerService) {
        this.answerService = answerService;
    }

    @Test
    public void createAnswer() {
        Answer q;
        Answer qFromBase;

        Question question = new Question();
        question.setQuestionId(1);

        q = new Answer();
        q.setDescription("desc");
        q.setCorrect(true);
        q.setQuestion(question);
        answerService.save(q);  // save

        List<Answer> list = answerService.findAll(); // findAll
        int list1Size = list.size();

        for (Answer t : list) {
            if (t.getQuestion().getQuestionId()==q.getQuestion().getQuestionId()
                    &&t.getDescription().equals(q.getDescription()) ) {
                q.setAnswerId(t.getAnswerId());
            }

            qFromBase = answerService.getById(q.getAnswerId()); //get
            assert(qFromBase!=null);
            assert (q.equals(qFromBase));

            q.setCorrect(false);
            answerService.update(q); //update
            qFromBase = answerService.getById(q.getAnswerId()); //get2
            assert (q.equals(qFromBase));

            answerService.delete(q);
            list = answerService.findAll(); // findAll2
            int list2Size = list.size();
            assert (list1Size == list2Size + 1);

        }
    }
}
