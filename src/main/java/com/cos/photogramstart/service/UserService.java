package com.cos.photogramstart.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import com.cos.photogramstart.handler.ex.CustomException;
import com.cos.photogramstart.handler.ex.CustomValidationApiException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	@Transactional(readOnly = true)
	public User 회원프로필(int userId) {
		User userEntity = userRepository.findById(userId).orElseThrow(() -> new CustomException("해당 회원은 존재하지 않습니다."));

		return userEntity;
	}

	@Transactional
	public User 회원수정(int id, User user) {
		// 1.영속화
//		User userEntity = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
//			@Override
//			public IllegalArgumentException get() {
//				// TODO Auto-generated method stub
//				return new IllegalArgumentException("찾을 수 없는 ID입니다");
//			}
//		});// 1. 무조건 찾았다. 걱정마 get()
		User userEntity = userRepository.findById(id)
				.orElseThrow(() -> new CustomValidationApiException("찾을 수 없는 ID입니다."));
		// 2. 못 찾았어, exception 발동시킬게 orElseThrow()
		// 2.영속화된 오브젝트를 수정 - 더티체킹(업데이트 완료)
		String rawPassword = user.getPassword();
		String encPassword = bCryptPasswordEncoder.encode(rawPassword);
		userEntity.setName(user.getName());
		userEntity.setPassword(encPassword);
		userEntity.setBio(user.getBio());
		userEntity.setWebsite(user.getWebsite());
		userEntity.setPhone(user.getPhone());
		userEntity.setGender(user.getGender());

		return userEntity;
		// 더티체킹이 일어나서 업데이트가 완료됨.
	}
}
