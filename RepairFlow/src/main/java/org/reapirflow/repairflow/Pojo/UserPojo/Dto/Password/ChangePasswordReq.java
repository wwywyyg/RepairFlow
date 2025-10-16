package org.reapirflow.repairflow.Pojo.UserPojo.Dto.Password;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public record ChangePasswordReq(
        @NotBlank(message = "old password can not be empty") String oldPassword,
        @NotBlank(message = "new password can not be empty") @Size(min=8,max=72) String newPassword,
        @NotBlank(message = "confirm new password can not be empty") @Size(min=8,max=72) String confirmPassword
) {
}
