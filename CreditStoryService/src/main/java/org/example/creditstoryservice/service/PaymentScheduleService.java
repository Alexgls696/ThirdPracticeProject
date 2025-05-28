package org.example.creditstoryservice.service;

import org.example.creditstoryservice.entity.PaymentSchedule;

public interface PaymentScheduleService {
    PaymentSchedule findById(long id);
    Iterable<PaymentSchedule> findAllByContractId(long contractId);
}
