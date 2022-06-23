package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.MovieDto;
import com.devsuperior.movieflix.dto.NewMovieDto;
import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.repositories.GenreRepository;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository repository;
    @Autowired
    private GenreRepository genreRepository;

    @Transactional(readOnly = true)
    public Page<NewMovieDto> findByGenre(Long genreId, Pageable pageable) {
        Genre genre = (genreId == 0) ? null : genreRepository.getOne(genreId);
        Page<Movie> page = repository.findByGenre(genre, pageable);
        repository.findMoviesAndGenres(page.getContent());
        return page.map(NewMovieDto::new);
    }

    @PreAuthorize("hasAnyRole('VISITOR', 'MEMBER')")
    @Transactional(readOnly = true)
    public MovieDto findById(Long id) {
        Optional<Movie> obj = repository.findById(id);
        Movie entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new MovieDto(entity);
    }

}
