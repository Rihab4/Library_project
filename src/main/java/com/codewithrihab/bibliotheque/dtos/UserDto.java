package com.codewithrihab.bibliotheque.dtos;

import com.codewithrihab.bibliotheque.entities.Role;
import lombok.Data;

@Data
public class UserDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String telephone;
    private String address;
    private Role role;
}
