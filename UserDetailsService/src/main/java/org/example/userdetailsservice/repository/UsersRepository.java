package org.example.userdetailsservice.repository;

import org.example.userdetailsservice.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends CrudRepository<User, Integer> {
    User findByEmail(String email);
    User findByPassport(String passport);
}
