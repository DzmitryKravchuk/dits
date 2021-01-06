package devinc.dits;

import devinc.dits.config.HibernateConfig;
import devinc.dits.config.WebConfig;
import devinc.dits.entity.Topic;
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
public class TopicServiceTest extends AbstractTestNGSpringContextTests {

    private TopicService topicService;

    @Autowired
    public void setTopicService(TopicService topicService) {
        this.topicService = topicService;
    }

    @Test
    public void createTopic() {
        Topic q;
        Topic qFromBase;

        q = new Topic();
        q.setDescription("desc");
        q.setName("name");
        topicService.save(q);  // save

        List<Topic> list = topicService.findAll(); // findAll
        int list1Size = list.size();

        for (Topic t : list
        ) {
            if (t.getName().equals(q.getName())){
                q.setTopicId(t.getTopicId());
            }
        }
        qFromBase = topicService.getById(q.getTopicId()); //get
        assert (q.equals(qFromBase));

        q.setName("new name");
        topicService.update(q); //update
        qFromBase = topicService.getById(q.getTopicId()); //get2
        assert (q.equals(qFromBase));

        topicService.delete(q);
        list = topicService.findAll(); // findAll2
        int list2Size = list.size();
        assert (list1Size == list2Size+1);

    }

}
