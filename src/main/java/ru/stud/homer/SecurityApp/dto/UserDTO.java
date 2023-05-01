package ru.stud.homer.SecurityApp.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {
    private String id;
    private String email;
    private String name;
    private String age;
    private String roles;
    private String password;
}
