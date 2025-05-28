package org.alexgls.centerservice.controller;

import lombok.RequiredArgsConstructor;
import org.alexgls.centerservice.client.CreditStoryRestClient;
import org.alexgls.centerservice.client.UserDetailsRestClient;
import org.alexgls.centerservice.controller.payload.FindUserByDataPayload;
import org.alexgls.centerservice.controller.payload.DataPayload;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/center")
@RequiredArgsConstructor
public class CenterServiceController {

    private final UserDetailsRestClient userDetailsRestClient;
    private final CreditStoryRestClient creditStoryRestClient;

    @PostMapping("find-by-userdata")
    public ResponseEntity<DataPayload> findUserByUserdata(@RequestBody FindUserByDataPayload payload) {
        var user = userDetailsRestClient.findUserByUserdata(payload);
        var creditStory = creditStoryRestClient.findCreditContractsByUserId(user.getId());

        return ResponseEntity
                .ok(new DataPayload(user, creditStory));
    }

    @GetMapping("find-by-email/{email}")
    public ResponseEntity<DataPayload> findUserByUserEmail(@PathVariable("email") String email) {
        var user = userDetailsRestClient.findUserByEmail(email);
        var creditStory = creditStoryRestClient.findCreditContractsByUserId(user.getId());
        return ResponseEntity
                .ok(new DataPayload(user, creditStory));
    }

    @GetMapping("find-by-user-id/{id}")
    public ResponseEntity<DataPayload> findUserByUserId(@PathVariable("id") int id) {
        var user = userDetailsRestClient.findUserById(id);
        var creditStory = creditStoryRestClient.findCreditContractsByUserId(user.getId());
        return ResponseEntity
                .ok(new DataPayload(user, creditStory));
    }

}
