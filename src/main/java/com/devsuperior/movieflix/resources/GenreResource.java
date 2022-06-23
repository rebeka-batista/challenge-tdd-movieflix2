package com.devsuperior.movieflix.resources;

import com.devsuperior.movieflix.dto.GenreDto;
import com.devsuperior.movieflix.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/genres")
public class GenreResource {

    @Autowired
    private GenreService service;

    @GetMapping
    public ResponseEntity<List<GenreDto>> findAll() {
        List<GenreDto> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<GenreDto> findById(@PathVariable Long id) {
        GenreDto dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

}
