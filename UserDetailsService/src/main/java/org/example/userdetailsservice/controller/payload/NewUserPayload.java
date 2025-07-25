package org.example.userdetailsservice.controller.payload;

import jakarta.validation.constraints.NotBlank;

public record NewUserPayload(
        @NotBlank(message = "{errors.validation.name_id_blank}")
        String name,

        @NotBlank(message = "{errors.validation.surname_id_blank}")
        String surname,

        String patronymic,

        String email,

        String gender,

        @NotBlank(message = "{errors.validation.passport_is_blank}")
        String passport,

        @NotBlank(message = "{errors.validation.inn_is_blank}")
        String inn,

        String driversLicense
) {
}
