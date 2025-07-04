package org.alexgls.centerservice.client;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.alexgls.centerservice.client.exception.BindException;
import org.alexgls.centerservice.client.exception.NoSuchUserException;
import org.alexgls.centerservice.controller.payload.FindByPassportDataPayload;
import org.alexgls.centerservice.controller.payload.FindUserByDataPayload;
import org.alexgls.centerservice.entity.User;
import org.springframework.http.ProblemDetail;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class UserDetailsRestClientImpl implements UserDetailsRestClient {

    private final RestClient restClient;

    @Override
    public User findUserByEmail(String email) {
        try {
            return restClient
                    .post()
                    .uri("api/users/find-by-email")
                    .body(Map.of("email", email))
                    .retrieve()
                    .body(User.class);
        } catch (HttpClientErrorException.NotFound exception) {
            throw new NoSuchUserException("User with email " + email + " not found");
        }
    }

    @Override
    public User findUserByPassport(String passport) {
        try {
            return restClient
                    .post()
                    .uri("api/users/find-by-passport")
                    .body(Map.of("passport", passport))
                    .retrieve()
                    .body(User.class);
        } catch (HttpClientErrorException.NotFound exception) {
            throw new NoSuchUserException("User with passport " + passport + " not found");
        }
    }

    @Override
    public User findUserByUserdata(FindUserByDataPayload payload) {
        try {
            return restClient
                    .post()
                    .uri("api/users/find-by-userdata")
                    .body(payload)
                    .retrieve()
                    .body(User.class);
        } catch (HttpClientErrorException.BadRequest badRequestException) {
            List<String> errorsList = (List<String>) badRequestException
                    .getResponseBodyAs(ProblemDetail.class)
                    .getProperties()
                    .get("errors");

            throw new BindException("Ошибка валидации данных", errorsList);
        } catch (HttpClientErrorException.NotFound exception) {
            throw new NoSuchUserException("Пользователь с такими данными не найден");
        }
    }

    @Override
    public User findUserById(int id) {
        try {
            return restClient
                    .get()
                    .uri("api/users/{id}", id)
                    .retrieve()
                    .body(User.class);
        } catch (HttpClientErrorException.NotFound exception) {
            throw new NoSuchUserException("User with id " + id + " not found");
        }
    }

    @Override
    public User findUserByPassportData(FindByPassportDataPayload payload) {
        try {
            return restClient
                    .post()
                    .uri("api/users/find-by-passport-data")
                    .body(payload)
                    .retrieve()
                    .body(User.class);
        } catch (HttpClientErrorException.BadRequest exception) {
            ProblemDetail detail = exception.getResponseBodyAs(ProblemDetail.class);
            var errors = (List<String>) detail
                    .getProperties()
                    .get("errors");
            throw new BindException("Ошибка валидации данных", errors);
        } catch (HttpClientErrorException.NotFound exception) {
            throw new NoSuchUserException("Пользователь с заданными данными не найден");
        }
    }
}
