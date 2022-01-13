package com.example.sjtu;


import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.ArrayList;

public class ShopFragment extends Fragment {

    Cache c ;

    private RecyclerView recyclerView;
    private ShopAdapter adapter;
    private RecyclerView.LayoutManager layout;
    private ArrayList<Food> food=new ArrayList<Food>();
    private TextView view_total_price;

    public ShopFragment() {
        // Required empty public constructor

    }

    public void initCache(Cache cache){
        c = cache;
    }

    public static ShopFragment newInstance(FoodBundle cache) {
        ShopFragment fragment = new ShopFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onResume() {
        super.onResume();
        if (getArguments() != null) {

        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        c = ((MainActivity)getActivity()).cache;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.shopping_cart, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();

    }

    private void showNormalDialog(double calory){
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(getActivity());

        normalDialog.setTitle("卡路里结果");
        normalDialog.setMessage("燃烧你的卡路里!!!\n"+"卡路里 : "+calory);
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                    }
                });
        normalDialog.setNegativeButton("关闭",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                    }
                });
        // 显示
        normalDialog.show();
    }

    private void bindClick(){
        getView().findViewById(R.id.calory).setOnClickListener(v->{
            double calory = c.calory();
            showNormalDialog(calory);

        });

        getView().findViewById(R.id.clear).setOnClickListener(
                v -> {adapter.setEmpty();
                    Toast.makeText(getContext(),"clear",Toast.LENGTH_SHORT).show();
                }
        );
    }



    public void init(){
        view_total_price = getView().findViewById(R.id.total_price);
        view_total_price.setText(""+c.cal_total());
        recyclerView = (RecyclerView)getView().findViewById(R.id.shopping_recyclerview);;
        adapter = new ShopAdapter(c.ordered);

        layout = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);

        recyclerView.setLayoutManager(layout);
        recyclerView.setAdapter(adapter);
        bindClick();
    }





class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ViewHolder> {

    private ArrayList<Food> foodDataSet;


    private int empty = -1;


    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView food_name;
        private final TextView food_price;
        WebView food_image;
        private final ImageView add_btn;
        private final ImageView del_btn;
        private TextView food_number;
        private Food food;


        public ViewHolder(View view) {
            super(view);

            food_name = view.findViewById(R.id.shopping_food_name);
            food_image = view.findViewById(R.id.shopping_food_image);
            food_price = view.findViewById(R.id.shopping_food_price);
            add_btn = view.findViewById(R.id.shopping_food_add);
            del_btn = view.findViewById(R.id.shopping_food_reduce);
            food_number = view.findViewById(R.id.shopping_food_number);
            WebSettings webSettings = food_image.getSettings();
            webSettings.setLoadWithOverviewMode(true);
            webSettings.setUseWideViewPort(true);// viewport
            webSettings.setBuiltInZoomControls(true);
            webSettings.setDisplayZoomControls(false);
            webSettings.setSupportZoom(true);
        }

    }


    public ShopAdapter(ArrayList<Food> dataSet) {
        if (dataSet == null) foodDataSet = new ArrayList<Food>();
        else {
            foodDataSet = dataSet;
        }
    }


    // Create new views (invoked by the layout manager)
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.shopping_message, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Food f = c.ordered.get(position);
        viewHolder.food_name.setText(f.name);
        viewHolder.food_price.setText("" + f.price);
        viewHolder.food_number.setText("" + f.orderNum);
        viewHolder.food_image.loadUrl(f.url);

        viewHolder.add_btn.setOnClickListener(v -> {
            foodDataSet.get(position).orderNum++;
            viewHolder.food_number.setText(""+f.orderNum);
            view_total_price.setText(""+c.cal_total());
        });
        viewHolder.del_btn.setOnClickListener(v->{
            foodDataSet.get(position).orderNum--;
            viewHolder.food_number.setText(""+f.orderNum);
            if(f.orderNum==0){
                c.removeOrdered(f);
                notifyItemRemoved(position);
                view_total_price.setText(""+c.cal_total());
            }
        });


    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return foodDataSet.size();
    }

    public void setEmpty() {

        notifyItemRangeChanged(0,foodDataSet.size());
        notifyDataSetChanged();
        c.setZero();
        c.ordered.clear();
    }
}
}