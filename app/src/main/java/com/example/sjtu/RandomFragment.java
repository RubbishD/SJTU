package com.example.sjtu;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RandomFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RandomFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RandomFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RandomFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RandomFragment newInstance(String param1, String param2) {
        RandomFragment fragment = new RandomFragment();
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
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_random, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button randombutton = getActivity().findViewById(R.id.random);
        TextView randomres = getActivity().findViewById(R.id.textViewRan);
        ImageView imageRes = getActivity().findViewById(R.id.imageView1);
        //for storing spinners' selection results
        Spinner price = getActivity().findViewById(R.id.spinner1);
        Spinner place = getActivity().findViewById(R.id.spinner2);
        Spinner stfood = getActivity().findViewById(R.id.spinner3);
        Spinner spice = getActivity().findViewById(R.id.spinner4);
        final String[] priceCon = {price.getSelectedItem().toString()};
        final String[] placeCon = {place.getSelectedItem().toString()};
        final String[] stfoodCon = {stfood.getSelectedItem().toString()};
        final String[] spiceCon = {spice.getSelectedItem().toString()};
        //set image view by random mechanism
        int[] imageLs = {R.drawable.azura, R.drawable.mc, R.drawable.bdffknight, R.drawable.chocobo,
                R.drawable.creeper, R.drawable.edea, R.drawable.cc};
        String[] imageNameLs = {"Azura", "Minecraft", "BDFF_knights", "Chocobo",
                "creeper", "Edea", "Castle Crahser"};


        randombutton.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String param = "username=root&message=123";

                class RanRunable implements Runnable {
                    String param;
                    String[] result;
                    public RanRunable(String param1, String[] param2){
                        param = param1;
                        result = param2;
                    }
                    @Override
                    public void run() {
                        HttpRequest request = new HttpRequest();
                        String url0 = "http://119.3.110.15:33"; // http://119.3.110.15:33
                        // param = "username=root&message=123"; // param string of get request
                        result[0] = request.get((url0+"/?"+param));
                        JSONArray arr = null;
                        try {
                            arr = new JSONArray(result[0]); // [{}]
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    public String getResult(){
                        return result[0];
                    }
                }

                String[] result0 = {"asdsdds"}; //start thread
                RanRunable r1 = new RanRunable(param, result0);
                Thread t = new Thread(r1);
                t.start();
                try {
                    t.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int num = getRandom();
                imageRes.setImageResource(imageLs[num-1]);
                randomres.setText("Viewname: " + imageNameLs[num-1] + "\nPrice: " + priceCon[0]
                        + "\tLocation: " + placeCon[0] + "\tStapleFood: " + stfoodCon[0] + "\tSpiceness: " + spiceCon[0]
                        + "\nResult: " + result0[0]);
            }
        });

        price.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 priceCon[0] = price.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        place.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                placeCon[0] = place.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        stfood.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                stfoodCon[0] = stfood.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spice.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spiceCon[0] = spice.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    public static int getRandom() {
        int[] array = new int[] {1, 2, 3, 4, 5, 6, 7};
        int rnd = new Random().nextInt(array.length);
        return array[rnd];
    }

}