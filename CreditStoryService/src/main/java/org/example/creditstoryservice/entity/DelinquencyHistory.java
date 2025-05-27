package org.example.creditstoryservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "delinquency_history")
@NoArgsConstructor
@Getter
@Setter
public class DelinquencyHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delinquency_id")
    private Long id;

    @Column(name = "contract_id", nullable = false)
    private Long contractId;

    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "days_delinquent")
    private Integer daysDelinquent;

    @Column(name = "max_delinquency_days")
    private Integer maxDelinquencyDays;

    @Column(name = "created_at")
    private Date createdAt;

}