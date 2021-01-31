package devinc.dits.controller.user;

import devinc.dits.entity.Test;
import devinc.dits.entity.Topic;
import devinc.dits.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ChooseController {
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

    @RequestMapping(value = "/userChoose", method = RequestMethod.POST)
    @ResponseBody
    public List<String> chooseTestByTopic(@RequestParam(value = "topic", required = false) String topic) {
        Topic t = topicService.getByTopicName(topic);
        List<Test> tList = topicService.getTestsByTopic(t.getTopicId());
        return tList.stream().map((test) -> test.getName()).collect(Collectors.toList());
    }

}
