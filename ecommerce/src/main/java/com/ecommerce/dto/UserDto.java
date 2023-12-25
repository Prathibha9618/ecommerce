package com.ecommerce.dto;


import com.ecommerce.enums.UserRole;

import lombok.Data;

@Data
public class UserDto {

    private long id;

	private String email;

	private String password;

	private String name;

	private UserRole userrole;

}
