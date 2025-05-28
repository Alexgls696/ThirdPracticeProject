package org.example.creditstoryservice.service;

import lombok.RequiredArgsConstructor;
import org.example.creditstoryservice.entity.PaymentSchedule;
import org.example.creditstoryservice.repository.PaymentScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class PaymentScheduleServiceImpl implements PaymentScheduleService {

    private final PaymentScheduleRepository paymentScheduleRepository;

    @Override
    public PaymentSchedule findById(long id) {
        return paymentScheduleRepository.findById(id).orElseThrow(()->new NoSuchElementException("Payment Schedule with id %d not found".formatted(id)));
    }

    @Override
    public Iterable<PaymentSchedule> findAllByContractId(long contractId) {
        return paymentScheduleRepository.findAllByContractId(contractId);
    }
}
