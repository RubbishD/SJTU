package com.example.sjtu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RestaurantScrollingFragment extends Fragment {

    private RecyclerView restaurantView;
    private RecyclerView.Adapter restaurantAdapter;
    private RecyclerView.LayoutManager restaurantLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_restraurant_scrolling, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        restaurantView = (RecyclerView) getActivity().findViewById(R.id.restaurant_view);
        restaurantAdapter=new RestaurantAdapter(getData());
        restaurantLayout=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
    }

    private ArrayList<String> getData(){
        ArrayList<String> data = new ArrayList<>();
        String temp = "item";
        for(int i =0;i<20;i++){
            data.add(i+temp);
        }
        return data;
    }
}