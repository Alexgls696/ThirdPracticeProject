package org.alexgls.centerservice.client;

import org.alexgls.centerservice.controller.payload.FindByPassportDataPayload;
import org.alexgls.centerservice.controller.payload.FindUserByDataPayload;
import org.alexgls.centerservice.entity.User;

public interface UserDetailsRestClient {
    User findUserByEmail(String email);
    User findUserByPassport(String passport);
    User findUserByUserdata(FindUserByDataPayload payload);
    User findUserById(int id);
    User findUserByPassportData(FindByPassportDataPayload payload);
}
