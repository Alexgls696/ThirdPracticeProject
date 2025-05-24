package org.example.userdetailsservice.service;

import lombok.RequiredArgsConstructor;
import org.example.userdetailsservice.entity.User;
import org.example.userdetailsservice.exceptions.NoSuchUserException;
import org.example.userdetailsservice.repository.UsersRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {
    private final UsersRepository usersRepository;



    @Override
    public Iterable<User> findAll() {
        return usersRepository.findAll();
    }

    @Override
    public User save(User user) {
        return usersRepository.save(user);
    }

    @Override
    public User findById(int id) {
        return usersRepository.findById(id).orElseThrow(()->new NoSuchUserException("User with id %d not found".formatted(id)));
    }

    @Override
    public User findByEmail(String email) {
        return usersRepository.findByEmail(email);
    }

    @Override
    public User findByPassport(String passport) {
        return usersRepository.findByPassport(passport);
    }

    @Override
    public void deleteById(int id) {
        if(usersRepository.existsById(id)) {
            usersRepository.deleteById(id);
        }else{
            throw new NoSuchUserException("User with id %d not found".formatted(id));
        }
    }
}
