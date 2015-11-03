package com.chsj.qingyue.fragments.homepage;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chsj.qingyue.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomePageFragment extends Fragment {


    public HomePageFragment() {
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_home_page, container, false);


        return view;
    }


}
