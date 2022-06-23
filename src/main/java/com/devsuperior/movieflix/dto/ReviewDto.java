package com.devsuperior.movieflix.dto;

import com.devsuperior.movieflix.entities.Review;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class ReviewDto implements Serializable {

    private Long id;

    @NotBlank
    private String text;
    private Long movieId;
    private UserDto user;

    public ReviewDto() {
    }

    public ReviewDto(Review entity) {
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

}
