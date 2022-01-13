//package com.example.sjtu;
//
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.recyclerview.widget.RecyclerView;
//
//import org.jetbrains.annotations.NotNull;
//import org.json.JSONArray;
//
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//
//public abstract class commentAdapter extends BaseAdapter {
//
//    private LayoutInflater inflater;
//    private ArrayList<CommentMessage> data;
//
//    public commentAdapter(LayoutInflater inflater, ArrayList<CommentMessage> data) {
//        this.inflater = inflater;
//        this.data = data;
//    }
//
//
//    @Override
//    public int getCount() {
//        return data.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return position;
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        CommentMessage chatMessage=data.get(position);
//        View view = null;
//        ViewHolder viewHolder;
//        if(convertView==null){
//            view=inflater.inflate(R.layout.food_comment,null);
//            viewHolder=new ViewHolder();
//            viewHolder.comment=view.findViewById(R.id.comment);
//            viewHolder.time=view.findViewById(R.id.time);
//            view.setTag(viewHolder);
//        }else {
//            view=convertView;
//            viewHolder= (ViewHolder) view.getTag();
//        }
//
//        viewHolder.comment.setText(data.get(position).getComment());
//        SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd HH:mm");
//        String time=format.format(new Date(chatMessage.getTime()));
//        viewHolder.time.setText(time);
//        return view;
//    }
//
//    class ViewHolder{
//        TextView comment;
//        TextView time;
//    }
//}

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

import android.view.LayoutInflater;
import android.view.View;
        import android.view.ViewGroup;
import android.widget.TextView;

        import androidx.annotation.NonNull;
        import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.commentViewHolder> {
    private ArrayList<CommentMessage> commentmsg;

    public static class commentViewHolder extends RecyclerView.ViewHolder{
        private TextView comment;
        private TextView comment_time;

        public commentViewHolder(@NonNull View itemView) {
            super(itemView);
            comment = itemView.findViewById(R.id.comment);
            comment_time = itemView.findViewById(R.id.comment_time);
        }
    }

    public CommentAdapter(ArrayList<CommentMessage> commentMessages){
        commentmsg = commentMessages;
    }
    @NonNull
    @Override
    public commentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.food_comment,parent,false);
        return new commentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull commentViewHolder holder, int position) {
        holder.comment.setText(commentmsg.get(position).getComment());
        SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String time=format.format(new Date(CommentMessage.getTime()));
        holder.comment_time.setText(time);
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}


