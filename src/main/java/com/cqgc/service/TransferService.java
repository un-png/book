package com.cqgc.service;

import com.cqgc.dto.TransferVO;

public interface TransferService {
    TransferVO transfer(Long fromId, Long toId, Double amount);

}
