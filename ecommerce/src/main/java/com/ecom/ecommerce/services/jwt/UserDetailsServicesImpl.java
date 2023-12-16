package com.ecom.ecommerce.services.jwt;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ecom.ecommerce.entity.User;
import com.ecom.ecommerce.repository.UserRepository;

@Service
public class UserDetailsServicesImpl implements UserDetailsService {

	@Autowired
	private UserRepository userrepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> optionalUser = userrepository.findFirstByEmail(username);
		if(optionalUser.isEmpty())throw new UsernameNotFoundException("user name not found",null);
		return new org.springframework.security.core.userdetails.User(optionalUser.get().getEmail(),optionalUser.get().getPassword(),new ArrayList<>());
		
		
		
	}

}
