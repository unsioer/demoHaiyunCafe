package com.example.demoHaiyunCafe.Mapper;

import com.example.demoHaiyunCafe.Bean.Order;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Calendar;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {
    List<Order> findAllByUid(Integer uid);

    List<Order> findAllByUserAddress(String userAddress);

    List<Order> findAllByIid(Integer iid);

    Page<Order> findAll(Pageable pageable);

    List<Order> findAllByOrderdateContaining(String dateString);
}
