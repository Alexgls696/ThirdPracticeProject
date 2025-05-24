package org.alexgls.centerservice.client;

import org.alexgls.centerservice.client.payload.FindUserByDataPayload;
import org.alexgls.centerservice.entity.User;

public interface UserDetailsRestClient {
    User findUserByEmail(String email);
    User findUserByPassport(String passport);
    User findUserByUserdata(FindUserByDataPayload payload);
}
