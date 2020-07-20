package com.example.demoHaiyunCafe.Bean;


import javax.persistence.*;

@Entity
@Table(name = "menu")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false,length = 20)
    private String itemname;
    @Column(nullable = false,length = 20)
    private String type;
    @Column(nullable = false)
    private Integer number;
    @Column(nullable = false)
    private Integer price;
    @Column(nullable = true)
    private String picturepath;

    private Integer popularity;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getPicturepath() {return picturepath;}

    public void setPicturepath(String picturepath) {this.picturepath=picturepath;}

    public Integer getPopularity() { return popularity; }

    public void setPopularity(Integer popularity) {
        this.popularity = popularity;
    }

    public Item(String itemname, String type, Integer number, Integer price) {
        this.itemname = itemname;
        this.type = type;
        this.number = number;
        this.price = price;
    }

    public Item(){

    }

}
