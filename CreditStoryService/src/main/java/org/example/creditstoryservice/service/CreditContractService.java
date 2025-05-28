package org.example.creditstoryservice.service;

import org.example.creditstoryservice.entity.CreditContract;


public interface CreditContractService {
    Iterable<CreditContract> findCreditContractByClientId(int clientId);

    Iterable<CreditContract> findAllCreditContractsByClientIdAsync(int clientId);
}
