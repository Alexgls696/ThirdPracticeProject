package org.example.userdetailsservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.userdetailsservice.controller.payload.FindUserByDataPayload;
import org.example.userdetailsservice.controller.payload.NewUserPayload;
import org.example.userdetailsservice.entity.User;
import org.example.userdetailsservice.service.UsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

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

    @PostMapping("find-by-email")
    public User findUserByUsername(@RequestBody String email) {
        return usersService.findByEmail(email);
    }

    @PostMapping("find-by-passport")
    public User findUserByPassport(@RequestBody String passport) {
        return usersService.findByPassport(passport);
    }

    @PostMapping("find-by-userdata")
    public User findUserByUserdata(@Valid @RequestBody FindUserByDataPayload payload, BindingResult bindingResult) throws BindException {
        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException exception) {
                throw exception;
            } else {
                throw new BindException(bindingResult);
            }
        }
        return usersService.findUserByData(payload);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody NewUserPayload payload, BindingResult bindingResult, UriComponentsBuilder builder) throws BindException {
        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException exception) {
                throw exception;
            } else {
                throw new BindException(bindingResult);
            }
        }
        var added = usersService.save(new User(payload));
        return ResponseEntity
                .created(builder.replacePath("api/users/{id}")
                        .build(Map.of("id", added.getId())))
                .body(added);

    }

    @DeleteMapping("delete-by-passport")
    public ResponseEntity<Void> deleteUserByPassport(@RequestBody String passport) {
        usersService.deleteByPassport(passport);
        return ResponseEntity
                .noContent()
                .build();
    }


}
