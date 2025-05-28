package org.example.creditstoryservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.creditstoryservice.entity.DelinquencyHistory;
import org.example.creditstoryservice.service.DelinquencyHistoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/credit-story/delinquencies")
@RequiredArgsConstructor
public class DelinquencyController {
    private final DelinquencyHistoryService delinquencyHistoryService;

    @GetMapping("/{id}")
    public DelinquencyHistory findDelinquencyHistoryById(@PathVariable int id) {
        return delinquencyHistoryService.findDelinquencyHistoryById(id);
    }
}
