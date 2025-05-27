package org.example.creditstoryservice.repository;

import org.example.creditstoryservice.entity.DelinquencyHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DelinquencyHistoryRepository extends CrudRepository<DelinquencyHistory, Long> {
}
