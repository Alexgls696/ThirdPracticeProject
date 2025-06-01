package org.example.userdetailsservice.controller.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record FindByPassportDataPayload(
        @NotBlank(message = "{errors.validation.name_is_blank}")
        @Size(min = 2,max = 50, message = "{errors.validation.name_length_is_invalid}")
        String name,
        String surname,
        String patronymic,
        String passport) {
}
