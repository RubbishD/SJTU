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

        String[] resultOut = {"asdsdds"}; //start thread
        RanRunable r1 = new RanRunable(param, resultOut);
        Thread t = new Thread(r1);
        t.start();
        try {
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
                Foodmsg foodmsg_msg = new Foodmsg();
                foodmsg_msg = get_food_data(recommendAdapter.array,position);
                System.out.println(foodmsg_msg);
                food_bd.putSerializable("INDEX", foodmsg_msg);
                food_bd.putString("String","hhhhh");
                ((MainActivity)getActivity()).controller.navigate(R.id.action_mainFragment_to_expanding_item,food_bd);
            }
        });


    }

    private Foodmsg get_food_data(JSONArray arr, int position){
        Foodmsg foodmsg = new Foodmsg();
        try {
            JSONObject object = arr.getJSONObject(position);
            foodmsg.setId(object.get("id").toString());
            foodmsg.setName(object.get("food").toString());//菜名
            foodmsg.setBuilding(object.get("building").toString());//
            foodmsg.setFloor(object.get("floor").toString());
            foodmsg.setRestaurant(object.get("restaurant").toString());
            foodmsg.setMerchant(object.get("merchant").toString());
            foodmsg.setPrice("￥"+object.get("price").toString());
            foodmsg.setImg_url(object.get("img_url").toString());
            foodmsg.setMorning_time(object.get("morning").toString());
            foodmsg.setNoon_time(object.get("noon").toString());
            foodmsg.setNight_time(object.get("night").toString());
            foodmsg.setRaw_material(object.get("raw").toString());
            foodmsg.setStaple(object.get("staple").toString());
            foodmsg.setSpicy(object.get("spicy").toString());
            foodmsg.setCalorie(object.get("calorie").toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return foodmsg;
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





//        recyclerView = (RecyclerView)getView().findViewById(R.id.recommend_list);
//        List<Map<String,Object>> list = getData();
//        adapter = new MealRecommendAdapter(mdata,context);
//
//        recommandLayout = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
//
//        recyclerView.setLayoutManager(recommandLayout);
//        recyclerView.setAdapter(adapter);
//    }
        //初始化模拟数据
//    private ArrayList<Map<String,Object>> getData() {
//        Meal meal1 = new Meal();
//        Meal meal2 = new Meal();
//        Meal meal3 = new Meal();
//        meal1.name = "青椒炒肉盖饭";
//        meal1.location = "第二餐饮大楼一楼西餐厅";
//        meal1.spicy = "辣";
//        meal1.price = 16;
//        meal1.calorie = 100;
//        meal2.name = "青椒盖饭";
//        meal2.location = "第二餐饮大楼一楼西餐厅";
//        meal2.spicy = "辣";
//        meal2.price = 16;
//        meal2.calorie = 100;
//        meal3.name = "盖饭";
//        meal3.location = "第二餐饮大楼一楼西餐厅";
//        meal3.spicy = "不辣";
//        meal3.price = 10;
//        meal3.calorie = 110;
//        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
//
//        for (int i = 0;i<3;i++){
////            Meal hhdata = new Meal();
////            int j = Datas[i].length;
////            for(int j = 0;j<Datas[i].length;j++){
////                data.price += Dates[i][j];
////            }
//            Map<String,Object> listitem = new HashMap<String,Object>();
//            listitem.put("菜名",meal1.name);
//            listitem.put("地址",meal1.location);
//            listitem.put("辣度",meal1.spicy);
//            listitem.put("价格",meal1.price);
//            listitem.put("卡路里",meal1.calorie);
//            list.add(listitem);
//        }
//        return (ArrayList<Map<String, Object>>) list;
//    }
//}

