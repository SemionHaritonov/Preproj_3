package ru.stud.homer.SecurityApp.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.stud.homer.SecurityApp.dto.UserDTO;
import ru.stud.homer.SecurityApp.services.UserService;

import java.security.Principal;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserRestController {
    private final UserService userService;

    @GetMapping("/info")
    public UserDTO testPaige(Principal principal) {
        return userService.userToUserDTO(userService.findUserByEmail(principal.getName()));
    }
}
