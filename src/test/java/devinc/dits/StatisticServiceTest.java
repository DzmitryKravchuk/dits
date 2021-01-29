package devinc.dits;

import devinc.dits.config.HibernateConfig;
import devinc.dits.config.WebConfig;
import devinc.dits.entity.Question;
import devinc.dits.entity.Statistic;
import devinc.dits.entity.User;
import devinc.dits.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.List;

@ComponentScan(basePackages = "devinc.dits")
@ContextConfiguration(classes = {WebConfig.class, HibernateConfig.class})
@WebAppConfiguration
@PropertySource("classpath:db.properties")
@PropertySource(value = "classpath:db.properties")
public class StatisticServiceTest extends AbstractTestNGSpringContextTests {
    private StatisticService statisticService;

    @Autowired
    public void setStatisticService(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @Test
    public void createStatistic() {
        Statistic q;
        Statistic qFromBase;

        User user = new User();
        user.setUserId(1);

        Question question = new Question();
        question.setQuestionId(1);

        q = new Statistic();
        q.setDate(LocalDate.now());
        q.setUser(user);
        q.setQuestion(question);
        q.setCorrect(true);
        statisticService.save(q);  // save

        List<Statistic> list = statisticService.findAll(); // findAll
        int list1Size = list.size();

        for (Statistic t : list
        ) {
            if (t.getQuestion().getQuestionId() == q.getQuestion().getQuestionId()
                    && t.getUser().getUserId() == q.getUser().getUserId()) {
                q.setStatisticId(t.getStatisticId());
            }
        }

        qFromBase = statisticService.getById(q.getStatisticId()); //get
        assert (qFromBase != null);
        assert (q.equals(qFromBase));

        q.setCorrect(false);
        statisticService.update(q); //update
        qFromBase = statisticService.getById(q.getStatisticId()); //get2
        assert (q.equals(qFromBase));

        statisticService.delete(q);
        list = statisticService.findAll(); // findAll2
        int list2Size = list.size();
        assert (list1Size == list2Size + 1);

        List<Statistic> statList = statisticService.getByUserId(4);
        int i = 1;
        for (Statistic stat : statList) {
            System.out.println("Statistic Number: "+ i +" - "+stat);
            System.out.println(stat.getUser());
            System.out.println(stat.getQuestion());
            System.out.println(stat.getQuestion().getTest());
            System.out.println(".........................");
            i++;
        }
    }
}
