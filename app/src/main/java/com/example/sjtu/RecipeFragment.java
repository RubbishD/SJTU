
package com.example.sjtu;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

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
    private Foodmsg foodmsg = new Foodmsg();
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
    private CommentMessage commentMessage;
    private CommentAdapter commentAdapter;
    private RecyclerView.LayoutManager commentLayout;
    private ArrayList<CommentMessage> commentdata;


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
            foodmsg = (Foodmsg) getArguments().getSerializable("INDEX");
            String hh = getArguments().getString("String");
            System.out.println(hh+"test0000000000000000000");

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
        tv_veg = getActivity().findViewById(R.id.single_vegetat);
        et_comment = getActivity().findViewById(R.id.comment_editText);
        btn_commit = getActivity().findViewById(R.id.comment_submit_btn);
        comment_view = getView().findViewById(R.id.food_comment_view);

        //传入参数

        WebSettings webSettings = img.getSettings();
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);// viewport
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);
        webSettings.setSupportZoom(true);
        img.loadUrl(foodmsg.getImg_url());
        tv_name.setText(foodmsg.getName());
        tv_location.setText(foodmsg.getBuilding()+ foodmsg.getFloor()+ foodmsg.getRestaurant());
        tv_merchant.setText(foodmsg.getMerchant());
        tv_price.setText(foodmsg.getPrice());
        if(foodmsg.getMorning_time().equals("1")){
            tv_morning.setText("早餐供应");}
        else {
            tv_morning.setText("无早餐供应");
        }
        if(foodmsg.getNoon_time().equals("1")){
            tv_noon.setText("午餐供应");}
        else {
            tv_noon.setText("无午餐供应");
        }
        if (foodmsg.getNight_time().equals("1")){
            tv_night.setText("晚餐供应");}
        else {
            tv_night.setText("无晚餐供应");
        }
        tv_raw.setText(foodmsg.getRaw_material());
        tv_calorie.setText(foodmsg.getCalorie());
        tv_spicy.setText(foodmsg.getSpicy());
        tv_veg.setText(foodmsg.getVegetat());
        tv_staple.setText(foodmsg.getStaple());

        btn_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String comment = et_comment.getText().toString();
                commentMessage = new CommentMessage(comment,System.currentTimeMillis());
                commentdata.add(commentMessage);
                commentAdapter.notifyDataSetChanged();
                et_comment.setText("");//清空内容

            }
        });
        commentAdapter = new CommentAdapter(commentdata);
        comment_view.setAdapter(commentAdapter);
        comment_view.setLayoutManager(commentLayout);

    }
}