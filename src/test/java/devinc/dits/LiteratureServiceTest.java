package devinc.dits;

import devinc.dits.config.HibernateConfig;
import devinc.dits.config.WebConfig;
import devinc.dits.entity.Literature;
import devinc.dits.entity.Question;
import devinc.dits.service.LiteratureService;
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
public class LiteratureServiceTest extends AbstractTestNGSpringContextTests {
    private LiteratureService literatureService;

    @Autowired
    public void setLiteratureService(LiteratureService literatureService) {
        this.literatureService = literatureService;
    }

    @Test
    public void createLiterature() {
        Literature q;
        Literature qFromBase;

        Question question = new Question();
        question.setQuestionId(1);

        q = new Literature();
        q.setDescription("desc");
        q.setQuestion(question);

        literatureService.save(q);  // save

        List<Literature> list = literatureService.findAll(); // findAll
        int list1Size = list.size();

        for (Literature t : list) {
            if (t.getQuestion().getQuestionId() == q.getQuestion().getQuestionId()
                    && t.getDescription().equals(q.getDescription())) {
                q.setLiteratureId(t.getLiteratureId());
            }
        }

        qFromBase = literatureService.getById(q.getLiteratureId()); //get
        assert(qFromBase!=null);
        assert (q.equals(qFromBase));

        q.setDescription("new_desc");
        literatureService.update(q); //update
        qFromBase = literatureService.getById(q.getLiteratureId()); //get2
        assert (q.equals(qFromBase));

        literatureService.delete(q);
        list = literatureService.findAll(); // findAll2
        int list2Size = list.size();
        assert (list1Size == list2Size + 1);
    }

}
