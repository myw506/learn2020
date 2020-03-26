package com.cloud.iot.controller;

import com.cloud.iot.domain.Account;
import com.cloud.iot.domain.CommonResult;
import com.cloud.iot.service.AccountService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class AccountController {
    @Resource
    private AccountService accountService;

    @PostMapping(value = "/account/decrease")
    CommonResult decrease(@RequestParam("userId") Long productId, @RequestParam("money") Integer money){
        accountService.decrease(productId,money);
        return new CommonResult(200,"减钱成功");
    }

    @GetMapping("/storage/get/{id}")
    public CommonResult<Account> getAccountById(@PathVariable("id") Long id){
        Account account = accountService.getAccountById(id);
        return new CommonResult(200,"查询成功", account);
    }
}
