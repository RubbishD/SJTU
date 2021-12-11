package com.example.sjtu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MealOrderAdapter extends RecyclerView.Adapter<MealOrderAdapter.ViewHolder> {

    private ArrayList<String> nameDataSet;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView restaurantName;
        private final TextView restaurantBrief;
        private final TextView orderNum;

        private ImageButton addBtn;
        private ImageButton delBtn;
        private int num=0;
        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            restaurantName = view.findViewById(R.id.meal_name);
            restaurantBrief = view.findViewById(R.id.meal_description);
            orderNum = view.findViewById(R.id.order_num);
            addBtn = view.findViewById(R.id.addBtn);
            delBtn = view.findViewById(R.id.delBtn);

            addBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    num++;
                    orderNum.setText(""+num);
                }
            });

            delBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (num>0){
                        num--;
                        orderNum.setText(""+num);
                    }
                }
            });
        }



        public TextView getTextView() {
            return restaurantName;
        }
    }

    public MealOrderAdapter(ArrayList<String> dataSet) {
        nameDataSet = dataSet;
    }

    // Create new views (invoked by the layout manager)
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.meal_viewholder, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.getTextView().setText(nameDataSet.get(position));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return nameDataSet.size();
    }


}
