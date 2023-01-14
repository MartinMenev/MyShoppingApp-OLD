package com.example.myshoppingapp.models.users;

import com.example.myshoppingapp.models.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class UserOutputDTO implements Serializable {
    private String username;
    private String password;
    private String email;
    private UserRole userRole;
}
