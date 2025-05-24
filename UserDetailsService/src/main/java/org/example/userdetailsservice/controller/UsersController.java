package org.example.userdetailsservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.userdetailsservice.controller.payload.NewUserPayload;
import org.example.userdetailsservice.entity.User;
import org.example.userdetailsservice.service.UsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriBuilder;

import java.util.Map;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UsersController {
    private final UsersService usersService;

    @GetMapping
    public Iterable<User> getAllUsers() {
        return usersService.findAll();
    }

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody NewUserPayload payload, BindingResult bindingResult, UriBuilder builder) throws BindException {
        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException exception) {
                throw exception;
            } else {
                throw new BindException(bindingResult);
            }
        } else {
            var added = usersService.save(new User(payload));
            return ResponseEntity
                    .created(builder.replacePath("api/users/{id}")
                            .build(Map.of("id", added.getId())))
                    .body(added);
        }
    }
}
