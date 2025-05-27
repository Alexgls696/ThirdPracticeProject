package org.example.creditstoryservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "payment_schedule")
@Setter
@Getter
@NoArgsConstructor
public class PaymentSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private Long id;

    @Column(name = "contract_id", nullable = false)
    private Long contractId;

    @Column(name = "payment_date", nullable = false)
    private Date paymentDate;

    @Column(name = "payment_amount", nullable = false)
    private double paymentAmount;

    @Column(name = "principal_amount", nullable = false)
    private double principalAmount;

    @Column(name = "interest_amount", nullable = false)
    private double interestAmount;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "created_at")
    private Date createdAt;
}
