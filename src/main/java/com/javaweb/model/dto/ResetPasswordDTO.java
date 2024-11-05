package com.javaweb.model.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResetPasswordDTO {
    private String secretKey;
    private String password;
    private String confirmPassword;
}
