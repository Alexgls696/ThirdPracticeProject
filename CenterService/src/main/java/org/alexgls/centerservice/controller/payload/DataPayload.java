package org.alexgls.centerservice.controller.payload;


import org.alexgls.centerservice.entity.CreditContract;
import org.alexgls.centerservice.entity.User;

public record DataPayload(User user, Iterable<CreditContract> contracts) { }
