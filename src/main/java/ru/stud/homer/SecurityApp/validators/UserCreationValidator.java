package ru.stud.homer.SecurityApp.validators;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.stud.homer.SecurityApp.models.User;
import ru.stud.homer.SecurityApp.services.UserService;

import java.util.Objects;


@Component
@AllArgsConstructor
@NoArgsConstructor
public class UserCreationValidator implements Validator {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        User fundedUser = null;
        fundedUser = userService.findUserByEmail(user.getEmail());
        if (fundedUser != null) {
            errors.rejectValue("email", "", "Email is already use");
        }
    }
}
