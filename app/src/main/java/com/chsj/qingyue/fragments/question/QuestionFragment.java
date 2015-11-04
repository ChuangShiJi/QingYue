package com.chsj.qingyue.fragments.question;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chsj.qingyue.Constants;
import com.chsj.qingyue.R;
import com.chsj.qingyue.adapters.QuestionFragmentItemAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionFragment extends Fragment implements ViewPager.OnPageChangeListener {


    private View view;

    //使用ViewPager：
    private ViewPager viewPager;

    private List<Fragment> fragments;

    private QuestionFragmentItemAdapter adapter;

    public QuestionFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_question, container, false);

        viewPager = (ViewPager) view.findViewById(R.id.position_fragment_viewpager);

        fragments = new ArrayList<>();

        initFragment();

        adapter = new QuestionFragmentItemAdapter(getChildFragmentManager(),fragments);

        viewPager.setAdapter(adapter);

        viewPager.setOnPageChangeListener(this);

        return view;
    }

    private void initFragment() {
        //初始化Fragment：
        for (int i=1;i<=10;i++){
            fragments.add(QuestionFragmentItem.getInstance(String.format(Constants.POSITION_URL, i+"")));
        }
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
