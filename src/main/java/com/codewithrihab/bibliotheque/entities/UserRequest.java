package com.codewithrihab.bibliotheque.entities;

import lombok.Data;

@Data
public class UserRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String telephone;
    private String address;
    private Role role;
}
