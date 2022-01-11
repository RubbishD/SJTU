package com.example.sjtu;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
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
        //ImageView imageRes = getActivity().findViewById(R.id.imageView1);
        WebView webRes = getActivity().findViewById(R.id.webView1);
        //for storing spinners' selection results
        Spinner priceLow = getActivity().findViewById(R.id.spinner1);
        Spinner priceHigh = getActivity().findViewById(R.id.spinner5);
        Spinner place = getActivity().findViewById(R.id.spinner2);
        Spinner stfood = getActivity().findViewById(R.id.spinner3);
        Spinner spice = getActivity().findViewById(R.id.spinner4);
        final String[] priceLowCon = {priceLow.getSelectedItem().toString()};
        final String[] priceHighCon = {priceHigh.getSelectedItem().toString()};
        final String[] placeCon = {place.getSelectedItem().toString()};
        final String[] stfoodCon = {stfood.getSelectedItem().toString()};
        final String[] spiceCon = {spice.getSelectedItem().toString()};

        //set image view by random mechanism
        //int[] imageLs = {R.drawable.azura, R.drawable.mc, R.drawable.bdffknight, R.drawable.chocobo,
               // R.drawable.creeper, R.drawable.edea, R.drawable.cc};

        WebSettings webSettings = webRes.getSettings();
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);// viewport
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);
        webSettings.setSupportZoom(true);

        webRes.loadUrl("https://imgb15.photophoto.cn/20201209/zhengtaohaimianbaobaotouxianghuangsetouxiangtupian-40035084_3.jpg");

        randombutton.setOnClickListener(new  View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String param = "price_low="+priceLowCon[0]+"&price_high="+priceHighCon[0]
                        +"&location="+placeCon[0]+"&staple="+stfoodCon[0]+"&spicy="+spiceCon[0];

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
                        String url0 = "http://119.3.110.15:33/random"; // http://119.3.110.15:33
                        // param = "username=root&message=123"; // param string of get request
                        result[0] = request.get((url0+"?"+param));
                    }
                }

                String[] resultOut = {"asdsdds"}; //start thread
                RanRunable r1 = new RanRunable(param, resultOut);
                Thread t = new Thread(r1);
                t.start();
                try {
                    t.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                String imageUrl = "";
                try {
                    JSONArray arr = new JSONArray(resultOut[0]);
                    JSONObject obj = arr.getJSONObject(0);
                    randomres.setText("Name:"+ obj.get("food")+ "\nPrice: " + obj.get("price")
                            + "\nRestaurant: " + obj.get("restaurant") + "\nWindow:" + obj.get("merchant")
                            + "\n:" + obj.get("img_url")
                    );
                    imageUrl = obj.get("img_url").toString();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                //int num = getRandom();
                //imageRes.setImageResource(imageLs[num-1]);
                webRes.loadUrl(imageUrl);

            }
        });

        priceLow.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 priceLowCon[0] = priceLow.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        priceHigh.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                priceHighCon[0] = priceHigh.getSelectedItem().toString();
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