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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RecommendFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecommendFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private RecyclerView recommendView;
    private MealRecommendAdapter recommendAdapter;
    private RecyclerView.LayoutManager recommendLayout;
    private ArrayList<Meal> meals;
    private JSONArray arr = new JSONArray();

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
        View view = inflater.inflate(R.layout.fragment_recommend, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String param = "recommend";

        class RanRunable implements Runnable {
            String param;
            String[] result;

            public RanRunable(String param1, String[] param2) {
                param = param1;
                result = param2;
            }

            @Override
            public void run() {
                HttpRequest request = new HttpRequest();
                String url0 = "http://119.3.110.15:33/recommend"; // http://119.3.110.15:33
                // param = "username=root&message=123"; // param string of get request
                result[0] = request.get((url0 + "?" + param));
            }
        }

        String[] resultOut = {"[{\"id\":23}]"}; //start thread

        try {
            RanRunable r1 = new RanRunable(param, resultOut);
            Thread t = new Thread(r1);
            t.start();
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(resultOut[0]);
        //格式转换
        JSONArray arr = new JSONArray();
        try {
            arr = new JSONArray(resultOut[0]);
            ArrayList<Meal> meal_data = new ArrayList<>();
            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj = arr.getJSONObject(i);
                Meal data = new Meal();
                data = getData(obj);
                meal_data.add(data);
            }
            meals = meal_data;
        } catch (JSONException e) {
            e.printStackTrace();
        }


        recommendView = getView().findViewById(R.id.meal_recommend_view);
        recommendAdapter = new MealRecommendAdapter(meals,arr);
        recommendLayout = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recommendView.setAdapter(recommendAdapter);
        recommendView.setLayoutManager(recommendLayout);
        recommendAdapter.setOnItemClickListener(new MealRecommendAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Bundle food_bd = new Bundle();
                try {
                    food_bd.putString("food", recommendAdapter.array.getJSONObject(position).toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                ((MainActivity)getActivity()).controller.navigate(R.id.action_mainFragment_to_expanding_item,food_bd);
            }
        });


    }


    private Meal getData(JSONObject object) {
        Meal meal = new Meal();
        try {
            meal.setSpicy(object.get("spicy").toString());
            meal.setCalorie(object.get("calorie").toString());
            meal.setName(object.get("food").toString());
            meal.setLocation(object.get("building")+""+object.get("floor")+object.get("restaurant"));
            meal.setPrice("￥"+object.get("price").toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return meal;
    }
}





