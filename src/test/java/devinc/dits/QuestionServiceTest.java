package devinc.dits;

import devinc.dits.config.HibernateConfig;
import devinc.dits.config.WebConfig;
import devinc.dits.entity.Answer;
import devinc.dits.entity.Literature;
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

        String questionName = "New Question Description";

        q = questionService.createQuestionByDescription(questionName, test);

        List<Question> list = questionService.findAll(); // findAll
        int list1Size = list.size();

        qFromBase = questionService.getById(q.getQuestionId()); //get
        assert (qFromBase != null);
        assert (q.equals(qFromBase));

        q.setDescription("new_desc");
        questionService.update(q); //update
        qFromBase = questionService.getQuestionByDescription("new_desc"); //get2
        assert (q.equals(qFromBase));

        questionService.delete(q);
        list = questionService.findAll(); // findAll2
        int list2Size = list.size();
        assert (list1Size == list2Size + 1);

        List<Answer> listA = questionService.getAnswersByQuestion(1);
        System.out.println(listA);

        System.out.println("Достаем полную информацию о вопросе");
        q = questionService.getFullInfoById(1);
        System.out.println(q);

        for(Literature lit : q.getLiteratureSet())
        System.out.printf("название - %s : источник - %s",lit, lit.getLink());
    }
}
