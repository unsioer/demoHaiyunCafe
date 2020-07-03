package com.example.demoHaiyunCafe.Mapper;

import com.example.demoHaiyunCafe.Bean.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {
    //根据用户名查询用户
    User findByUsername(String username);
    List<User> findAllByUsername(String username);

}