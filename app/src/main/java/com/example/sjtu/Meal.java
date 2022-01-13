package com.example.sjtu;

public class Meal {
    private String location;
    private String price;
    private String calorie;
    private String spicy;
    private String name;
    private String id;

    public Meal(){
    }

    public Meal(String location,String price,String calorie,String spicy,String name){
        this.price = price;
        this.calorie = calorie;
        this.spicy = spicy;
        this.name = name;
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCalorie() {
        return calorie;
    }

    public void setCalorie(String calorie) {
        this.calorie = calorie;
    }

    public String getSpicy() {
        return spicy;
    }

    public void setSpicy(String spicy) {
        this.spicy = spicy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
