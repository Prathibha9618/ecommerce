package com.ecom.ecommerce.services.auth;

import com.ecom.ecommerce.dto.SignupRequest;
import com.ecom.ecommerce.dto.UserDto;

public interface AuthService {
	
	UserDto createUser(SignupRequest signupRequest);
	
	Boolean hasUserWithEmail(String email);

}
