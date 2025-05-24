package org.alexgls.centerservice.client;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.alexgls.centerservice.client.exception.BindException;
import org.alexgls.centerservice.client.exception.NoSuchUserException;
import org.alexgls.centerservice.client.payload.FindUserByDataPayload;
import org.alexgls.centerservice.entity.User;
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
            return null;
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
            return null;
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
            String response = badRequestException.getResponseBodyAsString();
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                JsonNode jsonNode = objectMapper.readTree(response);
                JsonNode errors = jsonNode.get("errors");
                List<String> errorsList = new ArrayList<>();
                if (errors != null && errors.isArray()) {

                    for (JsonNode error : errors) {
                        errorsList.add(error.asText());
                    }
                }
                throw new BindException("Ошибка валидации данных", errorsList);
            } catch (IOException exception) {
                throw new BindException("Ошибка валидации данных", List.of());
            }
        } catch (HttpClientErrorException.NotFound exception) {
            throw new NoSuchUserException("Пользователь с такими данными не найден");
        }
    }
}
