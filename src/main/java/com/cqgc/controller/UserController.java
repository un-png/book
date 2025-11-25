package com.cqgc.controller;

import com.cqgc.domain.Result;
import com.cqgc.domain.User;
import com.cqgc.dto.GetUserDTO;
import com.cqgc.dto.UseLoginDTO;
import com.cqgc.dto.UserDTO;
import com.cqgc.dto.UserVO;
import com.cqgc.service.UserService;
import com.cqgc.threadLocal.BaseContext;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/user")
@Slf4j
@Api(tags = "用户模块")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/login")
    @ApiOperation("用户登录")
    public Result login(@RequestBody UseLoginDTO useLoginDTO, HttpServletRequest request) {
        log.info("用户登录,{}", useLoginDTO);
        User user = userService.login(useLoginDTO);
        // 将用户ID存储到Session中，供拦截器使用
        request.getSession().setAttribute("userId", user.getId());
        log.info("用户ID已存储到Session: {}", user.getId());
        return Result.success(user);
    }
    
    @PostMapping("/add")
    @ApiOperation("添加用户")
    public Result add(@RequestBody UserDTO userDTO){
        log.info("添加用户,{}",userDTO);
        userService.add(userDTO);
        return Result.success();
    }
    @PutMapping("/update")
    @ApiOperation("修改用户信息")
    public Result update(@RequestBody UserDTO userDTO){
        log.info("修改用户信息,{}",userDTO);
        userService.update(userDTO);
        return Result.success();
    }
    @DeleteMapping("/delete/{id}")
    @ApiOperation("删除用户")
    public Result delete(@PathVariable Long id){
        log.info("删除用户,{}",id);
        userService.delete(id);
        return Result.success();
    }
    @GetMapping(value = "/getUser")
    @ApiOperation("获取用户信息")
    public Result getUser(GetUserDTO getUserDTO){
        log.info("获取用户信息");
        List<UserVO> users = userService.getUser(getUserDTO);
        return Result.success(users);
    }

}