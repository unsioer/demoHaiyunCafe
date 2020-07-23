package com.example.demoHaiyunCafe.mapper;

import com.example.demoHaiyunCafe.domain.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findAllByUid(Integer uid);

    List<Order> findAllByUserAddress(String userAddress);

    List<Order> findAllByIid(Integer iid);

    Page<Order> findAll(Pageable pageable);

    List<Order> findAllByOrderdateContaining(String dateString);
}
