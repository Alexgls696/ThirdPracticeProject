package org.example.creditstoryservice.service;

import org.example.creditstoryservice.entity.DelinquencyHistory;

public interface DelinquencyHistoryService {
    DelinquencyHistory findDelinquencyHistoryById(long id);
}
