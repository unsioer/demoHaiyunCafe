package com.example.demoHaiyunCafe.Service;

import com.example.demoHaiyunCafe.Bean.Order;
import com.example.demoHaiyunCafe.Mapper.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    OrderRepository orderRepository;

    @Override
    @Transactional
    public List<Order> findAll(){
        return orderRepository.findAll();
    }

    @Override
    @Transactional
    public Order findById(Integer id){
        return orderRepository.findById(id).get();
    }

    @Override
    @Transactional
    public List<Integer> findMonthData(Integer year, Integer month) { ;
        String yearmonth = String.format("%04d", year) + "-" + String.format("%02d", month) + "-";
        System.out.println("Start to find.."+yearmonth);
        List<Order> orderList=orderRepository.findAllByOrderdateContaining(yearmonth);
        List<Integer> data = new ArrayList<Integer>();
        System.out.println(data.size());
        data.add(orderList.size());
        Integer monthIncome=0;
        for (Order order : orderList) {
             monthIncome+= order.getItemnum()*order.getItemprice();
        }
        data.add(monthIncome);
        return data;
    }

    @Override
    @Transactional
    public Order saveOrUpdateOrder(Order order){
        try{
            orderRepository.save(order);
        }
        catch (Exception e){
            throw new RuntimeException("Add Item Error: "+e.getMessage());
        }
        return order;
    }

    @Override
    @Transactional
    public void deleteById(Integer id){
        orderRepository.deleteById(id);
    }

    @Override
    @Transactional
    public List<Order> findAllByUid(Integer uid){
        return orderRepository.findAllByUid(uid);
    }
    
    public List<Order> findAllByIid(Integer iid){
        return orderRepository.findAllByIid(iid);
    }   


    @Override
    @Transactional
    public List<Order> findAllByUserAddress(String userAddress){
        return orderRepository.findAllByUserAddress(userAddress);
    }

//    @Override
    @Transactional
    public Page<Order> findAll(Pageable pageable){
        return orderRepository.findAll(pageable);
    }
}
