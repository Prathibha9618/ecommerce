package com.ecommerce.controller;

import java.io.IOException;
import java.util.Optional;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.dto.AuthenticationRequest;
import com.ecommerce.dto.SignupRequest;
import com.ecommerce.dto.UserDto;
import com.ecommerce.entity.User;
import com.ecommerce.repository.UserRepository;
import com.ecommerce.services.auth.AuthService;
import com.ecommerce.utils.Jwtutil;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor

public class AuthController {

	private final AuthenticationManager authenticationManager;

	private final UserDetailsService userdetailsService;

	private final UserRepository userRepository;

	private final Jwtutil  jwtutil;

	private final AuthService authService;

	public static final String HEADER_STRING ="Authorization";

	public static final String Token_PREFIX ="Bearer";


	@PostMapping("/authenticate")
	public void createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest,
											HttpServletResponse httpServletResponse) throws IOException, JSONException {
		
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
					authenticationRequest.getPassword()));
		}
		catch (BadCredentialsException e) {
			throw new BadCredentialsException("incorrect");
		}
		final UserDetails userDetails = userdetailsService.loadUserByUsername(authenticationRequest.getUsername());
		Optional<User> optionalUser = userRepository.findFirstByEmail(userDetails.getUsername());
		final String jwt = jwtutil.generateToken(userDetails.getUsername());
		System.out.println("jwt"+ jwt);
		if(optionalUser.isPresent()) {
			httpServletResponse.getWriter().write(new JSONObject()
					.put("userId",optionalUser.get().getId())
					.put("role",optionalUser.get().getRole())
					.toString()
					);
			httpServletResponse.addHeader("Access-Control-Expose-Headers","Authorization");
			httpServletResponse.addHeader("Access-Control-Allow-Headers","Authorization,X-PINGOTHER,Origin,"+
			"X-Requested-With,Content-Type,Accept,X-Custom-header");
			httpServletResponse.addHeader(HEADER_STRING, Token_PREFIX + jwt);
		}
	}
	@PostMapping("/sign-up")
	public ResponseEntity<?> signupUser(@RequestBody SignupRequest signupRequest){
		 System.out.println("Received registration request: " + signupRequest);

		if(authService.hasUserWithEmail(signupRequest.getEmail())) {
			  System.out.println("User already exists");
			return new ResponseEntity<>("user alredy exists",HttpStatus.NOT_ACCEPTABLE);
		}
		UserDto userDto = authService.createUser(signupRequest);
		
		 System.out.println("User registered successfully");
		return new ResponseEntity<>(userDto,HttpStatus.OK);
	}

	}


