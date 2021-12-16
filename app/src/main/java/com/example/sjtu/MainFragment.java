package com.example.sjtu;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.laocaixw.layout.SuspendButtonLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";






    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private NavController controller;
    private BottomNavigationView bottomNav;
    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance(String param1, String param2) {

        MainFragment fragment = new MainFragment();
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
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SuspendButtonLayout suspendButtonLayout = (SuspendButtonLayout) view.findViewById(R.id.suspend_button);
        bottomNav = getActivity().findViewById(R.id.bottom_navigation);
        controller = Navigation.findNavController(getActivity(),R.id.main_fragment_nav_host_fragment);
        NavigationUI.setupWithNavController(bottomNav,controller);

        suspendButtonLayout.setOnSuspendListener(new SuspendButtonLayout.OnSuspendListener() {
            @Override
            public void onButtonStatusChanged(int status) {
                // 监听按钮状态：展开、关闭、移动等



            }

            @Override
            public void onChildButtonClick(int index) {
                // 监听子按钮的点击事件
                switch (index) {
                    case  1 :
                    case  2 :
                    case  3 : showShop();break;
                    default:break;
                }
                }
            }
        );


//
//        suspendButtonLayout.hideSuspendButton(); // 隐藏按钮
//        suspendButtonLayout.showSuspendButton(); // 显示按钮
//
//        suspendButtonLayout.openSuspendButton(); // 展开按钮
//        suspendButtonLayout.closeSuspendButton(); // 关闭按钮
//
//        suspendButtonLayout.setMainCloseImageResource(R.mipmap.suspend_main_close); // 设置关闭时，主按钮的图片
//        suspendButtonLayout.setMainOpenImageResource(R.mipmap.suspend_main_open); // 设置展开时，主按钮的图片
//
//        suspendButtonLayout.setPosition(isRight, stayPosY); // 设置按钮位置。isRight：true在右边，false在左边；stayPosY：在'按钮停留区域'从上往下，值为从0到100。


    }
    public void showShop(){

    }


}