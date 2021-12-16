package com.example.sjtu;

public class Food {
    private String restaurant;//餐饮大楼
    private String floor;//楼层
    private String canteen;//餐厅
    private String merchant;//商铺
    private int time;  //全天-0，早-1，中-2，晚-3
    private String name;//菜名
    private int price;//价格
    private String raw_material;//原材料
    private int spicy;//不辣-0，微辣-1，辣-2
    private int vegetat;//素-0，少肉-1，多肉-2
    private String staple;//主食
    private int calorie;//卡路里
    private String img_url = null;//图片url

    public String getRestaurant() {
        return restaurant;
    }

    public String getFloor() {
        return floor;
    }

    public String getCanteen() {
        return canteen;
    }

    public String getMerchant() {
        return merchant;
    }

    public String getName() {
        return name;
    }

    public int getTime() {
        return time;
    }

    public int getPrice() {
        return price;
    }

    public String getRaw_material() {
        return raw_material;
    }

    public int getSpicy() {
        return spicy;
    }

    public int getCalorie() {
        return calorie;
    }

    public int getVegetat() {
        return vegetat;
    }

    public String getStaple() {
        return staple;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public void setCanteen(String canteen) {
        this.canteen = canteen;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setRaw_material(String raw_material) {
        this.raw_material = raw_material;
    }

    public void setSpicy(int spicy) {
        this.spicy = spicy;
    }

    public void setVegetat(int vegetat) {
        this.vegetat = vegetat;
    }

    public void setCalorie(int calorie) {
        this.calorie = calorie;
    }

    public void setStaple(String staple) {
        this.staple = staple;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
}
