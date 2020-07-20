package com.example.demoHaiyunCafe.Mapper;

import com.example.demoHaiyunCafe.Bean.Item;
import com.example.demoHaiyunCafe.Bean.Order;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ItemRepository extends JpaRepository<Item,Integer> {
    //根据菜品名查询菜品
    Item findByItemname(String itemname);

    //Item findById(Integer id);
    
    List<Item> findAllByItemname(String itemname);


    //根据类型查询
    List<Item> findAllByType(String type);

    //根据价格查询
    List<Item> findAllByPrice(Integer price);
    
}