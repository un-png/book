package com.cqgc.exception;

import com.cqgc.domain.Code;
import com.cqgc.domain.Result1;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.cqgc.domain.Result;
import com.cqgc.exception.AccountNotFoundException;
import com.cqgc.exception.PasswordErrorException;
import com.cqgc.exception.BaseException;

@RestControllerAdvice //用于标识当前类为REST风格对应的异常处理器
public class ProjectExceptionAdvice {
    //@ExceptionHandler用于设置当前处理器类对应的异常类型
    @ExceptionHandler(SystemException.class)
    public Result1 doSystemException(SystemException ex){
        //记录日志
        System.out.println("出现系统级异常");
        //发送消息给运维
        //发送邮件给开发人员,ex对象发送给开发人员
        return new Result1(ex.getCode(),null,ex.getMessage());
    }

    @ExceptionHandler(BusinessException.class)
    public Result1 doBusinessException(BusinessException ex){
        System.out.println("出现业务级异常");
        return new Result1(ex.getCode(),null,ex.getMessage());
    }
//    @ExceptionHandler({AccountNotFoundException.class, PasswordErrorException.class})
//    public Result doLoginException(BaseException ex) {
//        // code = 0 表示失败，msg 为具体业务错误信息
//        return Result.error(ex.getMessage());
//    }
    //除了自定义的异常处理器，保留对Exception类型的异常处理，用于处理非预期的异常
//    @ExceptionHandler(Exception.class)
//    public Result1 doOtherException(Exception ex){
//        //记录日志
//        System.out.println("出现其它未知异常");
//        //发送消息给运维
//        //发送邮件给开发人员,ex对象发送给开发人员
//        return new Result1(Code.SYSTEM_UNKNOW_ERR,null,"系统繁忙，请稍后再试！");
//    }
}