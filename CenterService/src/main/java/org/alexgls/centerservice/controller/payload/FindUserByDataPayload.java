package org.alexgls.centerservice.controller.payload;


public record FindUserByDataPayload(
        String name,

        String surname,

        String patronymic,

        String passport,

        String email,

        String inn
) {
}
