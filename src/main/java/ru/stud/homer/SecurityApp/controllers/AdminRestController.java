package ru.stud.homer.SecurityApp.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.stud.homer.SecurityApp.dto.UserDTO;
import ru.stud.homer.SecurityApp.models.User;
import ru.stud.homer.SecurityApp.services.RoleService;
import ru.stud.homer.SecurityApp.services.UserService;
import ru.stud.homer.SecurityApp.validators.UserCreationValidator;
import ru.stud.homer.SecurityApp.validators.UserUpdatingValidator;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminRestController {
    private final UserService userService;
    private final RoleService roleService;
    private final UserCreationValidator userCreationValidator;
    private final UserUpdatingValidator userUpdatingValidator;

    @GetMapping("/users")
    public List<UserDTO> index() {
        List<UserDTO> userDTO = new ArrayList<>();
        for (User user : userService.findAll()) {
            userDTO.add(userService.userToUserDTO(user));
        }
        return userDTO;
    }

    @GetMapping("/users/{id}")
    public UserDTO show(@PathVariable("id") Long id) {
        return userService.userToUserDTO(userService.findUserById(id));
    }

    @PostMapping("/users")
    public void create(@RequestBody UserDTO userDTO) {
        System.out.println(userDTO);
        userService.add(userService.userDTOToUser(userDTO));
    }

    @PatchMapping("/users/{id}")
    public void update(@PathVariable("id") Long id, @RequestBody UserDTO userDTO) {
        System.out.println(userDTO);
        userService.update(id, userService.userDTOToUser(userDTO));
    }

    @DeleteMapping("/users/{id}")
    public void delete(@PathVariable("id") Long id) {
        userService.delete(id);
    }
}
