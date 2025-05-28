package org.example.creditstoryservice.service;

import lombok.RequiredArgsConstructor;
import org.example.creditstoryservice.entity.CreditContract;
import org.example.creditstoryservice.repository.*;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CreditContractServiceImpl implements CreditContractService {

    private final CreditContractRepository creditContractRepository;
    private final DelinquencyHistoryRepository delinquencyHistoryRepository;
    private final BankRepository bankRepository;
    private final PaymentRepository paymentRepository;
    private final PaymentScheduleRepository paymentScheduleRepository;

    @Override
    public Iterable<CreditContract> findCreditContractByClientId(int clientId) {
        return creditContractRepository.findCreditContractByClientId(clientId);
    }

    @Override
    public Iterable<CreditContract> findAllCreditContractsByClientIdAsync(int clientId) {
        var contracts = creditContractRepository.findCreditContractByClientId(clientId);
        contracts.forEach(contract -> {
            contract.setBank(bankRepository.findById(contract.getBankId())
                    .orElseThrow(() -> new NoSuchElementException("Bank with id %d not found".formatted(contract.getBankId()))));
            contract.setDelinquencies(delinquencyHistoryRepository.findByContractId(contract.getId()));
            contract.setPayments(paymentRepository.findAllByContractId(contract.getId()));
            contract.setPaymentSchedules(paymentScheduleRepository.findAllByContractId(contract.getId()));
        });
        return contracts;
    }
}
