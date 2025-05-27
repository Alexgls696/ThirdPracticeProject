package org.example.creditstoryservice.repository;

import org.example.creditstoryservice.entity.CreditContract;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditContractRepository extends CrudRepository<CreditContract, Long> {
    Iterable<CreditContract> findCreditContractByClientId(int clientId);
}
