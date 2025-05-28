package org.example.creditstoryservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.creditstoryservice.entity.PaymentSchedule;
import org.example.creditstoryservice.service.PaymentScheduleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/credit-story/payments-schedule")
@RequiredArgsConstructor
public class PaymentsScheduleController {

    private final PaymentScheduleService paymentScheduleService;

    @GetMapping("/{id}")
    public PaymentSchedule findById(@PathVariable int id) {
        return paymentScheduleService.findById(id);
    }
}
