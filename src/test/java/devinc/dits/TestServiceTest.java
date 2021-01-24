package devinc.dits;

import devinc.dits.config.HibernateConfig;
import devinc.dits.config.WebConfig;
import devinc.dits.entity.Question;
import devinc.dits.entity.Topic;
import devinc.dits.service.TestService;
import devinc.dits.service.TopicService;
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
public class TestServiceTest extends AbstractTestNGSpringContextTests {
    private TestService testService;

    @Autowired
    public void setTestService(TestService testService) {
        this.testService = testService;
    }


    @Test
    public void createTest() {
        devinc.dits.entity.Test q;
        devinc.dits.entity.Test qFromBase;

        Topic topic = new Topic();
        topic.setTopicId(1);


        String testName = "New Test Name";
        q = testService.createTestByName(testName,topic);

        List<devinc.dits.entity.Test> list = testService.findAll(); // findAll
        int list1Size = list.size();

 //       for (devinc.dits.entity.Test t : list
  //      ) {
  //          if (t.getName().equals(q.getName())) {
  //              q.setTestId(t.getTestId());
   //         }
  //      }

        qFromBase = testService.getById(q.getTestId()); //get
        assert (q.equals(qFromBase));

        q.setName("new name");
        testService.update(q); //update
        qFromBase = testService.getById(q.getTestId()); //get2
        assert (q.equals(qFromBase));

        testService.delete(q);
        list = testService.findAll(); // findAll2
        int list2Size = list.size();
        assert (list1Size == list2Size + 1);

        List<Question> listQ = testService.getQuestionsByTest("Java core test");
        System.out.println(listQ);
    }
}
