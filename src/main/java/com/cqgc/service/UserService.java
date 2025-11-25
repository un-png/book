package com.cqgc.service;

import com.cqgc.domain.User;
import com.cqgc.dto.GetUserDTO;
import com.cqgc.dto.UseLoginDTO;
import com.cqgc.dto.UserDTO;
import com.cqgc.dto.UserVO;

import java.util.List;

public interface UserService {
    User login(UseLoginDTO useLoginDTO);

    void add(UserDTO userDTO);
    // 修改用户信息
    void update(UserDTO userDTO);

    void delete(Long id);
    // 查询用户
    List<UserVO> getUser(GetUserDTO getUserDTO);
}
