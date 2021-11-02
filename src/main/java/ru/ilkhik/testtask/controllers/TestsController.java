package ru.ilkhik.testtask.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.ilkhik.testtask.config.details.UserDetailsImpl;
import ru.ilkhik.testtask.models.TakedTest;
import ru.ilkhik.testtask.models.Test;
import ru.ilkhik.testtask.models.User;
import ru.ilkhik.testtask.services.TestService;
import ru.ilkhik.testtask.transfer.TestDto;
import ru.ilkhik.testtask.forms.TestTakeForm;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/tests")
public class TestsController {
    @Autowired
    private TestService testService;

    @GetMapping("")
    public String findAll(Model model, Authentication authentication) {
        List<TestDto> tests = testService.getAllTests();
        model.addAttribute("tests", tests);
        if (authentication != null && authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
            model.addAttribute("admin", true);
        } else {
            model.addAttribute("admin", false);
        }

        return "tests";
    }

    @GetMapping("/{id}")
    public String getById(Model model, @PathVariable int id, Authentication authentication) {
        User user = ((UserDetailsImpl) authentication.getPrincipal()).getUser();
        Optional<TakedTest> takedTest = testService.isTestTaked(user, id);
        if (takedTest.isPresent()) {
            model.addAttribute("takedTest", takedTest.get());
            return "taked_test";
        }
        model.addAttribute("test", testService.findById(id));
        return "test";
    }

    @PostMapping("/{id}")
    @ResponseBody
    public void takeTest(@PathVariable int id, @RequestBody TestTakeForm testTakeForm, Authentication authentication) {
        User user = ((UserDetailsImpl) authentication.getPrincipal()).getUser();
        testService.takeTest(id, user, testTakeForm);
    }

    @GetMapping("/create")
    public String getCreateTestPage() {
        return "create_test";
    }

    @PostMapping("/create")
    @ResponseBody
    public void createNewTest(@RequestBody Test test) {
        testService.createNewTest(test);
    }
}
