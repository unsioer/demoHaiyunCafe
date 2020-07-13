package com.example.demoHaiyunCafe.Service;

import com.example.demoHaiyunCafe.Bean.Cart;
import com.example.demoHaiyunCafe.Bean.Item;

import java.util.List;

public interface CartService {

    List<Cart> findAll();

    Cart findById(Integer id);

    Cart saveOrUpdateCart(Cart cart);

    void deleteById(Integer id);

    List<Cart> findAllByUid(Integer uid);

    Cart findByUidAndIid(Integer uid, Integer iid);

    void deleteAllByUid(Integer uid);
}
