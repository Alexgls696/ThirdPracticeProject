package org.example.userdetailsservice.service;

import jakarta.validation.Valid;
import org.example.userdetailsservice.controller.payload.FindByPassportDataPayload;
import org.example.userdetailsservice.controller.payload.FindUserByDataPayload;
import org.example.userdetailsservice.entity.User;

public interface UsersService {
    Iterable<User> findAll();

    User save(User user);

    User findUserByData(FindUserByDataPayload payload);

    User findById(int id);

    User findByEmail(String email);

    User findByPassport(String passport);

    void deleteById(int id);

    void deleteByPassport(String passport);

    User findByPassportData(FindByPassportDataPayload payload);
}
