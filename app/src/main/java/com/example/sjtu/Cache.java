package com.example.sjtu;

import androidx.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class Cache {
    ArrayList<Building> data = new ArrayList<>(7);

    ArrayList<Food> ordered = new ArrayList<>();

    private int total_price=0;

    public Cache(){
        for(int i=0;i<7;i++){
            data.add(new Building(i));
        }
    }
    public void removeOrdered(Food food){
        ordered.remove(food);
    }


    public int getTotal_price(){
        total_price=0;
        for(Food f:ordered){
            total_price+=(0);
        }
        return total_price;

    }
    public double cal_total(){
        double total = 0;
        for (Food f:ordered)
        {
            total+=f.price*f.orderNum;
        }
        return total;
    }
    //load data
    public void loadData(JSONObject json,int i) throws JSONException {
        data.get(i).addRestaurants(json);
    }

    public void access_data(Integer i){

        String param = "building="+i;

        class RanRunable implements Runnable {
            String param;
            String[] result;
            public RanRunable(String param1, String[] param2){
                param = param1;
                result = param2;
            }
            @Override
            public void run() {
                HttpRequest request = new HttpRequest();
                String url0 = "http://119.3.110.15:33/plan"; // http://119.3.110.15:33
                // param = "username=root&message=123"; // param string of get request
                result[0] = request.get((url0+"?"+param));
            }
        }


        String[] resultOut = {"asdsdds"}; //start thread
        RanRunable r1 = new RanRunable(param, resultOut);
        Thread t = new Thread(r1);
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            int a =1;

            JSONObject arr = new JSONObject(resultOut[0]).getJSONObject("ret");
            loadData(arr,i);


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    public void setZero(){
        for (Food f:ordered)
        {
            f.orderNum=0;
        }
    }
    public double calory(){
        double cal = 0;
        for (Food f:ordered)
        {
            cal+=f.price*f.orderNum;
        }
        return cal;
    }

}



class RestaurantView{
    public int building;
    public String restaurantName;
    public String merChantName;
    public ArrayList<String> display=new ArrayList<String>();
    public RestaurantView(int i,String a,String b,ArrayList<String> c){
        building = i;
        restaurantName =a;
        merChantName = b;
        display = c;
    }



}

class Food{


    public Double calorie;
    public Double price;
    public String name;
    public String raw;
    public String url;

    public int orderNum = 0;
    public Food(String s,JSONObject json) throws JSONException {
        name = s;
        price = json.getDouble("price");
        raw = json.getString("raw");
        url = json.getString("url");
        calorie = json.getDouble("calorie");

    }

}


class Merchant{
    public ArrayList<Food> foods = new ArrayList<>();
    public String merchantName;
    public boolean f=true;
    public Merchant(){

    }
    public Merchant(boolean e){
        f=e;
    }
    public Merchant(String s,JSONObject json) throws JSONException {
        merchantName = s;
        Iterator iterator = json.keys();
        while (iterator.hasNext()){
            String foodName = (String)iterator.next();
            JSONObject food = json.getJSONObject(foodName);
            foods.add(new Food(foodName,food));

        }
    }
}

class Restaurant{
    public String restaurantName;
    ArrayList<Merchant> merchants = new ArrayList<>();
    public boolean f=true;
    public Restaurant(){

    }
    public Restaurant(boolean a){
        f=a;
    }
    public Restaurant(String s,JSONObject json) throws JSONException {
        restaurantName =s;
        Iterator iterator = json.keys();
        while (iterator.hasNext()){
            String merChantName = (String)iterator.next();
            JSONObject foods = json.getJSONObject(merChantName);
            merchants.add(new Merchant(merChantName,foods));

        }
    }
    public ArrayList<RestaurantView> getView(int s){
        ArrayList<RestaurantView> res = new ArrayList<>();
        for (Merchant tmp:merchants){
            ArrayList<String> urls = new ArrayList<>();
            for(int i=0;i<3;i++)
            {
                urls.add(tmp.foods.get(i).url);
            }
            res.add(new RestaurantView(s,restaurantName,tmp.merchantName,urls));
        }
        return res;
    }
    public Merchant getFoods(String r){
        for (Merchant tmp:merchants){
            if (tmp.merchantName==r)
                return tmp;
        }
        return new Merchant(false);
    }
}

class Building{
    public ArrayList<Restaurant> restaurants= new ArrayList<>();
    public int i;

    public Building(int in){i = in;}
    public void addRestaurants(JSONObject json) throws JSONException {
        Iterator iterator = json.keys();
        while (iterator.hasNext()){
            String restaurantName = (String)iterator.next();
            JSONObject merchants = json.getJSONObject(restaurantName);
            restaurants.add(new Restaurant(restaurantName,merchants));

        }
    }

    public ArrayList<RestaurantView> getRestaurantsView() {
        ArrayList<RestaurantView> res = new ArrayList<>();
        for(Restaurant tmp:restaurants){
            res.addAll(tmp.getView(i));
        }
        return res;
    }
    public Restaurant getRestaurants(String r){
        for (Restaurant tmp:restaurants){
            if (tmp.restaurantName==r)
                return tmp;
        }
        return new Restaurant(false);
    }

}


