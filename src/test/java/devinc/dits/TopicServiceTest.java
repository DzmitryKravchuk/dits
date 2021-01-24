package devinc.dits;

import devinc.dits.config.HibernateConfig;
import devinc.dits.config.WebConfig;
import devinc.dits.entity.Topic;
import devinc.dits.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

@ComponentScan(basePackages = "devinc.dits")
@ContextConfiguration(classes = {WebConfig.class, HibernateConfig.class})
@WebAppConfiguration
@PropertySource("classpath:db.properties")
@PropertySource(value = "classpath:db.properties")
public class TopicServiceTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private TopicService topicService;

    @Test
    public void createTopic() {
        Topic q;
        Topic qFromBase;

        String topicName="My new Topic";
        q = topicService.createTopicByName(topicName);  // save
        System.out.println("Id= "+q.getTopicId());

        List<Topic> list = topicService.findAll(); // findAll
        int list1Size = list.size();

        qFromBase = topicService.getByTopicName(topicName);
        assert (q.getDescription().equals(qFromBase.getDescription()));

        qFromBase = topicService.getById(q.getTopicId()); //get
        assert (q.equals(qFromBase));

        q.setName("new name");
        topicService.update(q); //update
        qFromBase = topicService.getById(q.getTopicId()); //get2
        assert (q.equals(qFromBase));

        topicService.delete(q);
        list = topicService.findAll(); // findAll2
        int list2Size = list.size();
        assert (list1Size == list2Size + 1);

        Topic t = topicService.getByTopicName("Java core");
        List<devinc.dits.entity.Test> tList = topicService.getTestsByTopic(t.getTopicId());
        List<String> lis= tList.stream().map((test)->test.getName()).collect(Collectors.toList());
        System.out.println(lis);
    }

}
