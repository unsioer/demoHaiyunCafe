package com.example.demoHaiyunCafe.mapper;

import com.example.demoHaiyunCafe.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Integer> {
    //根据菜品名查询菜品
    Item findByItemname(String itemname);

    List<Item> findAllByItemname(String itemname);

    //根据类型查询
    List<Item> findAllByType(String type);

    //根据价格查询
    List<Item> findAllByPrice(Integer price);

}