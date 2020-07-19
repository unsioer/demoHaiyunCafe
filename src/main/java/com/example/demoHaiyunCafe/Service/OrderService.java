package com.example.demoHaiyunCafe.Service;

import com.example.demoHaiyunCafe.Bean.Item;
import com.example.demoHaiyunCafe.Bean.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface OrderService {
    List<Order> findAll();

    Order findById(Integer id);

    Order saveOrUpdateOrder(Order order);

    void deleteById(Integer id);

    List<Order> findAllByUid(Integer uid);

    List<Order> findAllByUserAddress(String userAddress);
}
