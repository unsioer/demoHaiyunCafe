package com.example.demoHaiyunCafe.Service;

import com.example.demoHaiyunCafe.Bean.Item;
import com.example.demoHaiyunCafe.Mapper.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService{
    @Autowired
    private ItemRepository itemRepository;

    @Override
    @Transactional
    public List<Item> findAll(){
        return itemRepository.findAll();
    }

    @Override
    @Transactional
    public Item findById(Integer id){
       return itemRepository.findById(id).get();
    }

    @Override
    @Transactional
    public Item saveOrUpdateItem(Item item){
        try{
            itemRepository.save(item);
        }
        catch (Exception e){
            throw new RuntimeException("Add Item Error: "+e.getMessage());
        }
        return item;
    }

    @Override
    @Transactional
    public void deleteById(Integer id){
        itemRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Item findByItemname(String itemname){
        return itemRepository.findByItemname(itemname);
    }
}
