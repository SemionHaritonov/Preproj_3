package ru.stud.homer.SecurityApp.controllers;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.stud.homer.SecurityApp.models.User;
import ru.stud.homer.SecurityApp.services.UserService;
import ru.stud.homer.SecurityApp.validators.UserCreationValidator;
import ru.stud.homer.SecurityApp.validators.UserUpdatingValidator;


import javax.validation.Valid;
import java.security.Principal;


@Controller
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping()
    public String index(Principal principal, Model model) {
        User user = userService.findUserByEmail(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("size", user.getRoles().size());
        return "user/show";
    }
}
