package org.alexgls.centerservice.client;

import org.alexgls.centerservice.entity.CreditContract;

public interface CreditStoryRestClient {
    Iterable<CreditContract>findCreditContractsByUserId(int userId);
}
