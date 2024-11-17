package com.javaweb.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String userName;
    private String phoneNumber;
    private String email;
    private Integer status;
    private String wallet;
    private String role;
}
