package org.example.userdetailsservice.repository;

import jakarta.annotation.Nullable;
import org.example.userdetailsservice.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends CrudRepository<User, Integer> {


    @Query(nativeQuery = true, value = """
    SELECT * FROM users 
    WHERE 
        name = :name 
        AND surname = :surname 
        AND (:patronymic IS NULL OR patronymic = :patronymic)
        AND passport = :passport 
        AND (:email IS NULL OR email = :email)
        AND inn = :inn
    """)
    Optional<User> findByUserData(
            @Param("name") String name,
            @Param("surname") String surname,
            @Param("patronymic") @Nullable String patronymic,
            @Param("passport") String passport,
            @Param("email") @Nullable String email,
            @Param("inn") String inn
    );


    Optional<User> findByEmail(String email);

    Optional<User> findByPassport(String passport);

    void deleteByPassport(String passport);
}
