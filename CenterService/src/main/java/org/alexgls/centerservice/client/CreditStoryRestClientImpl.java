package org.alexgls.centerservice.client;

import lombok.RequiredArgsConstructor;
import org.alexgls.centerservice.entity.CreditContract;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

import java.util.List;

@RequiredArgsConstructor
public class CreditStoryRestClientImpl implements CreditStoryRestClient {

    private final ParameterizedTypeReference<List<CreditContract>> PARAMETERIZED_TYPE_REFERENCE = new ParameterizedTypeReference<>() {
    };


    private final RestClient restClient;

    @Override
    public List<CreditContract> findCreditContractsByUserId(int userId) {
        return restClient
                .get()
                .uri("/api/credit-story/all-data/by-user-id/{id}", userId)
                .retrieve()
                .body(PARAMETERIZED_TYPE_REFERENCE);
    }
}
