package com.devsuperior.movieflix.dto;

import com.devsuperior.movieflix.entities.Review;

import java.io.Serializable;

public class NewMovieReviewDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String text;
	private Long movieId;
	private UserDto user;
	
	public NewMovieReviewDTO() {
	}
	
	public NewMovieReviewDTO(Review entity) {
		id = entity.getId();
		text = entity.getText();
		movieId = entity.getMovie().getId();
		user = new UserDto(entity.getUser());
		 
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Long getMovieId() {
		return movieId;
	}

	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	} 
}