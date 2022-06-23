package com.devsuperior.movieflix.resources;

import com.devsuperior.movieflix.dto.MovieDto;
import com.devsuperior.movieflix.dto.NewMovieDto;
import com.devsuperior.movieflix.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/movies")
public class MovieResource {

    @Autowired
    private MovieService service;

    @GetMapping
    public ResponseEntity<Page<NewMovieDto>> findByGenre(
            @RequestParam(value = "genreId", defaultValue = "0") Long genreId,
            Pageable pageable) {

        Page<NewMovieDto> list = service.findByGenre(genreId, pageable);
        return ResponseEntity.ok(list);
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<MovieDto> findById(@PathVariable Long id) {
        MovieDto dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

}
