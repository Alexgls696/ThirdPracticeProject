package org.alexgls.centerservice.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class Payment {

    private Long id;
    private Long contractId;

    private Long scheduleId;

    private Date paymentDate;

    private double amount;

    private String paymentMethod;

    private String transactionReference;

    private Date createdAt;

}