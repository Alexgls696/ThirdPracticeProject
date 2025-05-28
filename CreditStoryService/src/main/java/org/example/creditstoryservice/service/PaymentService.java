package org.example.creditstoryservice.service;

import org.example.creditstoryservice.entity.Payment;

public interface PaymentService {
    Payment findById(long id);
    Iterable<Payment> findAllByContractId(long contractId);
    Iterable<Payment>findAllByScheduleId(long scheduleId);
}
