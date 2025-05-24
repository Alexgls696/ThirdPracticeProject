package org.alexgls.centerservice.controller;

import lombok.RequiredArgsConstructor;
import org.alexgls.centerservice.client.UserDetailsRestClient;
import org.alexgls.centerservice.client.payload.FindUserByDataPayload;
import org.alexgls.centerservice.entity.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/center")
@RequiredArgsConstructor
public class CenterServiceController {

    private final UserDetailsRestClient userDetailsRestClient;

    @PostMapping("find-by-userdata")
    public User findUserByUserdata(@RequestBody FindUserByDataPayload payload) {
        return userDetailsRestClient.findUserByUserdata(payload);
    }

}
