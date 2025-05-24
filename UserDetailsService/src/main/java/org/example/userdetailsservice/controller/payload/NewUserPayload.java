package org.example.userdetailsservice.controller.payload;

import jakarta.validation.constraints.NotBlank;

public record NewUserPayload(
        @NotBlank(message = "Имя пользователя должно быть указано")
        String name,
        @NotBlank(message = "Фамилия пользователя должна быть указано")
        String surname,
        String email,
        String gender,

        @NotBlank(message = "Данные паспорта пользователя должны быть указаны")
        String passport,
        @NotBlank(message = "Номер ИИН пользователя должен быть указан")
        String inn,
        String driversLicense
) {
}
