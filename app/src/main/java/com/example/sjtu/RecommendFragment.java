package com.example.sjtu;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RecommendFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecommendFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layout;
    private ArrayList<Meal> mdata;
    private String mParam1;
    private String mParam2;


    public RecommendFragment() {
        // Required empty public constructor
    }


    public static RecommendFragment newInstance(String param1, String param2) {
        RecommendFragment fragment = new RecommendFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recommend,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //
        init();
    }

    private void init(){
        recyclerView = (RecyclerView)getView().findViewById(R.id.recommend_list);
        getData();
        adapter = new MealRecommendAdapter(mdata);


        layout = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);

        recyclerView.setLayoutManager(layout);
        recyclerView.setAdapter(adapter);
    }
    //初始化模拟数据
    private void getData() {
        mdata = new ArrayList<Meal>();
        for (int i = 0;i<4;i++){
            Meal hhdata = new Meal();
//            int j = Datas[i].length;
//            for(int j = 0;j<Datas[i].length;j++){
//                data.price += Dates[i][j];
//            }
            hhdata.price = i;
            hhdata.location = "第"+i+"餐饮大楼"+"第"+i+"楼"+"西餐厅";
            hhdata.calorie = i+100;
            hhdata.spicy = "辣";
            hhdata.name = "青椒炒肉"+i;
            mdata.add(hhdata);
        }

    }
}

