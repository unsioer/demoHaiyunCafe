package com.example.demoHaiyunCafe.service;

import com.example.demoHaiyunCafe.domain.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    List<Order> findAll();

    Order findById(Integer id);

    Order saveOrUpdateOrder(Order order);

    void deleteById(Integer id);

    List<Order> findAllByUid(Integer uid);
    
    List<Order> findAllByIid(Integer uid);

    List<Order> findAllByUserAddress(String userAddress);

    List<Integer> findMonthData(Integer year, Integer month);

    List<Integer> findYearData(Integer year);

    List<String> findLast30DayData();
}
