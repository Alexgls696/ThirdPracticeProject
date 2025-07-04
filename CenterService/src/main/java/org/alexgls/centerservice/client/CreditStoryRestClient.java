package org.alexgls.centerservice.client;

import org.alexgls.centerservice.entity.CreditContract;

import java.util.List;

public interface CreditStoryRestClient {
    List<CreditContract> findCreditContractsByUserId(int userId);
}
