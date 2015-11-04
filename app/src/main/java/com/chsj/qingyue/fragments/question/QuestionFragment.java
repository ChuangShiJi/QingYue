package com.chsj.qingyue.fragments.question;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.LocalBroadcastManager;
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

//    使用本地广播管理器：
    private LocalBroadcastManager localBroadcastManager;
    private QuestionFragmentItem frag;//用于获取数据

    private View view;

    //使用ViewPager：
    private ViewPager viewPager;

    private List<Fragment> fragments;

    private QuestionFragmentItemAdapter adapter;

    public QuestionFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        //获取本地广播管理器：
        localBroadcastManager = LocalBroadcastManager.getInstance(getActivity());

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

        if (fragments.get(position)!=null){

            frag = (QuestionFragmentItem) fragments.get(position);  //获取当前位置被选中的  页面数据：
            //该页面备选中后   发送广播数据：通过本地广播管理器  来发送广播：
            Intent intent = new Intent(Constants.GET_DATA_TO_SHARE);
            intent.putExtra(Constants.DATA_TO_EXTRA,frag.getQuestionEntity().getSWebLk());//将网络数据传入
            localBroadcastManager.sendBroadcast(intent);

        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
