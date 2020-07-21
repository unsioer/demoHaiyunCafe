package com.example.demoHaiyunCafe.Service;

import com.example.demoHaiyunCafe.Bean.Order;
import com.example.demoHaiyunCafe.Mapper.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
    public List<Integer> findYearData(Integer year) { ;
        String yearString = String.format("%04d", year) + "-";
        System.out.println("Start to find.."+yearString);
        List<Order> orderList=orderRepository.findAllByOrderdateContaining(yearString);
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
    public List<String> findLast30DayData() {
        List<String> data = new ArrayList<String>();
        String dayOrderNumString="",dayIncomeString="";
        Date date=new Date();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -30);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        for(int i=0;i<30;i++) {
            cal.add(Calendar.DATE, 1);
            date=cal.getTime();
            List<Order> orderList = orderRepository.findAllByOrderdateContaining(sdf.format(date));
            dayOrderNumString+=orderList.size()+", ";
            Integer dayIncome=0;
            for (Order order : orderList) {
                dayIncome+= order.getItemnum()*order.getItemprice();
            }
            dayIncomeString+=dayIncome+", ";
        }
        data.add(dayOrderNumString);
        data.add(dayIncomeString);
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
