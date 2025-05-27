package org.example.creditstoryservice.service;

import lombok.RequiredArgsConstructor;
import org.example.creditstoryservice.entity.CreditContract;
import org.example.creditstoryservice.repository.CreditContractRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreditContractServiceImpl implements CreditContractService {

    private final CreditContractRepository creditContractRepository;

    @Override
    public Iterable<CreditContract> findCreditContractByClientId(int clientId) {
        return creditContractRepository.findCreditContractByClientId(clientId);
    }
}
