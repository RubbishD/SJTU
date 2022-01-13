package com.example.sjtu;

import java.io.Serializable;

public class FoodBundle implements Serializable {
    private Cache cache;
    public FoodBundle(Cache c){
        cache = c;
    }
    public void setCache(Cache cache) {
        this.cache = cache;
    }

    public Cache getCache() {
        return cache;
    }
}
