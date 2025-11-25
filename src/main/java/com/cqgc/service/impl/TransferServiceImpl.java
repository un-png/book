package com.cqgc.service.impl;

import com.cqgc.dao.TransferDao;
import com.cqgc.domain.Account;
import com.cqgc.dto.TransferVO;
import com.cqgc.exception.TransferException;
import com.cqgc.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransferServiceImpl implements TransferService {

    @Autowired
    private TransferDao transferDao;

    @Transactional
    public TransferVO transfer(Long fromId, Long toId, Double amount) {
        if (fromId == null || toId == null ||  amount == null){
            throw new TransferException("转账失败，输入信息错误");
        }
        if (fromId.equals(toId)){
            throw new TransferException("转账失败，不能给自己转账");
        }
        Account account= transferDao.getAccount(fromId);
        if (account.getAmount()<amount) {
            throw new TransferException("转账失败，余额不足");
        }
        if (account.getAmount()<0){
            throw new TransferException("转账金额有误");
        }
        transferDao.transfer(fromId,amount);
        transferDao.receive(toId,amount);

        // 返回转账信息
        TransferVO transferVO = new TransferVO();
        transferVO.setFromName(account.getUsername());//获取转账的用户名
        transferVO.setToName(transferDao.getAccount(toId).getUsername());//获取接收的用户名
        transferVO.setAmount(amount);

        return transferVO;
    }
}
