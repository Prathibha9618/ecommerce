package com.ecommerce.services.auth;


import com.ecommerce.admin.entity.Order;
import com.ecommerce.admin.enums.Orderstatus;
import com.ecommerce.admin.repository.OrderRepo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ecommerce.dto.SignupRequest;
import com.ecommerce.dto.UserDto;
import com.ecommerce.entity.User;
import com.ecommerce.enums.UserRole;
import com.ecommerce.repository.UserRepository;

import jakarta.annotation.PostConstruct;

@Service
public class AuthServiceImpl implements AuthService{

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	@Autowired
    private OrderRepo orderRepo;



	@Override
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
		
		Order order= new Order();
		order.setAmount(0L);
		order.setTotalAmount(0L);
		order.setDiscount(0L);
		order.setUser(createdUser);
		order.setOrderStatus(Orderstatus.pending);
		orderRepo.save(order);

		System.out.println(createdUser);
		UserDto userDto = new UserDto();
		userDto.setId(createdUser.getId());

		return userDto;

	}
	@Override
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
			System.out.println("enter");
		}
	}
	




}
