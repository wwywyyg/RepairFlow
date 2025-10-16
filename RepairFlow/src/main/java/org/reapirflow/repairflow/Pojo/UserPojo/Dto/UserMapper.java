package org.reapirflow.repairflow.Pojo.UserPojo.Dto;

import org.reapirflow.repairflow.Pojo.UserPojo.User;


public final class UserMapper {
    private UserMapper() {}

    public static UserDto toUserDto(User user) {
        if (user == null) return null;
        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhone(),
                user.getRole(),
                user.isActive(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }
}
