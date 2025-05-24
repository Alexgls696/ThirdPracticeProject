package org.example.userdetailsservice.service;

import org.example.userdetailsservice.entity.User;

public interface UsersService {
    Iterable<User> findAll();

    User save(User user);

    User findById(int id);

    User findByEmail(String email);

    User findByPassport(String passport);

    void deleteById(int id);
}
