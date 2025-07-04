package org.alexgls.centerservice.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@NoArgsConstructor
@Getter
@Setter
public class CreditContract {
    private Long id;

    private int clientId;

    private Integer bankId;

    private String contractNumber;

    private Date startDate;
    private Date endDate;
    private double originalAmount;

    private String currency;

    private double interestRate;

    private String contractStatus;

    private Date createdAt;
    private Date updatedAt;

    private Bank bank;

    private Iterable<DelinquencyHistory> delinquencies;

    private Iterable<Payment> payments;

    private Iterable<PaymentSchedule> paymentSchedules;

}
