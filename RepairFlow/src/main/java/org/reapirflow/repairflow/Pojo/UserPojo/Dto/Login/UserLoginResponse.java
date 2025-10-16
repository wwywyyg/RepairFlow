package org.reapirflow.repairflow.Pojo.UserPojo.Dto.Login;


import org.reapirflow.repairflow.Pojo.UserPojo.Dto.UserDto;

public class UserLoginResponse {
    private final String token;
    private final UserDto user;

    public UserLoginResponse(String token, UserDto user) {
        this.token = token;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public UserDto getUser() {
        return user;
    }
}
