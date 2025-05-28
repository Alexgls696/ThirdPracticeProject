package org.example.creditstoryservice.service;

import lombok.RequiredArgsConstructor;
import org.example.creditstoryservice.entity.Payment;
import org.example.creditstoryservice.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Override
    public Payment findById(long id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Payment with id %d not found".formatted(id)));
    }

    @Override
    public Iterable<Payment> findAllByContractId(long contractId) {
        return paymentRepository.findAllByContractId(contractId);
    }

    @Override
    public Iterable<Payment> findAllByScheduleId(long scheduleId) {
        return paymentRepository.findAllByScheduleId(scheduleId);
    }
}
