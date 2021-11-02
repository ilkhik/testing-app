package ru.ilkhik.testtask.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.ilkhik.testtask.forms.UserForm;
import ru.ilkhik.testtask.services.SignUpService;

@Controller
@RequestMapping("/signup")
public class SignUpController {
    @Autowired
    SignUpService signUpService;

    @PostMapping
    public String signUp(UserForm userForm, Model model) {
        try {
            signUpService.signUp(userForm);
        } catch (Throwable e) {
            model.addAttribute("error", true);
            return "sign_up";
        }
        return "redirect:/login";
    }

    @GetMapping
    public String signUpPage(Model model) {
        model.addAttribute("error", false);
        return "sign_up";
    }
}
