package com.cqgc.exception;

import lombok.Getter;
import lombok.Setter;

//自定义异常处理器，用于封装异常信息，对异常进行分类
public class SystemException extends RuntimeException{
    //异常的编码
    @Getter
    @Setter
	private Integer code;

    //两个参数的构造方法:状态码，信息
    public SystemException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    //三个参数的构造方法：状态码，信息，异常
    public SystemException(Integer code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }
}