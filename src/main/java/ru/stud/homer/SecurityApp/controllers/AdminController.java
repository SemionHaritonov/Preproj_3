package ru.stud.homer.SecurityApp.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.stud.homer.SecurityApp.models.Role;
import ru.stud.homer.SecurityApp.models.User;
import ru.stud.homer.SecurityApp.services.RoleService;
import ru.stud.homer.SecurityApp.services.UserService;
import ru.stud.homer.SecurityApp.validators.UserCreationValidator;
import ru.stud.homer.SecurityApp.validators.UserUpdatingValidator;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;
    private final UserCreationValidator userCreationValidator;
    private final UserUpdatingValidator userUpdatingValidator;

    @GetMapping("/users")
    public String index(Model model) {
        model.addAttribute("size", roleService.findAll().size());
        model.addAttribute("users", userService.findAll());
        return "admin/users/index";
    }

    @PostMapping("/users")
    public String create(@ModelAttribute("user") @Valid User user,
                         BindingResult bindingResult) {
        userCreationValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "admin/users/new";
        }
        userService.add(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/users/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        List<Role> roles = new ArrayList<>(roleService.findAll());
        model.addAttribute("allRoles", roles);
        return "admin/users/new";
    }

    @GetMapping("/users/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.findUserById(id));

        return "admin/users/show";
    }

    @PatchMapping("/users/{id}")
    public String update(@ModelAttribute("user") @Valid User user,
                         BindingResult bindingResult,
                         @PathVariable("id") Long id,
                         @ModelAttribute("allRoles") ArrayList<Role> roleList) {
        userUpdatingValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "admin/users/edit";
        }

        userService.update(id, user);
        return "redirect:/admin/users";
    }

    @GetMapping("/users/{id}/edit")
    public String edit(@PathVariable("id") Long id, Model model) {
        User user = userService.findUserById(id);
        System.out.println(user.getRoles());
        user.setPassword("write_new_password");
        model.addAttribute("user", userService.findUserById(id));

        List<Role> roles = new ArrayList<>(roleService.findAll());
        model.addAttribute("allRoles", roles);

        return "admin/users/edit";
    }

    @DeleteMapping("/users/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/admin/users";
    }




}
