package com.example.demoHaiyunCafe.Mapper;

import com.example.demoHaiyunCafe.Bean.Item;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ItemRepository extends JpaRepository<Item,Integer> {
    //根据菜品名查询菜品
    Item findByItemname(String itemname);
}