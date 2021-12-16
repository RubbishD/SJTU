package com.example.sjtu;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.work.Data;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

public abstract class MealRecommendAdapter extends BaseAdapter {
    private ArrayList<Food> mData;
    private Context mContext; //布局id
    private View.OnClickListener defaultRequestBtnClickListener;

    public MealRecommendAdapter() {
    }

    public MealRecommendAdapter(ArrayList mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mData != null ? mData.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.activity_recipe1, parent, false);
        }
        final RelativeLayout relativeLayout = convertView.findViewById(R.id.Relative_name);
        final TextView price_num = convertView.findViewById(R.id.price_num);
        final TextView canteen = convertView.findViewById(R.id.canteen_label);
        final TextView floor = convertView.findViewById(R.id.floor_label);
        final TextView food1 = convertView.findViewById((R.id.name1));
        final TextView food2 = convertView.findViewById(R.id.name2);
        final TextView taste = convertView.findViewById(R.id.taste);
        final TextView calorie = convertView.findViewById(R.id.calorie);
        final ImageView del_btn = convertView.findViewById(R.id.delBtn);
        final ImageView add_btn = convertView.findViewById(R.id.addBtn);
        final TextView order_num = convertView.findViewById(R.id.order_num);
        final int num=0;

        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,RecipeActivity1.class);
                Bundle recipe_msg = new Bundle();
                recipe_msg.putSerializable("recipe", mData);
                intent.putExtra("recipe",recipe_msg);
                mContext.startActivity(intent);
            }
        });
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num++;
                order_num.setText(""+num);
            }
        });
        return convertView;


    }

    static class ViewHolder(View view){

    }
}
