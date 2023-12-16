package com.ecom.ecommerce.services.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecom.ecommerce.dto.SignupRequest;
import com.ecom.ecommerce.dto.UserDto;
import com.ecom.ecommerce.entity.User;
import com.ecom.ecommerce.enums.UserRole;
import com.ecom.ecommerce.repository.UserRepository;

import jakarta.annotation.PostConstruct;

@Service
public class AuthServiceImpl implements AuthService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public UserDto createUser(SignupRequest signupRequest) {
		User user = new User();
		user.setEmail(signupRequest.getEmail());
		user.setName(signupRequest.getName());
		user.setRole(UserRole.CUSTOMERS);
		user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
		System.out.print(signupRequest.getEmail());
		System.out.print(signupRequest.getName());
		System.out.print(signupRequest.getPassword());
		User createdUser = userRepository.save(user);
		
//		System.out.println(createdUser);
		UserDto userDto = new UserDto();
		userDto.setId(createdUser.getId());
		
		return userDto;
		
	}
	public Boolean hasUserWithEmail(String email) {
		return userRepository.findFirstByEmail(email).isPresent();
		
	}
	@PostConstruct
	public void createAdminAccount() {
		User adminAccount = userRepository.findByRole(UserRole.ADMIN);
		if(null == adminAccount) {
			User user = new User();
			user.setEmail("admin@test.com");
			user.setName("admin");
			user.setRole(UserRole.ADMIN);
			user.setPassword(new BCryptPasswordEncoder().encode("admin"));
			userRepository.save(user);
		}
	}
	
	
	

}
