package org.example.creditstoryservice.repository;

import org.example.creditstoryservice.entity.Payment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends CrudRepository<Payment, Long> {
    Iterable<Payment> findAllByContractId(long contractId);
    Iterable<Payment>findAllByScheduleId(long scheduleId);
}
