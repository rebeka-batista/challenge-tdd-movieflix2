package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.ReviewDto;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository repository;

    @Autowired
    private AuthService authService;

    @Autowired
    private MovieRepository movieRepository;

    @Transactional(readOnly = true)
    public Page<ReviewDto> findAllPaged(Pageable pageable) {
        Page<Review> list = repository.findAll(pageable);
        return list.map(ReviewDto::new);
    }

    @Transactional(readOnly = true)
    public ReviewDto findById(Long id) {
        authService.validateSelfOrAdmin(id);
        Optional<Review> obj = repository.findById(id);
        Review entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new ReviewDto(entity);
    }

    @PreAuthorize("hasAnyRole('MEMBER')")
    @Transactional
    public ReviewDto insert(ReviewDto dto) {
        Review entity = new Review();
        entity.setText(dto.getText());
        entity.setMovie(movieRepository.getOne(dto.getMovieId()));
        entity.setUser(authService.authenticated());
        entity = repository.save(entity);
        return new ReviewDto(entity);
    }

    @Transactional
    public ReviewDto update(Long id, ReviewDto dto) {
        try {
            Review entity = repository.getOne(id);
            entity.setText(dto.getText());
            entity.setMovie(movieRepository.getOne(dto.getMovieId()));
            entity.setUser(authService.authenticated());
            entity = repository.save(entity);
            return new ReviewDto(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        }
    }

}
