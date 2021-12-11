package com.example.sjtu;

import android.net.sip.SipSession;
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
    private RestaurantAdapter restaurantAdapter;
    private RecyclerView.LayoutManager restaurantLayout;
    private static final String ARG_PREFIX= "param1";
    private ArrayList<String> name;


    String prefix;

    public  RestaurantScrollingFragment(){

    }

    public static RestaurantScrollingFragment newInstance(String arg_prefix) {
        RestaurantScrollingFragment fragment = new RestaurantScrollingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PREFIX,arg_prefix);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            prefix = getArguments().getString(ARG_PREFIX);
        }
        else{
            prefix = "default";
        }
    }

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

        restaurantView = (RecyclerView) getView().findViewById(R.id.restaurant_view);
        name = getData();
        restaurantAdapter=new RestaurantAdapter(name);


        restaurantLayout=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);

        restaurantView.setAdapter(restaurantAdapter);
        restaurantView.setLayoutManager(restaurantLayout);
    }
    public void setOnItemClickListener(RestaurantAdapter.OnItemClickListener onItemClickListener){
        restaurantAdapter.setOnItemClickListener(onItemClickListener);
    }


    public void updateData(){
        name.clear();
        for (int i = 0; i < 20; i++) {
            name.add(i + prefix);
        }
        restaurantAdapter.notifyItemRangeChanged(0,20);
    }
    private ArrayList<String> getData() {
        ArrayList<String> data = new ArrayList<>();
        String temp = prefix;
        for (int i = 0; i < 20; i++) {
            data.add(i + temp);
        }
        return data;
    }

    public void setArgPrefix(String arg){
        prefix=arg;
        updateData();
    }
}
