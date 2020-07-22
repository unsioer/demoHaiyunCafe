package com.example.demoHaiyunCafe.mapper;

import com.example.demoHaiyunCafe.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart,Integer>  {

    List<Cart> findAllByUid(Integer uid);

    Cart findByUidAndIid(Integer uid,Integer iid);

    void deleteAllByUid(Integer uid);
}
