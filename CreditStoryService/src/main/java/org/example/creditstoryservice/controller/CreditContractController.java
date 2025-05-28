package org.example.creditstoryservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.creditstoryservice.entity.CreditContract;
import org.example.creditstoryservice.service.CreditContractService;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("api/credit-story/credit-contract")
@RequiredArgsConstructor
public class CreditContractController {

    private final CreditContractService creditContractService;

    @GetMapping("by-user-id/{id}")
    public Iterable<CreditContract> getCreditContractsByUserId(@PathVariable("id") int id) {
        return creditContractService.findCreditContractByClientId(id);
    }

}
