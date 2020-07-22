package com.example.demoHaiyunCafe.domain;

import javax.persistence.*;

@Entity
@Table(name = "userorder")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer uid;

    @Column(nullable = false)
    private Integer iid;

    @Column(nullable = false,length = 20)
    private String itemname;

    @Column(nullable = false)
    private Integer itemprice;

    @Column(nullable = false)
    private Integer itemnum;

    @Column(nullable = false,length = 20)
    private String orderstate;

    @Column(nullable = false,length = 20)
    private String orderdate;

    @Column(nullable = false,length = 20)
    private String userAddress;

    public Order(){

    }
    public Order(Integer uid, Integer iid, String itemname, Integer itemprice, Integer itemnum, String orderstate, String orderdate,String userAddress) {
        this.uid = uid;
        this.iid = iid;
        this.itemname = itemname;
        this.itemprice = itemprice;
        this.itemnum = itemnum;
        this.orderstate = orderstate;
        this.orderdate = orderdate;
        this.userAddress = userAddress;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getIid() {
        return iid;
    }

    public void setIid(Integer iid) {
        this.iid = iid;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public Integer getItemprice() {
        return itemprice;
    }

    public void setItemprice(Integer itemprice) {
        this.itemprice = itemprice;
    }

    public Integer getItemnum() {
        return itemnum;
    }

    public void setItemnum(Integer itemnum) {
        this.itemnum = itemnum;
    }

    public String getOrderstate() {
        return orderstate;
    }

    public void setOrderstate(String orderstate) {
        this.orderstate = orderstate;
    }

    public String getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(String orderdate) {
        this.orderdate = orderdate;
    }

}
