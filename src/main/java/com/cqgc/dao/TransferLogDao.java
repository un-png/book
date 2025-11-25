package com.cqgc.dao;

import com.cqgc.domain.TransferLog;
import org.apache.ibatis.annotations.Insert;

public interface TransferLogDao {
    /**
     * 添加转账日志
     * @param transferLog
     */
    @Insert("insert into transferlog(id, log) values(#{id}, #{log})")
    void addTransferLog(TransferLog transferLog);
}
