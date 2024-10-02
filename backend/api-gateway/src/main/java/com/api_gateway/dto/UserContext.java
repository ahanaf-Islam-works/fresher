package com.api_gateway.dto;

import com.api_gateway.model.user.UserType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserContext {
    private String userName;
    private Integer rollNumber;
    private String email;
    private UserType userType;
    private Long expiresIn;
    private String authorities;
}