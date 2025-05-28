package org.example.creditstoryservice.repository;

import org.example.creditstoryservice.entity.PaymentSchedule;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentScheduleRepository extends CrudRepository<PaymentSchedule, Long> {
    Iterable<PaymentSchedule> findAllByContractId(Long contractId);
}
