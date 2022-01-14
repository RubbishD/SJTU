//package com.example.sjtu;
//
//
//import android.content.Context;
//import android.content.Intent;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.ArrayList;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Map;
//
////public class MealRecommendAdapter extends RecyclerView.Adapter<MealRecommendAdapter.ViewHolder> {
////
////    private List<Map<String,Object>> mdata;
////    private Intent intent;
////    private Context mcontext;
////    private MealRecommendAdapter mealRecommendAdapter;
////
////    public MealRecommendAdapter(List<Map<String,Object>> data,Context context) {
////        this.mdata = data;
////        this.mcontext = context;
////
////    }
////
////
////    public class ViewHolder extends RecyclerView.ViewHolder{
////
////        TextView price;
////        TextView location;
////        TextView calorie;
////        TextView spicy;
////        TextView number;
////        TextView name;
////        ImageView addbtn;
////        ImageView reducebtn;
////        int num = 0;
////        public ViewHolder(View view){
////            super(view);
////
////            price = view.findViewById(R.id.meal_price);
////            location = view.findViewById(R.id.meal_location);
////            calorie = view.findViewById(R.id.meal_calorie);
////            spicy = view.findViewById(R.id.meal_spicy);
////            addbtn = view.findViewById(R.id.meal_shopping_food_add);
////            reducebtn = view.findViewById(R.id.meal_shopping_food_reduce);
////            number = view.findViewById(R.id.meal_shopping_food_number);
////            name = view.findViewById(R.id.meal_food_name);
////            addbtn.setOnClickListener(new View.OnClickListener() {
////                @Override
////                public void onClick(View v) {
////                    num++;
////                    number.setText(""+num);
////                }
////            });
////
////            reducebtn.setOnClickListener(new View.OnClickListener() {
////                @Override
////                public void onClick(View v) {
////                    if(num>0){
////                        num--;
////                        number.setText((""+num));
////                    }
////                }
////            });
////
////            name.setOnClickListener(new View.OnClickListener() {
////                @Override
////                public void onClick(View v) {
////
////                    Intent intent = new Intent(v.getContext(),RecipeActivity.class);
////                    mcontext.startActivity(intent);
////                }
////            });
////        }
////
////
////    }
////
////
////    @NonNull
////    @Override
////    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
////        View view = LayoutInflater.from(mcontext)
////                .inflate(R.layout.meal_list,viewGroup,false);
////        return new ViewHolder(view);
////    }
////
////    @Override
////    public void onBindViewHolder(ViewHolder viewHolder, int position) {
////        viewHolder.name.setText(mdata.get(position).get("菜名").toString());
////        viewHolder.location.setText(mdata.get(position).get("地址").toString());
////        viewHolder.calorie.setText(mdata.get(position).get("10").toString());
////        viewHolder.spicy.setText(mdata.get(position).get("辣度").toString());
////        viewHolder.price.setText(mdata.get(position).get("16").toString());
////    }
////
////    @Override
////    public int getItemCount() {
////        if(mdata!=null){
////            return mdata.size();}
////        return 0;
////    }
////
////}
//public class MealRecommendAdapter extends BaseAdapter {
//
//    private LinkedList<Meal> mData;
//    private Context mContext;
//    private LayoutInflater mlayoutInflater;
//    private ClickListener mListener;
//
//    public MealRecommendAdapter(ArrayList<Meal> meals) {
//    }
//
//    public interface ClickListener{
//        public void onClick(View v);
//    }
//
//    public MealRecommendAdapter(LinkedList<Meal> Data, Context Context) {
//        this.mData = Data;
//        this.mContext = Context;
//        mlayoutInflater = LayoutInflater.from(Context);
//
//    }
//
//    @Override
//    public int getCount() {
//        return mData.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return null;
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    static class ViewHolder{
//        public TextView price;
//        public TextView location;
//        public TextView calorie;
//        public TextView spicy;
//        public ImageView addbtn;
//        public ImageView reducebtn;
//        public TextView number;
//        public TextView name;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        ViewHolder holder = null;
//        if (convertView == null) {
//            convertView = mlayoutInflater.from(mContext).inflate(R.layout.meal_list, parent, false);
//            holder = new ViewHolder();
//            holder.price = convertView.findViewById(R.id.meal_price);
//            holder.location = convertView.findViewById(R.id.meal_location);
//            holder.calorie = convertView.findViewById(R.id.meal_calorie);
//            holder.spicy = convertView.findViewById(R.id.meal_spicy);
//            holder.addbtn = convertView.findViewById(R.id.meal_shopping_food_add);
//            holder.reducebtn = convertView.findViewById(R.id.meal_shopping_food_reduce);
//            holder.number = convertView.findViewById(R.id.meal_shopping_food_number);
//            holder.name = convertView.findViewById(R.id.meal_food_name);
//            convertView.setTag(holder);
//        } else {
//            holder = (ViewHolder) convertView.getTag();
//        }
//
//
////        holder.name.setOnClickListener(new View.OnClickListener() {
////                @Override
////                public void onClick(View v) {
////
////                    Intent intent = new Intent(v.getContext(),RecipeActivity.class);//点击菜名跳转到详细菜品界面
////                    mContext.startActivity(intent);
////                }
////            });
//
//        holder.name.setText(mData.get(position).getName());
//        holder.location.setText(mData.get(position).getLocation());
//        holder.spicy.setText(mData.get(position).getSpicy());
//        holder.calorie.setText(mData.get(position).getCalorie());
//        holder.price.setText(mData.get(position).getPrice());
//
//        return convertView;
//    }
////        TextView price = convertView.findViewById(R.id.meal_price);
////        TextView location = convertView.findViewById(R.id.meal_location);
////        TextView calorie = convertView.findViewById(R.id.meal_calorie);
////        TextView spicy = convertView.findViewById(R.id.meal_spicy);
////        ImageView addbtn = convertView.findViewById(R.id.meal_shopping_food_add);
////        ImageView reducebtn = convertView.findViewById(R.id.meal_shopping_food_reduce);
////        TextView number = convertView.findViewById(R.id.meal_shopping_food_number);
////        TextView name = convertView.findViewById(R.id.meal_food_name);
////
////        final int[] num = {0};
////        addbtn.setOnClickListener(new View.OnClickListener() {//添加
////            @Override
////            public void onClick(View v) {
////                num[0]++;
////                number.setText(""+ num[0]);
////            }
////        });
////
////        reducebtn.setOnClickListener(new View.OnClickListener() {//删除
////            @Override
////            public void onClick(View v) {
////                if(num[0] >0){
////                    num[0]--;
////                    number.setText((""+ num[0]));
////                }
////            }
////        });
////
////        name.setOnClickListener(new View.OnClickListener() {
////                @Override
////                public void onClick(View v) {
////
////                    Intent intent = new Intent(v.getContext(),RecipeActivity.class);//点击菜名跳转到详细菜品界面
////                    mContext.startActivity(intent);
////                }
////            });
////
////        name.setText(mData.get(position).getName());
////        location.setText(mData.get(position).getLocation());
////        spicy.setText(mData.get(position).getSpicy());
////        calorie.setText(mData.get(position).getCalorie());
////        price.setText(mData.get(position).getPrice());
////        return convertView;
////    }
//}

package com.example.sjtu;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;

import java.util.ArrayList;

public class MealRecommendAdapter extends RecyclerView.Adapter<MealRecommendAdapter.ViewHolder> {

    private final ArrayList<Meal> mealsDataSet;
    private OnItemClickListener onItemClickListener;
    public JSONArray array;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView price;
        private final TextView location;
        private final TextView calorie;
        private final TextView spicy;
//        private final ImageView addbtn;
//        private final ImageView reducebtn;
//        private final TextView number;
        private final TextView name;


        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            price = view.findViewById(R.id.meal_price);
            location = view.findViewById(R.id.meal_location);
            calorie = view.findViewById(R.id.meal_calorie);
            spicy = view.findViewById(R.id.meal_spicy);
//            addbtn = view.findViewById(R.id.meal_shopping_food_add);
//            reducebtn = view.findViewById(R.id.meal_shopping_food_reduce);
//            number = view.findViewById(R.id.meal_shopping_food_number);
            name = view.findViewById(R.id.meal_food_name);
        }



        public TextView getPrice() {
            return price;
        }

        public TextView getLocation() {
            return location;
        }

        public TextView getCalorie() {
            return calorie;
        }

        public TextView getSpicy() {
            return spicy;
        }


        public TextView getName() {
            return name;
        }

//        public TextView getNumber() {
//            return number;
//        }
    }

    public MealRecommendAdapter(ArrayList<Meal> dataSet, JSONArray arr) {
        mealsDataSet = dataSet;
        array = arr;
    }

    // Create new views (invoked by the layout manager)
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.meal_list, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.calorie.setText(mealsDataSet.get(position).getCalorie()+"");
        viewHolder.location.setText(mealsDataSet.get(position).getLocation());
        viewHolder.name.setText(mealsDataSet.get(position).getName());
        viewHolder.price.setText(mealsDataSet.get(position).getPrice()+"");
        viewHolder.spicy.setText(mealsDataSet.get(position).getSpicy());

        init(viewHolder,position);
    }

    private void init(ViewHolder viewHolder,int position){
//        viewHolder.addbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
        viewHolder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(position);
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mealsDataSet.size();
    }
    public interface OnItemClickListener{
        void onItemClick(int i);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

}
