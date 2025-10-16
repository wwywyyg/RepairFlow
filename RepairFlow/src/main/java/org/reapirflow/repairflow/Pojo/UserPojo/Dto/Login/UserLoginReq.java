package org.reapirflow.repairflow.Pojo.UserPojo.Dto.Login;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public class UserLoginReq {
    @NotNull(message = "Email can not be empty")
    @Email(message = "invalid Email format")
    private String email;

    @NotNull(message = "Email can not be empty")
    private String password;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
