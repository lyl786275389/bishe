package com.ssm.gbq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("")
public class IndexController {
    @RequestMapping({ "/login.zhtml"})
    public ModelAndView login() {
        return new ModelAndView("login");
    }
}
