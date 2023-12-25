package com.ecommerce.services.auth;

import com.ecommerce.dto.SignupRequest;
import com.ecommerce.dto.UserDto;

public interface AuthService {

	UserDto createUser(SignupRequest signupRequest);

	Boolean hasUserWithEmail(String email);

}
