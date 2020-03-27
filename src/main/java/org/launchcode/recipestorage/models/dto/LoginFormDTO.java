package org.launchcode.recipestorage.models.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LoginFormDTO {
    @NotNull
    @NotBlank
    @Size(min = 6, max = 30, message = "Username must between 6 and 30 characters.")
    private String username;

    @NotNull
    @NotBlank
    @Size(min = 10, max = 32, message = "Password must be between 10 and 32 characters.")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword () {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
