package devinc.dits.controller.user;

import devinc.dits.entity.Test;
import devinc.dits.entity.Topic;
import devinc.dits.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ChooseController {
    TopicService topicService;

    @Autowired
    public void setTopicService(TopicService topicService) {
        this.topicService = topicService;
    }

    @RequestMapping(value = "/userChoose", method = RequestMethod.GET)
    @ResponseBody
    public List<String> l(@RequestParam(value = "topic", required = false) String topic) {
        Topic t = topicService.getByTopicName(topic);
        List<Test> tList = topicService.getTestsByTopic(t.getTopicId());
        return tList.stream().map((test) -> test.getName()).collect(Collectors.toList());
    }

    @GetMapping(value = "/goTest")
    public String gpTest() {
        return "User/testPage";
    }

}
