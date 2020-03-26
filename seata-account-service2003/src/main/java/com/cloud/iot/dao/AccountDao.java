package com.cloud.iot.dao;

import com.cloud.iot.domain.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AccountDao {
    void decrease(@Param("userId") Long userId, @Param("money") Integer money);


    Account getAccountById(@Param("id") Long id);
}
