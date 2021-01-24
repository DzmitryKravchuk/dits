package devinc.dits.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {

    @RequestMapping(value = "/testPage", method = RequestMethod.GET) // получение формы
    public ModelAndView testPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Admin/testPage");
        return modelAndView;
    }

}
