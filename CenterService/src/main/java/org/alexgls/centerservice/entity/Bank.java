package org.alexgls.centerservice.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Bank {
    private Integer id;
    private String name;
    private String licenseNumber;
    private String bic;
    private String inn;
}
