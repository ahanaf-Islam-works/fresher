package com.api_gateway.dto.rest;

import com.api_gateway.model.user.User;
import com.api_gateway.model.user.UserType;

public record SignInResponse(String token,
                             String userName,
                             UserType userType,
                             Integer rollNumber) { }
