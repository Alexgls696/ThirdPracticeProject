package org.example.userdetailsservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.userdetailsservice.controller.payload.NewUserPayload;

@Entity
@Table(name = "users")
@Setter
@Getter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String surname;
    private String patronymic;
    private String email;
    private String gender;
    private String passport;
    private String INN;

    @Column(name = "drivers_license")
    private String driversLicense;

}
