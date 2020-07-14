package com.example.demoHaiyunCafe.Service;


import com.example.demoHaiyunCafe.Bean.Cart;
import com.example.demoHaiyunCafe.Bean.Item;
import com.example.demoHaiyunCafe.Mapper.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;


    @Override
    @Transactional
    public List<Cart> findAll(){
        return cartRepository.findAll();
    }


    @Override
    @Transactional
    public Cart findById(Integer id){
        return cartRepository.findById(id).get();
    }

    @Override
    @Transactional
    public Cart saveOrUpdateCart(Cart cart){
        try{
            cartRepository.save(cart);
        }
        catch (Exception e) {
            throw new RuntimeException("Add Item Error: " + e.getMessage());
        }
        return cart;
    }

    @Override
    @Transactional
    public void deleteById(Integer id){
        cartRepository.deleteById(id);
    }

    @Override
    @Transactional
    public List<Cart> findAllByUid(Integer uid){
        return cartRepository.findAllByUid(uid);
    }

    @Override
    @Transactional
    public Cart findByUidAndIid(Integer uid, Integer iid){
        return cartRepository.findByUidAndIid(uid,iid);
    }

    @Override
    @Transactional
    public void deleteAllByUid(Integer uid){
        cartRepository.deleteAllByUid(uid);
    }
}
