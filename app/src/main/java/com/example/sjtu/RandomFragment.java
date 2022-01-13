package com.example.sjtu;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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
        randomres.setMovementMethod(ScrollingMovementMethod.getInstance());//scroll
        //ImageView imageRes = getActivity().findViewById(R.id.imageView1);
        WebView webRes1 = getActivity().findViewById(R.id.webView1);
        WebView webRes2 = getActivity().findViewById(R.id.webView2);
        WebView webRes3 = getActivity().findViewById(R.id.webView3);
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

        WebSettings webSettings1 = webRes1.getSettings();
        webSettings1.setLoadWithOverviewMode(true);
        webSettings1.setUseWideViewPort(true);// viewport
        webSettings1.setBuiltInZoomControls(true);
        webSettings1.setDisplayZoomControls(false);
        webSettings1.setSupportZoom(true);
        WebSettings webSettings2 = webRes2.getSettings();
        webSettings2.setLoadWithOverviewMode(true);
        webSettings2.setUseWideViewPort(true);// viewport
        webSettings2.setBuiltInZoomControls(true);
        webSettings2.setDisplayZoomControls(false);
        webSettings2.setSupportZoom(true);
        WebSettings webSettings3 = webRes3.getSettings();
        webSettings3.setLoadWithOverviewMode(true);
        webSettings3.setUseWideViewPort(true);// viewport
        webSettings3.setBuiltInZoomControls(true);
        webSettings3.setDisplayZoomControls(false);
        webSettings3.setSupportZoom(true);

        String defaultUrl = "http://bpic.588ku.com/element_pic/19/04/09/87e4892d62ea31543cdd8360d5d5bb33.jpg";
        webRes1.loadUrl(defaultUrl);
        webRes2.loadUrl(defaultUrl);
        webRes3.loadUrl(defaultUrl);
        String[] resultWeb = {"ggg"}; //start thread

        webRes1.setOnTouchListener(new View.OnTouchListener(){
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction()==MotionEvent.ACTION_MOVE){
                    return false;
                }
                if (event.getAction()==MotionEvent.ACTION_UP){
                    try {
                        JSONArray arrt = new JSONArray(resultWeb[0]);
                        JSONObject obj1 = arrt.getJSONObject(0);
                        Bundle args = new Bundle();
                        args.putString("food", obj1.toString());
                        ((MainActivity)getActivity()).controller.navigate(R.id.action_mainFragment_to_expanding_item,args);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                return false;
            }
        });
        webRes2.setOnTouchListener(new View.OnTouchListener(){
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction()==MotionEvent.ACTION_MOVE){
                    return false;
                }
                if (event.getAction()==MotionEvent.ACTION_UP){
                    try {
                        JSONArray arrt = new JSONArray(resultWeb[0]);
                        JSONObject obj2 = arrt.getJSONObject(1);
                        Bundle args = new Bundle();
                        args.putString("food", obj2.toString());
                        ((MainActivity)getActivity()).controller.navigate(R.id.action_mainFragment_to_expanding_item,args);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                return false;
            }
        });
        webRes3.setOnTouchListener(new View.OnTouchListener(){
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction()==MotionEvent.ACTION_MOVE){
                    return false;
                }
                if (event.getAction()==MotionEvent.ACTION_UP){
                    try {
                        JSONArray arrt = new JSONArray(resultWeb[0]);
                        JSONObject obj3 = arrt.getJSONObject(2);
                        Bundle args = new Bundle();
                        args.putString("food", obj3.toString());
                        ((MainActivity)getActivity()).controller.navigate(R.id.action_mainFragment_to_expanding_item,args);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                return false;
            }
        });

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
                resultWeb[0] = resultOut[0];

                String imageUrl1 = "";
                String imageUrl2 = "";
                String imageUrl3 = "";
                //text colors: Turquoise1 #00F5FF; Chartreuse2 #76EE00; DarkOrange #FF8C00; Firebrick2 #EE2C2C
                //RoyalBlue2 #436EEE; ForestGreen #228B22;
                try {
                    JSONArray arr = new JSONArray(resultOut[0]);
                    if (arr.length() == 0) {
                        String text0 = "<font color='#EE2C2C'>抱歉,未找到相应结果</font>";
                        randomres.setText(Html.fromHtml(text0));
                        webRes1.loadUrl(defaultUrl);
                        webRes2.loadUrl(defaultUrl);
                        webRes3.loadUrl(defaultUrl);
                    }
                    else if (arr.length() == 1) {
                        JSONObject obj = arr.getJSONObject(0);
                        String text1 = "为您推荐的菜品: <font color='#436EEE'>"+ obj.get("food")+ "</font>\n价格: <font color='#EE2C2C'>"
                                + obj.get("price") + "</font>\n所在餐厅: " + obj.get("restaurant") + "\n窗口: " + obj.get("merchant");
                        randomres.setText(Html.fromHtml(text1));
                        imageUrl1 = obj.get("img_url").toString();
                        webRes1.loadUrl(imageUrl1);
                        webRes2.loadUrl(defaultUrl);
                        webRes3.loadUrl(defaultUrl);
                    }
                    else if (arr.length() == 2) {
                        JSONObject obj = arr.getJSONObject(0);
                        JSONObject obj2 = arr.getJSONObject(1);
                        float total = Float.parseFloat(obj.getString("price")) + Float.parseFloat(obj2.getString("price"));
                        String text2 = "为您推荐的组合: <font color='#436EEE'>"+ obj.get("food")+ "</font> + <font color='#228B22'>"
                                + obj2.get("food") + "</font>\n价格: <font color='#436EEE'>" + obj.get("price") +"</font> + <font color='#228B22'>"
                                + obj2.get("price") + "</font> = <font color='#EE2C2C'>" + total + "</font>\n所在餐厅: " + obj.get("restaurant")
                                + "\n窗口:" + obj.get("merchant") + ", " + obj2.get("merchant");
                        randomres.setText(Html.fromHtml(text2));
                        imageUrl1 = obj.get("img_url").toString();
                        webRes1.loadUrl(imageUrl1);
                        imageUrl2 = obj2.get("img_url").toString();
                        webRes2.loadUrl(imageUrl2);
                        webRes3.loadUrl(defaultUrl);
                    }
                    else {
                        JSONObject obj = arr.getJSONObject(0);
                        JSONObject obj2 = arr.getJSONObject(1);
                        JSONObject obj3 = arr.getJSONObject(2);
                        float total = Float.parseFloat(obj.getString("price")) + Float.parseFloat(obj2.getString("price")) +
                                Float.parseFloat(obj3.getString("price"));
                        String text3 = "为您推荐的组合: <font color='#436EEE'>"+ obj.get("food")+ "</font> + <font color='#228B22'>"+ obj2.get("food")
                                + "</font> + <font color='#FF8C00'>" + obj3.get("food") + "</font>\n价格: <font color='#436EEE'>" + obj.get("price")
                                +"</font> + <font color='#228B22'>" + obj2.get("price") + "</font> + <font color='#FF8C00'>" + obj3.get("price")
                                + "</font> = <font color='#EE2C2C'>" + total + "</font>\n所在餐厅: " + obj.get("restaurant") + "\n窗口:"
                                + obj.get("merchant") + ", " + obj2.get("merchant")+ ", " + obj3.get("merchant");
                        randomres.setText(Html.fromHtml(text3));
                        imageUrl1 = obj.get("img_url").toString();
                        webRes1.loadUrl(imageUrl1);
                        imageUrl2 = obj2.get("img_url").toString();
                        webRes2.loadUrl(imageUrl2);
                        imageUrl3 = obj3.get("img_url").toString();
                        webRes3.loadUrl(imageUrl3);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                //int num = getRandom();
                //imageRes.setImageResource(imageLs[num-1]);
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