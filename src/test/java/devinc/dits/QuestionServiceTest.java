package devinc.dits;

import devinc.dits.config.HibernateConfig;
import devinc.dits.config.WebConfig;
import devinc.dits.entity.Question;
import devinc.dits.service.QuestionService;
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
public class QuestionServiceTest extends AbstractTestNGSpringContextTests {
    private QuestionService questionService;

    @Autowired
    public void setQuestionService(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Test
    public void createQuestion() {
        Question q;
        Question qFromBase;

        devinc.dits.entity.Test test = new devinc.dits.entity.Test();
        test.setTestId(1);

        q = new Question();
        q.setDescription("desc");
        q.setTest(test);

        questionService.save(q);  // save

        List<Question> list = questionService.findAll(); // findAll
        int list1Size = list.size();

        for (Question t : list) {
            if (t.getTest().getTestId() == q.getTest().getTestId()
                    && t.getDescription().equals(q.getDescription())) {
                q.setQuestionId(t.getQuestionId());
            }
        }

        qFromBase = questionService.getById(q.getQuestionId()); //get
        assert (qFromBase != null);
        assert (q.equals(qFromBase));

        q.setDescription("new_desc");
        questionService.update(q); //update
        qFromBase = questionService.getById(q.getQuestionId()); //get2
        assert (q.equals(qFromBase));

        questionService.delete(q);
        list = questionService.findAll(); // findAll2
        int list2Size = list.size();
        assert (list1Size == list2Size + 1);
    }
}
