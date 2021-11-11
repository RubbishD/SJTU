package com.example.sjtu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Bundle;


public class MainActivity extends AppCompatActivity {
    private NavController controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initNav(1);
    }

    private void initNav(int type){
        controller = Navigation.findNavController(this ,R.id.main_nav_host_fragment);
        if(type == 1){
            controller.setGraph(R.navigation.main_nav);
            
        }
    }


}