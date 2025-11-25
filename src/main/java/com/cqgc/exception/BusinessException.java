package com.cqgc.exception;

import lombok.Getter;
import lombok.Setter;

//自定义异常处理器，用于封装异常信息，对异常进行分类
public class BusinessException extends RuntimeException{
    @Getter
    @Setter
    private Integer code;

    public BusinessException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(Integer code,String message,Throwable cause) {
        super(message, cause);
        this.code = code;
    }
}