package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.GenreDto;
import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.repositories.GenreRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GenreService {

    @Autowired
    private GenreRepository repository;

    @PreAuthorize("hasAnyRole('VISITOR', 'MEMBER')")
    @Transactional(readOnly = true)
    public List<GenreDto> findAll() {
        List<Genre> list = repository.findAll();
        return list.stream().map(GenreDto::new).collect(Collectors.toList());

    }

    @PreAuthorize("hasAnyRole('VISITOR', 'MEMBER')")
    @Transactional(readOnly = true)
    public GenreDto findById(Long id) {
        Optional<Genre> obj = repository.findById(id);
        Genre entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new GenreDto(entity);
    }

}
