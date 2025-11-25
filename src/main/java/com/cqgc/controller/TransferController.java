package com.cqgc.controller;

import com.cqgc.domain.Account;
import com.cqgc.domain.Result;
import com.cqgc.dto.TransferVO;
import com.cqgc.service.TransferService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transfer")
@Api(tags = "转账接口管理")
@Slf4j
public class TransferController {

    @Autowired
    private TransferService transferService;

    @PostMapping("/transfer")
    @ApiOperation("用户转账")
    public Result<TransferVO> transfer(@RequestParam Long fromId,
                                       @RequestParam Long toId,
                                       @RequestParam Double amount){
        log.info("{}向{}转账:{}元",fromId,toId,amount);
        TransferVO transfer = transferService.transfer(fromId,toId,amount);
        return Result.success(transfer);
    }
}
