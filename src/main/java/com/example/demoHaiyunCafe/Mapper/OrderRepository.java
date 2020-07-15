package com.example.demoHaiyunCafe.Mapper;

import com.example.demoHaiyunCafe.Bean.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Integer> {
    List<Order> findAllByUid(Integer uid);

}
