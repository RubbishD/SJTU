package com.example.sjtu;

import android.net.sip.SipSession;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class RestaurantScrollingFragment extends Fragment {

    private RecyclerView restaurantView;
    private RestaurantAdapter restaurantAdapter;
    private RecyclerView.LayoutManager restaurantLayout;
    private static final String ARG_PREFIX= "param1";
    public int building;
    public String restaurantName;
    public ArrayList<RestaurantView> viewData;

    String prefix;

    public  RestaurantScrollingFragment(){

    }

    public static RestaurantScrollingFragment newInstance(String arg_prefix) {
        RestaurantScrollingFragment fragment = new RestaurantScrollingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PREFIX,arg_prefix);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            prefix = getArguments().getString(ARG_PREFIX);
        }
        else{
            prefix = "default";
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_restraurant_scrolling, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        restaurantView = (RecyclerView) getView().findViewById(R.id.restaurant_view);
        viewData = ((MainActivity)getActivity()).getCache().data.get(1).getRestaurantsView();


        restaurantAdapter=new RestaurantAdapter(viewData);


        restaurantLayout=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);

        restaurantView.setAdapter(restaurantAdapter);
        restaurantView.setLayoutManager(restaurantLayout);
    }
    public void setOnItemClickListener(RestaurantAdapter.OnItemClickListener onItemClickListener){
        restaurantAdapter.setOnItemClickListener(onItemClickListener);
    }


    public void updateData(int i){
        int k = viewData.size();
        viewData.clear();
        viewData.addAll(((MainActivity)getActivity()).getCache().data.get(i).getRestaurantsView());


        if (viewData.size()!=0){
            restaurantAdapter.empty=false;
            restaurantAdapter.notifyItemRangeRemoved(0,k);
            restaurantAdapter.notifyDataSetChanged();
            restaurantAdapter.notifyItemRangeInserted(0,viewData.size());
        }
        else {
            restaurantAdapter.notifyItemRangeRemoved(0,k);
            restaurantAdapter.notifyDataSetChanged();
            restaurantAdapter.empty=true;
            restaurantAdapter.notifyItemInserted(0);
        }
    }
}
class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.ViewHolder> {

    private ArrayList<RestaurantView> nameDataSet;
    private OnItemClickListener onItemClickListener;

    boolean empty;
    public static class ViewHolder extends RecyclerView.ViewHolder {

        private  TextView name;
        private  Button btn;
        private WebView img;
        private TextView emptyText;
        public ViewHolder(View view,boolean empty) {
            super(view);
            // Define click listener for the ViewHolder's View
            name = view.findViewById(R.id.restaurantName);
            btn = view.findViewById(R.id.more_btn);
            img = view.findViewById(R.id.imageA);
            WebSettings webSettings = img.getSettings();
            webSettings.setLoadWithOverviewMode(true);
            webSettings.setUseWideViewPort(true);// viewport
            webSettings.setBuiltInZoomControls(true);
            webSettings.setDisplayZoomControls(false);
            webSettings.setSupportZoom(true);

            emptyText = view.findViewById(R.id.emptyRestaurant);


        }


        public TextView getTextView() {
            return name;
        }
        public void hide(){
            emptyText.setVisibility(View.VISIBLE);
            name.setVisibility(View.INVISIBLE);
            btn.setVisibility(View.INVISIBLE);
            img.setVisibility(View.INVISIBLE);
        }
        public void show(){
            name.setVisibility(View.VISIBLE);
            btn.setVisibility(View.VISIBLE);
            img.setVisibility(View.VISIBLE);
            emptyText.setVisibility(View.INVISIBLE);

        }
    }

    public RestaurantAdapter(ArrayList<RestaurantView> dataSet) {
        nameDataSet = dataSet;
    }




    // Create new views (invoked by the layout manager)
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.restaurant_viewholder, viewGroup, false);

        return new ViewHolder(view,empty);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        if (!empty) {
            viewHolder.show();
            viewHolder.getTextView().setText(nameDataSet.get(position).restaurantName + "-" + nameDataSet.get(position).merChantName);
            viewHolder.img.loadUrl(nameDataSet.get(position).display.get(0));
            viewHolder.btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(position);
                }
            });
        }
        else{

            viewHolder.hide();

        }

    }


    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return empty?1:nameDataSet.size();
    }
    public interface OnItemClickListener{
        void onItemClick(int i);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

}
