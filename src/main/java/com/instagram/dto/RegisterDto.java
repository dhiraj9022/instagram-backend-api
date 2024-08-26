package com.instagram.dto;

import com.instagram.Enum.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDto {

    @NotNull
    @NotBlank
    private String username;

    @NotNull
    @NotBlank
    private String email;

    @NotBlank
    @NotNull
    private String password;

    @NotNull
    @NotBlank
    private String fullName;

    private String bio;

    private Status status;
}