package org.reapirflow.repairflow.Pojo.UserPojo.Dto;

import org.reapirflow.repairflow.Pojo.UserPojo.Role;
import jakarta.validation.constraints.NotNull;


public record AdminUserUpdateReq(
        @NotNull Boolean isActive,
        @NotNull Role role
) {
}
