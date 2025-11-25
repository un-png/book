package com.cqgc.dao;

import com.cqgc.domain.User;
import com.cqgc.dto.GetUserDTO;
import com.cqgc.dto.UserDTO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserDao {
    @Select("select * from user where username=#{username}")
    User searchUser(String username);

    @Select("select * from user where id=#{id}")
    User getUserById(Long id);

    @Insert("insert into user(username,password) values(#{username},#{password})")
    void add(UserDTO userDTO);

    void update(UserDTO userDTO);
    @Select("select count(*) from user where role=1")
    int getAdminCount();
    @Delete("delete from user where id=#{id}")
    void delete(Long id);

    List<User> getUser(GetUserDTO getUserDTO);
}
