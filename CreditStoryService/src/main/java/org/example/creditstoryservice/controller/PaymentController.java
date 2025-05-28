package org.example.creditstoryservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.creditstoryservice.entity.Payment;
import org.example.creditstoryservice.service.PaymentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/credit-story/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping("/{id}")
    public Payment findById(@PathVariable int id) {
        return paymentService.findById(id);
    }
}
