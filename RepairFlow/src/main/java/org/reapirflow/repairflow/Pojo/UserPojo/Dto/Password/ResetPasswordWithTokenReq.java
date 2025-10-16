package org.reapirflow.repairflow.Pojo.UserPojo.Dto.Password;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public record ResetPasswordWithTokenReq(
        @NotBlank(message = "token can not be empty") String token,
        @NotBlank(message = "new password can not be empty") @Size(min = 8,max = 72)String newPassword,
        @NotBlank(message = "confirm new password can not be empty") @Size(min = 8,max = 72)String confirmPassword
) {
}
