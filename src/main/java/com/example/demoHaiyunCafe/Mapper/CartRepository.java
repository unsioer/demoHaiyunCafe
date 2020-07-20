package com.example.demoHaiyunCafe.Mapper;

import com.example.demoHaiyunCafe.Bean.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart,Integer>  {

    List<Cart> findAllByUid(Integer uid);

    Cart findByUidAndIid(Integer uid,Integer iid);

    void deleteAllByUid(Integer uid);
}
