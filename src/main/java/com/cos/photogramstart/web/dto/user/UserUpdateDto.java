package com.cos.photogramstart.web.dto.user;


import javax.validation.constraints.NotBlank;

import com.cos.photogramstart.domain.user.User;

import lombok.Data;

@Data
public class UserUpdateDto {
	
	@NotBlank
	private String name;
	@NotBlank
	private String password;
	private String website;
	private String bio;
	private String phone;
	private String gender;
	
	
	//Validation 체크 필수적.
	public User toEntity() {
		return User.builder()
				.name(name)
				.password(password) // 사용자가 패스워드를 기재하지 않았으면 db에 공백이 들어감 이것이 문제!!! Validation 체크 필수적
				.website(website)
				.bio(bio)
				.phone(phone)
				.gender(gender)
				.build();
	}
	
}
