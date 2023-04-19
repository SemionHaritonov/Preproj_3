package ru.stud.homer.SecurityApp.services;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.stud.homer.SecurityApp.models.Role;
import ru.stud.homer.SecurityApp.repositories.RoleRepository;

import java.util.List;

@Service
@Getter
public class RoleService {
    private UserService userService;
    private RoleRepository roleRepository;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role findRoleByName(String name) {
        return roleRepository.findRoleByName(name);
    }

    public List<Role> findAll() {
        return roleRepository.findAll();
    }
}