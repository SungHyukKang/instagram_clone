package com.cos.photogramstart.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service // 1.IoC , 2.트랜잭션 관리
public class AuthService {

	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	@Transactional // Insert , Update ,Delete -> 무조건 트랜잭셔널 걸어주자
	public void 회원가입(User user) {
		String rawPw = user.getPassword();
		String encPw = bCryptPasswordEncoder.encode(rawPw);
		user.setPassword(encPw);
		user.setRole("ROLE_USER");
		userRepository.save(user);
	}

}
