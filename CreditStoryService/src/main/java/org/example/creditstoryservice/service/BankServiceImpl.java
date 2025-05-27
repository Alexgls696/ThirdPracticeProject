package org.example.creditstoryservice.service;

import lombok.RequiredArgsConstructor;
import org.example.creditstoryservice.entity.Bank;
import org.example.creditstoryservice.repository.BankRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BankServiceImpl implements BankService {

    private final BankRepository bankRepository;

    @Override
    public Bank findById(int id) {
        return bankRepository.findById(id).get();
    }
}
