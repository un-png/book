package com.cqgc.service.impl;

import com.cqgc.dao.UserDao;
import com.cqgc.domain.User;
import com.cqgc.dto.GetUserDTO;
import com.cqgc.dto.UseLoginDTO;
import com.cqgc.dto.UserDTO;
import com.cqgc.dto.UserVO;
import com.cqgc.exception.AccountExistException;
import com.cqgc.exception.AccountNotFoundException;
import com.cqgc.exception.InsufficientPermissionsException;
import com.cqgc.exception.PasswordErrorException;
import com.cqgc.service.UserService;
import com.cqgc.threadLocal.BaseContext;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {


    @Autowired
    private UserDao userDao;
    /**
     * 登录
     * @param useLoginDTO
     * @return
     */
    public User login(UseLoginDTO useLoginDTO) {


        String username = useLoginDTO.getUsername();
        String password = useLoginDTO.getPassword();

        User user=userDao.searchUser(username);
        if (user == null){
            throw new AccountNotFoundException("用户不存在");
        }
        if (!user.getPassword().equals(password)){
            throw new PasswordErrorException("密码错误");
        }
        log.info("登录用户ID: {}", user.getId());
        BaseContext.setCurrentId(user.getId());
        log.info("BaseContext中设置的用户ID: {}", BaseContext.getCurrentId());
        return user;
    }
    /**
     * 添加用户
     * @param userDTO
     */
    public void add(UserDTO userDTO) {
        getLoginId();
        User searchUser = userDao.searchUser(userDTO.getUsername());
        if (searchUser!=null){
            throw new AccountExistException("用户已存在");
        }
        userDao.add(userDTO);
    }
    /**
     * 修改用户
     * @param userDTO
     */
    @Override
    public void update(UserDTO userDTO) {
        getLoginId();
        userDao.update(userDTO);
    }

    @Override
    public void delete(Long id) {
        getLoginId();
        if (id==1 && userDao.getAdminCount()==1){
            throw new InsufficientPermissionsException("至少需要一个管理员");
        }
        userDao.delete(id);
    }

    @Override
    public List<UserVO> getUser(GetUserDTO getUserDTO) {
        List<User> users = userDao.getUser(getUserDTO);
        List<UserVO> userVOS = new ArrayList<>();
        for (User user : users) {
            UserVO userVO = new UserVO();
            userVO.setId(user.getId());
            userVO.setUsername(user.getUsername());
            if (user.getRole()==1){
                userVO.setRole("管理员");
            }
            else {
                userVO.setRole("普通用户");
            }
            userVOS.add(userVO);
        }
        return userVOS;
    }

    /**
     * 获取登录用户id
     */
    public void getLoginId(){
        Long id = BaseContext.getCurrentId();
        log.info("从BaseContext中获取的用户ID: {}", id);
        if (id == null) {
            log.error("未能从BaseContext中获取到用户ID，ThreadLocal可能未正确设置或已被清除");
            throw new RuntimeException("未能获取到登录用户ID");
        }
        log.info("当前用户id为{}",id);
        User user = userDao.getUserById(id);
        if (user.getRole()!=1){
            throw new InsufficientPermissionsException("权限不足");
        }
    }

}