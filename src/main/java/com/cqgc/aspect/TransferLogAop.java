package com.cqgc.aspect;

import com.cqgc.dao.TransferDao;
import com.cqgc.dao.TransferLogDao;
import com.cqgc.domain.TransferLog;
import com.cqgc.dto.TransferVO;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class TransferLogAop {


    @Autowired
    private TransferDao transferDao;
    @Autowired
    private TransferLogDao transferLogDao;

    // 定义切入点：TransferServiceImpl中的transfer方法
    @Pointcut("execution(* com.cqgc.service.impl.TransferServiceImpl.transfer(..))")
    public void transferPointcut() {}

    // 转账成功后记录日志
    @AfterReturning(pointcut = "transferPointcut()", returning = "result")
    public void logTransferSuccess(JoinPoint joinPoint, Object result) {
        try {
            // // 获取方法参数
            // Object[] args = joinPoint.getArgs();
            // Long fromId = (Long) args[0];
            // Long toId = (Long) args[1];
            // Double amount = (Double) args[2];

            // String fromName=transferDao.getAccount(fromId).getUsername();
            // String toName=transferDao.getAccount(toId).getUsername();

            // // 创建转账日志对象
            // TransferLog transferLog = new TransferLog();
            // transferLog.setId(System.currentTimeMillis()); // 使用当前时间戳作为ID

            // transferLog.setLog(fromName+" 向 "+toName+"转账成功: " + amount+"元");

            // transferLogDao.addTransferLog(transferLog);
            // log.info("转账成功，已记录日志: " + transferLog.getLog());
            if (result instanceof TransferVO) {
                TransferVO vo = (TransferVO) result;
                String fromName = vo.getFromName();
                String toName = vo.getToName();
                Double amount = vo.getAmount();
    
                // 这里就不需要再查 DAO 了，直接用返回的 VO
                TransferLog transferLog = new TransferLog();
                transferLog.setId(System.currentTimeMillis());
                transferLog.setLog(fromName + " 向 " + toName + " 转账成功: " + amount + " 元");
                transferLogDao.addTransferLog(transferLog);
            } else {
                log.warn("transfer 方法返回的不是 TransferVO，实际类型：{}", 
                         result == null ? "null" : result.getClass().getName());
            }
        } catch (Exception e) {
            // 日志记录失败不应该影响主流程
            e.printStackTrace();
        }
    }
}