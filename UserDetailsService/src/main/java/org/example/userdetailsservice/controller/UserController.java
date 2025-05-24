package org.example.userdetailsservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.userdetailsservice.entity.User;
import org.example.userdetailsservice.service.UsersService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users/{id:\\d+}")
@RequiredArgsConstructor
public class UserController {
    private final UsersService usersService;

    @GetMapping
    public User getUserById(@PathVariable int id) {
        return usersService.findById(id);
    }

    @DeleteMapping
    public void deleteUserById(@PathVariable int id) {
        usersService.deleteById(id);
    }
}
