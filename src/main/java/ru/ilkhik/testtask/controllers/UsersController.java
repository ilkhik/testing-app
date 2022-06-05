package ru.ilkhik.testtask.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.ilkhik.testtask.config.details.UserDetailsImpl;
import ru.ilkhik.testtask.models.User;
import ru.ilkhik.testtask.services.UserService;
import ru.ilkhik.testtask.services.exceptions.UserNotFoundException;

@Controller
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String getAllUsersPage(Model model, Authentication authentication) {
        User currentUser = ((UserDetailsImpl) authentication.getPrincipal()).getUser();
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("users", userService.findAll());
        return "users";
    }

    @PostMapping("/{id}/role")
    public String changeRole(@PathVariable int id,
                             @RequestParam(value = "admin", required = false, defaultValue = "false") boolean admin) {
        try {
            userService.changeRole(id, admin);
        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        
        return "redirect:/users";
    }
}
