package com.example.demoHaiyunCafe.Mapper;

import com.example.demoHaiyunCafe.Bean.Order;
import com.example.demoHaiyunCafe.Bean.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.apache.ibatis.annotations.*;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Integer> {
    List<Order> findAllByUid(Integer uid);
    
    List<Order> findAllByIid(Integer uid);
    
    List<Order> findAll();
}
