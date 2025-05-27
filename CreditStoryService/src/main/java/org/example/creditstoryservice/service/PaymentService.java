package org.example.creditstoryservice.service;

import org.example.creditstoryservice.entity.Payment;

public interface PaymentService {
    Payment findById(long id);
}
