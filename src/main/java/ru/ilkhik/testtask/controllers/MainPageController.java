package ru.ilkhik.testtask.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.ilkhik.testtask.config.details.UserDetailsImpl;
import ru.ilkhik.testtask.models.User;

@Controller
public class MainPageController {
    @RequestMapping("/")
    public String getMainPage(Authentication authentication, Model model) {
        User user = ((UserDetailsImpl) authentication.getPrincipal()).getUser();
        model.addAttribute("user", user);
        return "main";
    }
}
