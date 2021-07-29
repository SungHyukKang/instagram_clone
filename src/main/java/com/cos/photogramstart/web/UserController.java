package com.cos.photogramstart.web;

//import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.service.UserService;
import com.cos.photogramstart.web.dto.user.UserProfileDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class UserController {

	private final UserService userService;

	@GetMapping("/user/{pageUserId}")
	public String profile(@PathVariable int pageUserId, Model model,
			@AuthenticationPrincipal PrincipalDetails principalDetails) {
		UserProfileDto userEntity = userService.회원프로필(pageUserId, principalDetails.getUser().getId());
		model.addAttribute("dto", userEntity);

		return "user/profile";
	}

	@GetMapping("/user/{id}/update")
	public String update(@PathVariable int id, @AuthenticationPrincipal PrincipalDetails principalDetails) {
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		PrincipalDetails principal = (PrincipalDetails) auth.getPrincipal();
		return "user/update";
	}

}
