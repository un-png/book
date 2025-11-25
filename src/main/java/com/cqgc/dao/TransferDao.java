package com.cqgc.dao;

import com.cqgc.domain.Account;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface TransferDao {
    /**
     * 获取账户信息
     * @param id
     * @return
     */
    @Select("select id, account_name AS username, amount from account where id=#{id}")
    Account getAccount(@Param("id") Long id);
    /**
     * 转账
     * @param fromId
     * @param amount
     */
    @Update("update account set amount=amount-#{amount} where id=#{fromId}")
    void transfer(@Param("fromId") Long fromId, @Param("amount") Double amount);
    /**
     * 接收
     * @param toId
     * @param amount
     *  */
    @Update("update account set amount=amount+#{amount} where id= #{toId}")
    void receive (@Param("toId") Long toId, @Param("amount") Double amount);
}
