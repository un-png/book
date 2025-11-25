package com.cqgc.aspect;


import com.cqgc.service.UserService;
import com.cqgc.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *根据登录用户的权限来判断是否能执行操作
 **/
@Aspect
@Component
@Slf4j
public class BookRoleAOP {

    @Autowired
    private UserServiceImpl userService;

    @Pointcut("execution(* com.cqgc.service.impl.BookServiceImpl.*(..))&&"+
              "!execution(* com.cqgc.service.impl.BookServiceImpl.getById(..)) &&"+
              "!execution(* com.cqgc.service.impl.BookServiceImpl.getAll(..)) &&"+
              "!execution(* com.cqgc.service.impl.BookServiceImpl.getBooks(..))")
    public void bookModificationPointcut() {
    }

    @Before("bookModificationPointcut()")
    public void checkUserRole(){
        userService.getLoginId();
    }


}
