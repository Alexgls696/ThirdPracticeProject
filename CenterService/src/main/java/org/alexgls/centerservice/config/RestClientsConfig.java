package org.alexgls.centerservice.config;

import org.alexgls.centerservice.client.UserDetailsRestClient;
import org.alexgls.centerservice.client.UserDetailsRestClientImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientsConfig {

    @Bean
    public UserDetailsRestClient userDetailsRestClient(@Value("${services.user-details-service-url}") String userDetailsServiceUrl) {
        return new UserDetailsRestClientImpl(RestClient
                .builder()
                .baseUrl(userDetailsServiceUrl)
                .build());
    }
}
