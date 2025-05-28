package org.example.creditstoryservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.Date;
import java.util.List;


@Entity
@Table(name = "credit_contracts")
@NoArgsConstructor
@Getter
@Setter
public class CreditContract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contract_id")
    private Long id;

    @Column(name = "client_id", nullable = false)
    private int clientId;

    @Column(name = "bank_id", nullable = false)
    private Integer bankId;

    @Column(name = "contract_number", nullable = false, unique = true)
    private String contractNumber;

    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @Column(name = "end_date", nullable = false)
    private Date endDate;

    @Column(name = "original_amount", nullable = false)
    private double originalAmount;

    @Column(name = "currency", nullable = false)
    private String currency;

    @Column(name = "interest_rate", nullable = false)
    private double interestRate;

    @Column(name = "contract_status", nullable = false)
    private String contractStatus;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Transient
    private Bank bank;

    @Transient
    private Iterable<DelinquencyHistory>delinquencies;

    @Transient
    private Iterable<Payment>payments;

    @Transient
    private Iterable<PaymentSchedule>paymentSchedules;

}
