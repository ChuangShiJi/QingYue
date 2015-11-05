package com.chsj.qingyue.fragments.question;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.chsj.qingyue.Constants;
import com.chsj.qingyue.R;
import com.chsj.qingyue.ZoomOutPageTransformer;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionFragment extends Fragment implements ViewPager.OnPageChangeListener, View.OnClickListener {



    private View view;

    //使用ViewPager：
    private ViewPager viewPager;

    private List<Fragment> fragments;

    private QuestionFragmentItemAdapter adapter;


    private RelativeLayout canShare;
    private ImageView noShare;

    public QuestionFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        fragments = new ArrayList<>();

        initFragment();

        adapter = new QuestionFragmentItemAdapter(getChildFragmentManager(),fragments);

        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_question, container, false);

        viewPager = (ViewPager) view.findViewById(R.id.position_fragment_viewpager);

        canShare = (RelativeLayout) view.findViewById(R.id.question_fragment_to_share);
        noShare = (ImageView) view.findViewById(R.id.question_no_can_share);

        viewPager.setAdapter(adapter);

        //设置   动画：
        viewPager.setPageTransformer(true,new ZoomOutPageTransformer());

        viewPager.addOnPageChangeListener(this);
        noShare.setOnClickListener(this);

        return view;
    }

    private void initFragment() {
        //初始化Fragment：
        for (int i=0; i <= 9;i++){
            fragments.add(QuestionFragmentItem.getInstance(String.format(Constants.QUESTION_URL, (i+1)+"")));
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (position == 0){
            Toast.makeText(getActivity(), getString(R.string.now_is_first_page),Toast.LENGTH_SHORT).show();
        }else if(position == 9){
            Toast.makeText(getActivity(), getString(R.string.now_is_first_page),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void onClick(View v) {
        //添加单击事件：当单击x的图标时    提示信息消失：
        canShare.setVisibility(View.GONE);
    }



}
