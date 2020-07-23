package com.example.demoHaiyunCafe.service;

import com.example.demoHaiyunCafe.domain.Item;
import com.example.demoHaiyunCafe.domain.Order;
import com.example.demoHaiyunCafe.mapper.ItemRepository;
import com.example.demoHaiyunCafe.mapper.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    @Transactional
    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    @Override
    @Transactional
    public Item findById(Integer id) {
        return itemRepository.findById(id).get();
    }

    @Override
    @Transactional
    public Item findByItemname(String itemname) {
        return itemRepository.findByItemname(itemname);
    }

    @Override
    @Transactional
    public Integer findItemPopularity(Integer iid) {
        List<Order> orderList = orderRepository.findAllByIid(iid);
        Integer popularity = 0;
        for (Order order : orderList) {
            popularity += order.getItemnum();
        }
        return popularity;
    }

    @Override
    @Transactional
    public List<Item> findTop5PopularItems() {
        List<Item> itemList = findAll();
        for (Item i : itemList) {
            i.setPopularity(findItemPopularity(i.getId()));
        }
        itemList.sort(Comparator.comparing(Item::getPopularity).reversed());
        List<Item> result = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            if (i == itemList.size()) break;
            result.add(itemList.get(i));
        }
        return result;
    }

    @Override
    @Transactional
    public Item saveOrUpdateItem(Item item) {

        try {
            itemRepository.save(item);
        } catch (Exception e) {
            throw new RuntimeException("Add Item Error: " + e.getMessage());
        }
        return item;
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        itemRepository.deleteById(id);
    }


    @Override
    @Transactional
    public List<Item> findAllByType(String type) {
        return itemRepository.findAllByType(type);
    }

    @Override
    @Transactional
    public List<Item> findAllByPrice(Integer price) {
        return itemRepository.findAllByPrice(price);
    }

    @Override
    @Transactional
    public List<Item> findAllByItemname(String itemname) {
        return itemRepository.findAllByItemname(itemname);
    }

}
