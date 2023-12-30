package com.ecommerce.services.auth;

import java.util.Optional;

import com.ecommerce.dto.SignupRequest;
import com.ecommerce.dto.UserDto;
import com.ecommerce.entity.User;

public interface AuthService {

	UserDto createUser(SignupRequest signupRequest);

	Boolean hasUserWithEmail(String email);

}
