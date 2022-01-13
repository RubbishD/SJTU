package com.example.sjtu;

import java.io.Serializable;
import java.util.ArrayList;

public class Food implements Serializable {

    private static final long SerialVersionUID = 1L;

    private String id;
    private String building;//餐饮大楼
    private String floor;//楼层
    private String restaurant;//餐厅
    private String merchant;//商铺
    private String morning_time;  //全天-0，早-1，中-2，晚-3
    private String noon_time;
    private String night_time;
    private String name;//菜名
    private String price;//价格
    private String raw_material;//原材料
    private String spicy;//不辣-0，微辣-1，辣-2
    private String vegetat;//素-0，少肉-1，多肉-2
    private String staple;//主食
    private String calorie;//卡路里
    private String img_url;//图片url
    private ArrayList<String> comment;

    public String  getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    public String getMorning_time() {
        return morning_time;
    }

    public void setMorning_time(String morning_time) {
        this.morning_time = morning_time;
    }

    public String getNoon_time() {
        return noon_time;
    }

    public void setNoon_time(String noon_time) {
        this.noon_time = noon_time;
    }

    public String getNight_time() {
        return night_time;
    }

    public void setNight_time(String night_time) {
        this.night_time = night_time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRaw_material() {
        return raw_material;
    }

    public void setRaw_material(String raw_material) {
        this.raw_material = raw_material;
    }

    public String getSpicy() {
        return spicy;
    }

    public void setSpicy(String spicy) {
        this.spicy = spicy;
    }

    public String getVegetat() {
        return vegetat;
    }

    public void setVegetat(String vegetat) {
        this.vegetat = vegetat;
    }

    public String getStaple() {
        return staple;
    }

    public void setStaple(String staple) {
        this.staple = staple;
    }

    public String getCalorie() {
        return calorie;
    }

    public void setCalorie(String calorie) {
        this.calorie = calorie;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }


    public ArrayList<String> getComment() {
        return comment;
    }

    public void setComment(ArrayList<String> comment) {
        this.comment = comment;
    }
}
