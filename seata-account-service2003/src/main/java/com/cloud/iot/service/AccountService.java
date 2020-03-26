package com.cloud.iot.service;

import com.cloud.iot.domain.Account;

public interface AccountService {
    void decrease(Long userId, Integer money);

    Account getAccountById(Long id);
}
