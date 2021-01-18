package devinc.dits.controller.user;

import devinc.dits.entity.Topic;
import devinc.dits.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class StartController {
    TopicService topicService;

    @Autowired
    public void setTopicService(TopicService topicService) {
        this.topicService = topicService;
    }

    @RequestMapping(value = "/chooseTest", method = RequestMethod.GET) // получение формы
    public ModelAndView chooseTest() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("User/userChoose");
        List<Topic> topics = topicService.findAll();
        modelAndView.addObject("topics", topics);
        return modelAndView;
    }

    @GetMapping(value = "/personalStatistic")
    public String personalStatisticPage() {
        return "User/personalStatistic";
    }
}
