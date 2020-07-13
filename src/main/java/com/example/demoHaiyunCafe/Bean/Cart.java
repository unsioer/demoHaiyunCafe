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

    public Integer getId() {
        return id;
    }

    public Cart(){

    }
    public Cart(Integer iid, Integer uid, Integer num) {
        this.iid = iid;
        this.uid = uid;
        this.num = num;
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
