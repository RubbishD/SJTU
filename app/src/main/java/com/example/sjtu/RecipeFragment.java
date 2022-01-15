
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
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RecipeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecipeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Foodmsg food = new Foodmsg();
    private TextView tv_name;
    private TextView tv_location;
    private TextView tv_merchant;
    private TextView tv_price;
    private WebView img;
    private TextView tv_morning;
    private TextView tv_noon;
    private TextView tv_night;
    private TextView tv_raw;
    private TextView tv_calorie;
    private TextView tv_spicy;
    private TextView tv_staple;
    private TextView tv_veg;
    private EditText et_comment;
    private Button btn_commit;
    private RecyclerView comment_view;
    private JSONObject object;
    private CommentMessage commentMessage;
    private ArrayList<CommentMessage> commentdata = new ArrayList<>();
    private CommentAdapter commentAdapter;
    private RecyclerView.LayoutManager commentLayout;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RecipeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RecipeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RecipeFragment newInstance(String param1, String param2) {
        RecipeFragment fragment = new RecipeFragment();
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
            String data =getArguments().getString("food");
            int a =1;
            try {
                //to debug
                object = new JSONObject(data);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.expanding_sub_item, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tv_name = getActivity().findViewById(R.id.food_name);
        tv_location = getActivity().findViewById(R.id.food_location);
        tv_merchant = getActivity().findViewById(R.id.food_merchant);
        tv_price = getActivity().findViewById(R.id.food_price);
        img = getActivity().findViewById(R.id.food_img);
        tv_morning = getActivity().findViewById(R.id.moring);
        tv_noon = getActivity().findViewById(R.id.noon);
        tv_night = getActivity().findViewById(R.id.night);
        tv_raw = getActivity().findViewById(R.id.single_material);
        tv_calorie = getActivity().findViewById(R.id.single_calorie);
        tv_spicy = getActivity().findViewById(R.id.single_spicy);
        tv_staple = getActivity().findViewById(R.id.single_staple);
        et_comment = getActivity().findViewById(R.id.comment_editText);
        btn_commit = getActivity().findViewById(R.id.comment_submit_btn);
        comment_view = getView().findViewById(R.id.food_comment_view);

        String param = null;
        try {
            param = "id="+object.get("id").toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }

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
                String url0 = "http://119.3.110.15:33/request_comment"; // http://119.3.110.15:33
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

        //格式转换
        JSONArray arr = new JSONArray();
        try {
            arr = new JSONArray(resultOut[0]);
            ArrayList<CommentMessage> comment_data = new ArrayList<>();
            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj = arr.getJSONObject(i);
                CommentMessage data = new CommentMessage(obj.get("comment").toString(),obj.get("time").toString());
                comment_data.add(data);
            }
            commentdata = comment_data;
        } catch (JSONException e) {
            e.printStackTrace();
        }



        //传入参数

        WebSettings webSettings = img.getSettings();
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);// viewport
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);
        webSettings.setSupportZoom(true);

        try{
            img.loadUrl(object.get("img_url").toString());
            tv_name.setText(object.get("food").toString());
            tv_location.setText(object.get("building").toString()+object.get("floor").toString()+object.get("restaurant").toString());
            tv_merchant.setText(object.get("merchant").toString());
            tv_price.setText("￥"+object.get("price").toString());
            if(object.get("morning").toString().equals("1")){
                tv_morning.setText("早餐供应");}
            else {
                tv_morning.setText("无早餐供应");
            }
            if(object.get("noon").toString().equals("1")){
                tv_noon.setText("午餐供应");}
            else {
                tv_noon.setText("无午餐供应");
            }
            if (object.get("night").toString().equals("1")){
                tv_night.setText("晚餐供应");}
            else {
                tv_night.setText("无晚餐供应");
            }
            tv_raw.setText(object.get("raw").toString());
            tv_calorie.setText(object.get("calorie").toString());
            tv_spicy.setText(object.get("spicy").toString());
            tv_staple.setText(object.get("staple").toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        btn_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String commentmsg = et_comment.getText().toString();
                SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd HH:mm");
                String time=format.format(new Date(System.currentTimeMillis()));
                commentMessage = new CommentMessage(commentmsg,time);
                commentdata.add(commentMessage);
                commentAdapter.notifyItemChanged(commentdata.size(),commentMessage);
                et_comment.setText("");
                class Ranable implements Runnable {
                    String param;

                    public Ranable(String param1) {
                        param = param1;
                    }

                    @Override
                    public void run() {
                        HttpRequest request = new HttpRequest();
                        String url0 = "http://119.3.110.15:33/upload_comment"; // http://119.3.110.15:33
                        // param = "username=root&message=123"; // param string of get request
                        String test = request.get((url0 + "?" + param));
                    }
                }

                String[] resultOut = {"[{\"id\":23}]"}; //start thread

                try {
                    String[] string = {commentmsg};
                    String post = "id="+object.get("id").toString()+"&comment="+string[0];
                    Ranable r1 = new Ranable(post);
                    Thread t = new Thread(r1);
                    t.start();
                    t.join();
                } catch (JSONException | InterruptedException e) {
                    e.printStackTrace();
                }


            }
        });
        ArrayList<CommentMessage> data = new ArrayList<>();

        comment_view = getView().findViewById(R.id.food_comment_view);
        commentAdapter = new CommentAdapter(commentdata);
        commentLayout = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        comment_view.setAdapter(commentAdapter);
        comment_view.setLayoutManager(commentLayout);


    }
}