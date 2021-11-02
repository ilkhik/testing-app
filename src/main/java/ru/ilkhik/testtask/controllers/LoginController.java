package ru.ilkhik.testtask.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/login")
public class LoginController {
    @GetMapping
    public String getLoginPage(Authentication authentication, HttpServletRequest request, Model model) {
        if (authentication != null)
            return "redirect:/";

        if(request.getParameterMap().keySet().contains("error"))
            model.addAttribute("error", true);
        else model.addAttribute("error", false);

        return "login";
    }
}
