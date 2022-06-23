package com.devsuperior.movieflix.resources;

import com.devsuperior.movieflix.dto.ReviewDto;
import com.devsuperior.movieflix.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/reviews")
public class ReviewResource {

    @Autowired
    private ReviewService service;

    @GetMapping
    public ResponseEntity<Page<ReviewDto>> findAll(Pageable pageable) {
        Page<ReviewDto> list = service.findAllPaged(pageable);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ReviewDto> findById(@PathVariable Long id) {
        ReviewDto dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<ReviewDto> insert(@Valid @RequestBody ReviewDto dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ReviewDto> update(@PathVariable Long id,
                                            @RequestBody ReviewDto dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }

}
