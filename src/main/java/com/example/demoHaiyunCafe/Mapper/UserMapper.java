package com.example.demoHaiyunCafe.Mapper;

import com.example.demoHaiyunCafe.Bean.Item;
import com.example.demoHaiyunCafe.Bean.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * mapper的具体表达式
 */
@Mapper //标记mapper文件位置，否则在Application.class启动类上配置mapper包扫描
@Repository
public interface UserMapper {

    /**
     * 查询用户名是否存在，若存在，不允许注册
     * 注解@Param(value) 若value与可变参数相同，注解可省略
     * 注解@Results  列名和字段名相同，注解可省略
     * @param username
     * @return
     */
    @Select(value = "select u.username,u.password from user u where u.username=#{username}")
    @Results
            ({@Result(property = "username",column = "username"),
                    @Result(property = "password",column = "password")})
    User findUserByName(@Param("username") String username);

    /**
     * 查询用户名是否存在，若存在，不允许注册
     * 注解@Param(value) 若value与可变参数相同，注解可省略
     * 注解@Results  列名和字段名相同，注解可省略
     * @return
     */
    @Select(value = "select * from user")
    List<User> findAll();

    /**
     * 注册  插入一条user记录
     * @param user
     * @return
     */
    @Insert({"insert into user values(#{id},#{username},#{password},null,null,null)"})
    //加入该注解可以保存对象后，查看对象插入id
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    void regist(User user);

    /**
     * 登录
     * @param user
     * @return
     */
    @Select("select u.id from user u where u.username = #{username} and password = #{password}")
    Long login(User user);

    /**修改
     * 针对个人信息的修改
     * @param user
     * @return
     */
    @Update("Update user u where u.id = #{id} set values()")
    Long update(User user);


    /**
     * 删除
     * @param user
     * @return
     */
    @Delete("delete * from user where u.id= #{id}")
    void delete(User user);
}