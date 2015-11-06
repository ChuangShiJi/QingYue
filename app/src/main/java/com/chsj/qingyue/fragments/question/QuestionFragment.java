package com.chsj.qingyue.fragments.question;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.chsj.qingyue.Constants;
import com.chsj.qingyue.R;
import com.chsj.qingyue.ZoomOutPageTransformer;

import org.xml.sax.helpers.LocatorImpl;

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
            int curr = i+1;
            fragments.add(QuestionFragmentItem.getInstance(String.format(Constants.QUESTION_URL, curr + "")));
        }
    }


    private int count;
    private boolean isRefresh;
    /**
     * 当页面在滑动的时候会调用此方法，在滑动被停止之前，此方法回一直得到调用。其中三个参数的含义分别为：
         arg0 :当前页面，及你点击滑动的页面
         arg1:当前页面偏移的百分比
         arg2:当前页面偏移的像素位置
     * @param position
     * @param positionOffset
     * @param positionOffsetPixels
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        //检测  viewpager 的滑动事件：   事件右滑刷新，左滑加载。。
        if (positionOffsetPixels == 0){ //偏移像素 为零   说明当不能偏移   为第一页 或者最后一页，或者停止状态
            count++;
        }
        if(count > 5){  //当前触摸屏莫的事件长短   超过5次调用该方法  弹出对话框  排除手指点击 但未偏移的事件

            if (position == 0){
                //排除  第一页中向  左滑的事件  不弹出
                if (positionOffset != 0){  //偏移的百分比 不为零
                    isRefresh = false; //置为false  不刷新   排除
                }
                //弹出：
                if (positionOffset == 0 && isRefresh) {
                    Toast.makeText(getActivity(), getString(R.string.now_is_lastest_content), Toast.LENGTH_SHORT).show();
                    isRefresh = false;
                }
            }

            if (position ==9){
                if (positionOffset != 0){
                    isRefresh = false;
                }
                if (positionOffset ==0 && isRefresh){
                    Toast.makeText(getActivity(), getString(R.string.now_is_not_content), Toast.LENGTH_SHORT).show();
                    isRefresh = false;
                }
            }
        }


    }

    @Override
    public void onPageSelected(int position) {

    }

    /**
     * //正在滚动时回调，回调2-3次，手指没抛则回调2次。scrollState = 2的这次不回调
     //回调顺序如下
     //第1次：scrollState = SCROLL_STATE_TOUCH_SCROLL(1) 正在滚动
     //第2次：scrollState = SCROLL_STATE_FLING(2) 手指做了抛的动作（手指离开屏幕前，用力滑了一下）
     //第3次：scrollState = SCROLL_STATE_IDLE(0) 停止滚动
     * @param state
     */
    @Override
    public void onPageScrollStateChanged(int state) {

        if (state  ==ViewPager.SCROLL_STATE_IDLE ){//停止滑动
            isRefresh = true;
            count = 0;
        }
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
