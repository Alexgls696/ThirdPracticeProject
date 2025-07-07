package org.alexgls.centerservice.controller.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


public record FindByPassportDataPayload(
        @NotBlank(message = "{errors.validation.name_is_blank}")
        @Size(min = 2,max = 100, message = "{errors.validation.name_length_is_invalid}")
        String name,

        @Size(min = 2,max = 100, message = "{errors.validation.name_length_is_invalid}")
        @NotBlank(message = "{errors.validation.surname_is_blank}")
        String surname,

        String patronymic,

        @Pattern(regexp = "^\\d{4}\\s\\d{6}$", message = "{errors.validation.passport_is_invalid}")
        @NotBlank(message = "{errors.validation.passport_is_blank}")
        String passport)
{
}
