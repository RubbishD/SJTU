package com.example.sjtu;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MealRecommendAdapter extends RecyclerView.Adapter<MealRecommendAdapter.ViewHolder> {

    private ArrayList<Meal> mdata;
    private Context mcontext;

    public MealRecommendAdapter(ArrayList<Meal> data,Context context) {
        this.mdata = data;
        this.mcontext = context;

    }

    public MealRecommendAdapter(ArrayList<Meal> mdata) {
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView price;
        private final TextView location;
        private final TextView calorie;
        private final TextView spicy;
        private final TextView number;
        private final TextView name;
        private final ImageView addbtn;
        private final ImageView reducebtn;
        private int num = 0;
        public ViewHolder(View view){
            super(view);

            price = view.findViewById(R.id.meal_price);
            location = view.findViewById(R.id.meal_location);
            calorie = view.findViewById(R.id.meal_calorie);
            spicy = view.findViewById(R.id.meal_spicy);
            addbtn = view.findViewById(R.id.meal_shopping_food_add);
            reducebtn = view.findViewById(R.id.meal_shopping_food_reduce);
            number = view.findViewById(R.id.meal_shopping_food_number);
            name = view.findViewById(R.id.meal_food_name);
            addbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    num++;
                    number.setText(""+num);
                }
            });

            reducebtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(num>0){
                        num--;
                        number.setText((""+num));
                    }
                }
            });

            name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mcontext,RecipeActivity.class);
                    mcontext.startActivity(intent);
                }
            });
        }

        public void setData(Meal food) {
        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.meal_list,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.setData(mdata.get(position));
    }

    @Override
    public int getItemCount() {
        if(mdata!=null){
            return mdata.size();}
        return 0;
    }

}
