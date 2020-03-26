package com.cloud.iot.service.impl;

import com.cloud.iot.dao.AccountDao;
import com.cloud.iot.domain.Account;
import com.cloud.iot.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {
    @Resource
    private AccountDao accountDao;

    @Override
    public void decrease(Long userId, Integer money) {
        log.info("--------->account-service中扣减账户余额开始");
        try{
            //模拟超时异常
            TimeUnit.SECONDS.sleep(20);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        accountDao.decrease(userId, money);
        log.info("--------->account-service中扣减账户余额结束");
    }

    @Override
    public Account getAccountById(Long id) {
        return accountDao.getAccountById(id);
    }
}
