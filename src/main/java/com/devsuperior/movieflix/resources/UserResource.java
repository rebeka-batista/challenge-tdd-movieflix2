package com.devsuperior.movieflix.resources;

import com.devsuperior.movieflix.dto.UserDto;
import com.devsuperior.movieflix.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService service;

    @GetMapping(value = "/profile")
    public ResponseEntity<UserDto> getProfile() {
        UserDto dto = service.getProfile();
        return ResponseEntity.ok(dto);
    }

}
