package com.example.sjtu;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ClassificationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ClassificationFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PREFIX= "param1";
    RestaurantScrollingFragment restaurantScrollingFragment;
    private String prefix;

    public ClassificationFragment() {
        // Required empty public constructor
    }


    public static ClassificationFragment newInstance(String arg_prefix) {
        ClassificationFragment fragment = new ClassificationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PREFIX, arg_prefix);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            prefix = getArguments().getString(ARG_PREFIX);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.fragment_classification, container, false);
        init(rootView);
        return  rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    private void init(View view){
        restaurantScrollingFragment = (RestaurantScrollingFragment) getChildFragmentManager().findFragmentById(R.id.restaurantScrollingFragment);
        restaurantScrollingFragment.setOnItemClickListener(new RestaurantAdapter.OnItemClickListener() {
            @Override
            public void onItemClick() {
               Navigation.findNavController(getParentFragment().getView()).navigate(R.id.action_classificationFragment_to_mealOrderFragment);
            }
        });

        NavigationView navigationView= (NavigationView)view.findViewById(R.id.planBuildingView);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Toast.makeText(getContext(),restaurantScrollingFragment.prefix,Toast.LENGTH_SHORT).show();
                switch (item.getItemId()) {
                    case R.id._1stBuilding:
                        restaurantScrollingFragment.setArgPrefix("_1stBuilding");
                        break;

                    case R.id._2ndBuilding:
                        restaurantScrollingFragment.setArgPrefix("_2ndBuilding");
                        break;
                    case R.id._3rdBuilding:
                        restaurantScrollingFragment.setArgPrefix("_13rdBuilding");
                        break;
                    case R.id._4thBuilding:
                        restaurantScrollingFragment.setArgPrefix("_4thBuilding");
                        break;
                    case R.id._5thBuilding:
                        restaurantScrollingFragment.setArgPrefix("_5thBuilding");
                        break;
                    case R.id._6thBuilding:
                        restaurantScrollingFragment.setArgPrefix("_6thBuilding");
                        break;
                    case R.id._7thBuilding:
                        restaurantScrollingFragment.setArgPrefix("_7thBuilding");
                        break;
                    case R.id._yulanBuilding:
                        restaurantScrollingFragment.setArgPrefix("_yuLanBuilding");
                }

                return true;
            }
        });
    }

}