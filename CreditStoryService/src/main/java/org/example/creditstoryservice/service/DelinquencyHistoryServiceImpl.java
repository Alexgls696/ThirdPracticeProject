package org.example.creditstoryservice.service;

import lombok.RequiredArgsConstructor;
import org.example.creditstoryservice.entity.DelinquencyHistory;
import org.example.creditstoryservice.repository.DelinquencyHistoryRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class DelinquencyHistoryServiceImpl implements DelinquencyHistoryService {

    private final DelinquencyHistoryRepository delinquencyHistoryRepository;

    @Override
    public DelinquencyHistory findDelinquencyHistoryById(long id) {
        return delinquencyHistoryRepository.findById(id).orElseThrow(() -> new NoSuchElementException("DelinquencyHistory with id %d not found".formatted(id)));
    }

    @Override
    public Iterable<DelinquencyHistory> findAllByContractId(long contractId) {
        return delinquencyHistoryRepository.findByContractId(contractId);
    }
}
