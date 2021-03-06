package com.cos.photogramstart.domain.image;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PrePersist;
import javax.persistence.Transient;

import com.cos.photogramstart.domain.comment.Comment;
import com.cos.photogramstart.domain.likes.Likes;
import com.cos.photogramstart.domain.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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

	@JsonIgnoreProperties({ "images" })
	@JoinColumn(name = "userId")
	@ManyToOne(fetch = FetchType.EAGER)
	private User user; // 1명의 유저는 여러개의 이미지를 올릴 수 있음 N:1
	// 하나의 이미지는 한명의 유저만 등록할 수 있음 -> OneToOne

	// 이미지 좋아요
	// 댓글
	@JsonIgnoreProperties({ "image" })
	@OneToMany(mappedBy = "image")
	private List<Likes> likes;

	@OrderBy("id DESC")
	@JsonManagedReference
	@OneToMany(mappedBy = "image", fetch = FetchType.LAZY)
	private List<Comment> comments;

	@Transient // DB에 컬럼이 만들어지지 않는다.
	private boolean likeState;

	@Transient
	private int likeCount;

	private LocalDateTime createDate;

	@PrePersist // DB에 Insert 되기 직전에 실행
	public void createDate() {
		this.createDate = LocalDateTime.now();
	}

}
