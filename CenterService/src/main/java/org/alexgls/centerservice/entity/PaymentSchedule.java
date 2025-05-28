package org.alexgls.centerservice.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
public class PaymentSchedule {
    private Long id;

    private Long contractId;

    private Date paymentDate;

    private double paymentAmount;

    private double principalAmount;

    private double interestAmount;

    private String status;

    private Date createdAt;
}
