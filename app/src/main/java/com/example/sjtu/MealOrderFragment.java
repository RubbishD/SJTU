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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MealOrderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MealOrderFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public Cache c ;

    private RecyclerView recyclerView;
    private MealOrderAdapter adapter;
    private RecyclerView.LayoutManager layout;
    private static final String ARG_PREFIX= "param1";
    private ArrayList<Food> data=new ArrayList<>();

    public String restaurantName;
    public String merchantName;
    public int building;

    public MealOrderFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MealOrderFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MealOrderFragment newInstance(String param1, String param2) {
        MealOrderFragment fragment = new MealOrderFragment();
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
            ArrayList<String> tmp= getArguments().getStringArrayList("INDEX");
            building=Integer.parseInt(tmp.get(0));
            restaurantName = tmp.get(1);
            merchantName = tmp.get(2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        c = ((MainActivity)getActivity()).cache;
        return inflater.inflate(R.layout.fragment_meal_order, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init();
    }

    private void init(){
        ((TextView)getView().findViewById(R.id.restaurant_name)).setText(restaurantName);

        recyclerView = (RecyclerView)getView().findViewById(R.id.meal_recyclerView);
        initData();
        adapter = new MealOrderAdapter(data);
        layout = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);

        recyclerView.setLayoutManager(layout);
        recyclerView.setAdapter(adapter);
    }
    public void initData() {
        Restaurant r = ((MainActivity) getActivity()).getCache().data.get(building).getRestaurants(restaurantName);
        if (r.f) {
            Merchant m = r.getFoods(merchantName);
            data = m.foods;
        }
    }







class MealOrderAdapter extends RecyclerView.Adapter<MealOrderAdapter.ViewHolder> {
    public ArrayList<Food> nameDataSet;
    public class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView mealName;
        public TextView mealBrief;
        public TextView orderNum;
        WebView img;
        private ImageView addBtn;
        private ImageView delBtn;
        TextView price;
        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            mealName = view.findViewById(R.id.meal_name);
            mealBrief = view.findViewById(R.id.meal_description);
            orderNum = view.findViewById(R.id.order_num);
            addBtn = view.findViewById(R.id.addBtn);
            delBtn = view.findViewById(R.id.delBtn);
            price = view.findViewById(R.id.price);
            img = view.findViewById(R.id.imageB);
            WebSettings webSettings = img.getSettings();
            webSettings.setLoadWithOverviewMode(true);
            webSettings.setUseWideViewPort(true);// viewport
            webSettings.setBuiltInZoomControls(true);
            webSettings.setDisplayZoomControls(false);
            webSettings.setSupportZoom(true);

        }




    }

    public MealOrderAdapter(ArrayList<Food> dataSet) {
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
        viewHolder.mealName.setText(nameDataSet.get(position).name);
        viewHolder.img.loadUrl(nameDataSet.get(position).url);
        viewHolder.orderNum.setText(""+nameDataSet.get(position).orderNum);
        viewHolder.price.setText(""+nameDataSet.get(position).price);

        viewHolder.addBtn.setOnClickListener(v -> {
            if(nameDataSet.get(position).orderNum==0){
                c.ordered.add(data.get(position));
                    }
            nameDataSet.get(position).orderNum++;
            viewHolder.orderNum.setText(""+adapter.nameDataSet.get(position).orderNum);

        }
        );
        viewHolder.delBtn.setOnClickListener(v -> {

            if (nameDataSet.get(position).orderNum>0){
                nameDataSet.get(position).orderNum--;

                viewHolder.orderNum.setText( ""+adapter.nameDataSet.get(position).orderNum);
                if(nameDataSet.get(position).orderNum==0){
                    c.removeOrdered(nameDataSet.get(position));
                }
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {

        return nameDataSet.size();
    }



}
}