package devinc.dits;

import devinc.dits.config.HibernateConfig;
import devinc.dits.config.WebConfig;
import devinc.dits.entity.Link;
import devinc.dits.entity.Literature;
import devinc.dits.service.LinkService;
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
public class LinkServiceTest extends AbstractTestNGSpringContextTests {
    private LinkService linkService;

    @Autowired
    public void setLinkService(LinkService linkService) {
        this.linkService = linkService;
    }

    @Test
    public void createLink() {
        Link q;
        Link qFromBase;

        Literature literature = new Literature();
        literature.setLiteratureId(1);

        q = new Link();
        q.setLink("www.some_link");
        q.setLiterature(literature);
        linkService.save(q);  // save

        List<Link> list = linkService.findAll(); // findAll
        int list1Size = list.size();

        for (Link t : list) {
            if (t.getLiterature().getLiteratureId() == q.getLiterature().getLiteratureId()
                    && t.getLink().equals(q.getLink())) {
                q.setLinkId(t.getLinkId());
            }
        }

        qFromBase = linkService.getById(q.getLinkId()); //get
        assert(qFromBase!=null);
        assert (q.equals(qFromBase));

        q.setLink("new_link");
        linkService.update(q); //update
        qFromBase = linkService.getById(q.getLinkId()); //get2
        assert (q.equals(qFromBase));

        linkService.delete(q);
        list = linkService.findAll(); // findAll2
        int list2Size = list.size();
        assert (list1Size == list2Size + 1);
    }
}
