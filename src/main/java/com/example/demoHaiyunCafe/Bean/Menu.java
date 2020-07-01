package com.example.demoHaiyunCafe.Bean;

public class Menu {
    private String Meal_name;
    private String type;
    private int number;
    private int price;

    public void setMeal_name(String meal_name) {
        Meal_name = meal_name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getMeal_name() {
        return Meal_name;
    }

    public String getType() {
        return type;
    }

    public int getNumber() {
        return number;
    }

    public int getPrice() {
        return price;
    }
}
