package ru.ilkhik.testtask.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.ilkhik.testtask.config.details.UserDetailsImpl;
import ru.ilkhik.testtask.models.User;
import ru.ilkhik.testtask.services.UserService;
import ru.ilkhik.testtask.services.exceptions.PasswordIsWrongException;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/settings")
public class SettingsController {
    @Autowired
    UserService userService;

    @GetMapping("")
    public String getSettingsPage(Model model, HttpServletRequest request) {
        if (request.getParameterMap().keySet().contains("passwordIsWrong")) {
            model.addAttribute("passwordIsWrong", true);
        } else if (request.getParameterMap().keySet().contains("passwordChanged")) {
            model.addAttribute("passwordChanged", true);
        }
        return "settings";
    }

    @PostMapping("/password")
    public String changePassword(Authentication authentication, @RequestParam String oldPassword,
                                 @RequestParam String newPassword) {
        User user = ((UserDetailsImpl) authentication.getPrincipal()).getUser();
        try {
            userService.changePassword(user, oldPassword, newPassword);
        } catch (PasswordIsWrongException e) {
            return "redirect:/settings?passwordIsWrong";
        }
        return "redirect:/settings?passwordChanged";
    }
}
