package org.example.userdetailsservice.controller.payload;

import jakarta.validation.constraints.NotBlank;

public record FindUserByDataPayload(
        @NotBlank(message = "{errors.validation.name_id_blank}")
        String name,

        @NotBlank(message = "{errors.validation.surname_id_blank}")
        String surname,

        String patronymic,

        @NotBlank(message = "{errors.validation.passport_is_blank}")
        String passport,

        String email,

        @NotBlank(message = "{errors.validation.inn_is_blank}")
        String inn
) {
}
