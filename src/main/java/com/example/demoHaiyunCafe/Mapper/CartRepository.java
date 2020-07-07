package com.example.demoHaiyunCafe.Mapper;

import com.example.demoHaiyunCafe.Bean.Cart;
import com.example.demoHaiyunCafe.Bean.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart,Integer>  {

    List<Cart> findAllByUid(Integer uid);
}
