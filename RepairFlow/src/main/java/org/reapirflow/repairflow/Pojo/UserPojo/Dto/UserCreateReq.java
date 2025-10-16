
// dto/user/UserCreateReq.java
package org.reapirflow.repairflow.Pojo.UserPojo.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserCreateReq(
        @NotBlank(message = "first name can not be empty") @Size(max = 60) String firstName,
        @NotBlank(message = "last name can not be empty") @Size(max = 60) String lastName,
        @NotBlank(message = "Email can not be empty") @Email @Size(max = 120) String email,
        @NotBlank(message = "password can not be empty") @Size(min = 8, max = 72) String password, // 原始密码，仅入参使用
        @NotBlank(message = "phone number can not be empty") @Size(max = 25) String phone
) {}