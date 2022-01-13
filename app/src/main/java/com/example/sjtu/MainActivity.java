package com.example.sjtu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;
import android.util.Log;

import com.example.sjtu.HttpRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;



public class MainActivity extends AppCompatActivity {
    public NavController controller;
    private ShopFragment shop;
    public Cache cache=new Cache();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initNav(1);
        cache.access_data(2);
        int a=1;
    }

    private void initNav(int type){
        controller = Navigation.findNavController(this ,R.id.main_nav_host_fragment);
        if(type == 1){
            controller.setGraph(R.navigation.main_nav);
            
        }
    }

    public Cache getCache() {
        return cache;
    }
}