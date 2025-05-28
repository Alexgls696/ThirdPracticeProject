package org.alexgls.centerservice.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class DelinquencyHistory {


    private Long id;

    private Long contractId;

    private Date startDate;

    private Date endDate;

    private Integer daysDelinquent;

    private Integer maxDelinquencyDays;

    private Date createdAt;

}