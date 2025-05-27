package org.example.creditstoryservice.repository;

import org.example.creditstoryservice.entity.Bank;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends CrudRepository<Bank, Integer> {

}
