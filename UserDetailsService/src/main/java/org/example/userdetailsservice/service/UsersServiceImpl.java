package org.example.userdetailsservice.service;

import lombok.RequiredArgsConstructor;
import org.example.userdetailsservice.controller.payload.FindByPassportDataPayload;
import org.example.userdetailsservice.controller.payload.FindUserByDataPayload;
import org.example.userdetailsservice.controller.payload.NewUserPayload;
import org.example.userdetailsservice.entity.User;
import org.example.userdetailsservice.exceptions.NoSuchUserException;
import org.example.userdetailsservice.repository.UsersRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {
    private final UsersRepository usersRepository;

    private User createUserByUserDataPayload(NewUserPayload payload) {
        User user = new User();
        user.setName(payload.name());
        user.setSurname(payload.surname());
        user.setINN(payload.inn());
        user.setPassport(payload.passport());
        user.setPatronymic(payload.patronymic());
        user.setEmail(payload.email());
        user.setGender(payload.gender());
        user.setDriversLicense(payload.driversLicense());
        return user;
    }


    @Override
    public Iterable<User> findAll() {
        return usersRepository.findAll();
    }

    @Override
    public User save(NewUserPayload payload) {
        User creatingUser = createUserByUserDataPayload(payload);
        return usersRepository.save(creatingUser);
    }

    @Override
    public User findUserByData(FindUserByDataPayload payload) {
        return usersRepository.findByUserData(payload.name(), payload.surname(), payload.patronymic(),
                        payload.passport(), payload.email(), payload.inn())
                .orElseThrow(() -> new NoSuchUserException("User with current data not found"));
    }

    @Override
    public User findById(int id) {
        return usersRepository.findById(id)
                .orElseThrow(() -> new NoSuchUserException("User with id %d not found".formatted(id)));
    }

    @Override
    public User findByEmail(String email) {
        return usersRepository.findByEmail(email)
                .orElseThrow(() -> new NoSuchUserException("User with email %s not found".formatted(email)));
    }

    @Override
    public User findByPassport(String passport) {
        return usersRepository.findByPassport(passport)
                .orElseThrow(() -> new NoSuchUserException("User with passport %s not found".formatted(passport)));
    }

    @Override
    public void deleteById(int id) {
        if (usersRepository.existsById(id)) {
            usersRepository.deleteById(id);
        } else {
            throw new NoSuchUserException("User with id %d not found".formatted(id));
        }
    }

    @Override
    public void deleteByPassport(String passport) {
        if (usersRepository.findByPassport(passport).isPresent()) {
            usersRepository.deleteByPassport(passport);
        } else {
            throw new NoSuchUserException("User with passport %s not found".formatted(passport));
        }
    }

    @Override
    public User findByPassportData(FindByPassportDataPayload payload) {
        return usersRepository.findByPassportData(payload.name(), payload.surname(), payload.patronymic(), payload.passport())
                .orElseThrow(() -> new NoSuchUserException("User with current data not found"));
    }
}
