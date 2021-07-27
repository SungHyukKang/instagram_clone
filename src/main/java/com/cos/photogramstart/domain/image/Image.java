package com.cos.photogramstart.domain.image;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import com.cos.photogramstart.domain.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
public class Image {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String caption;// ex)오늘 나 너무 피곤해

	private String postImageUrl;// 사진을 전송 받아서 그 사진을 서버의 특정 폴더에 저장-DB에 그 저장된 경로를 insert

	@JoinColumn(name = "userId")
	@ManyToOne
	private User user; // 1명의 유저는 여러개의 이미지를 올릴 수 있음 N:1
	// 하나의 이미지는 한명의 유저만 등록할 수 있음 -> OneToOne

	// 이미지 좋아요
	// 댓글

	private LocalDateTime createDate;

	@PrePersist // DB에 Insert 되기 직전에 실행
	public void createDate() {
		this.createDate = LocalDateTime.now();
	}

}
