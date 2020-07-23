package com.example.demoHaiyunCafe.service;

import com.example.demoHaiyunCafe.domain.Result;
import com.example.demoHaiyunCafe.domain.User;
import com.example.demoHaiyunCafe.mapper.UserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = RuntimeException.class)
public class UserService {
    @Autowired
    private UserMapper userMapper;
    //private UserRepository userRepository;

    /**
     * 注册
     *
     * @param user 参数封装
     * @return Result
     */
    public Result regist(User user) {
        Result result = new Result();
        result.setSuccess(false);
        result.setDetail(null);
        user.setAuthority("user");
        try {
            User existUser = userMapper.findUserByName(user.getUsername());
            if (existUser != null) {
                //如果用户名已存在
                result.setMsg("用户名已存在");
            } else {
                userMapper.regist(user);
                //System.out.println(user.getId());
                result.setMsg("注册成功");
                result.setSuccess(true);
                result.setDetail(user);
            }
        } catch (Exception e) {
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 登录
     *
     * @param user 用户名和密码
     * @return Result
     */
    public Result login(User user) {
        Result result = new Result();
        result.setSuccess(false);
        result.setDetail(null);
        try {
            Long userId = userMapper.login(user);
            if (userId == null) {
                result.setMsg("用户名或密码错误");
            } else {
                result.setMsg("登录成功");
                result.setSuccess(true);
                user.setId(userId);
                user.setAuthority(userMapper.searchAuthority(user));
                result.setDetail(user);
            }
        } catch (Exception e) {
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    /*
     * @return Result
     */
    @Transactional
    public List<User> findAll() {
        return userMapper.findAll();
    }


    @Transactional
    public User findById(Long id) {
        return userMapper.findUserById(id);
    }

    ;

    @Transactional
    public User saveOrUpdateUser(User user) {

        try {
            userMapper.update(user);
        } catch (Exception e) {
            throw new RuntimeException("Add user Error: " + e.getMessage());
        }
        return user;
    }

    @Transactional
    public void deleteById(Long id) {
        userMapper.deleteById(id);
    }

    ;
}