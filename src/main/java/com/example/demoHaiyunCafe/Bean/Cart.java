package com.example.demoHaiyunCafe.Bean;

import javax.persistence.*;

@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer iid;

    @Column(nullable = false)
    private Integer uid;

    @Column(nullable = false)
    private Integer num;

    @Column(nullable =  false)
    private String itemName;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = true)
    private String picturepath;


    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getPicturepath() {
        return picturepath;
    }

    public void setPicturepath(String picturepath) {
        this.picturepath = picturepath;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public Cart(){

    }

    public Cart(Integer iid, Integer uid, Integer num, String itemName, Integer price, String picturepath) {
        this.iid = iid;
        this.uid = uid;
        this.num = num;
        this.itemName = itemName;
        this.price = price;
        this.picturepath = picturepath;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIid() {
        return iid;
    }

    public void setIid(Integer iid) {
        this.iid = iid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
