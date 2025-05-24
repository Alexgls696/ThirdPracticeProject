package org.alexgls.centerservice.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
public class User {
    private Integer id;
    private String name;
    private String surname;
    private String patronymic;
    private String email;
    private String gender;
    private String passport;
    private String INN;
    private String driversLicense;
}
