package com.lab2.auth.models;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class Login {
    @NotNull
    private String username;
    @NotNull
    @Min(6)
    private String password;
}
