package org.example.creditstoryservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.creditstoryservice.entity.CreditContract;
import org.example.creditstoryservice.service.CreditContractService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/credit-story/all-data")
@RequiredArgsConstructor
@Slf4j
public class AllCreditStoreDataController {
    private final CreditContractService creditContractService;

    @GetMapping("by-user-id/{id}")
    public Iterable<CreditContract> findCreditContractsWithAllDataByClientId(@PathVariable int id) {
        log.info("Find credit contract for user id {}", id);
        return creditContractService.findAllCreditContractsByClientIdAsync(id);
    }
}
