package com.chsj.qingyue.fragments.article;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chsj.qingyue.R;
import com.chsj.qingyue.ZoomOutPageTransformer;

import java.util.LinkedList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArticleFragment extends Fragment implements ViewPager.OnPageChangeListener {

    private ViewPager viewPager;
    private ArticleFragmentAdapter adapter;
    private List<ArticleFragmentItem> datas;
    private boolean isRefresh;
    private int count;

    public ArticleFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        datas = new LinkedList<ArticleFragmentItem>();
        for (int i = 0; i < 10; i++) {
            ArticleFragmentItem item = new ArticleFragmentItem();
            Bundle bundle = new Bundle();

            bundle.putInt("page", i + 1);
            item.setArguments(bundle);
            datas.add(item);
        }

        adapter = new ArticleFragmentAdapter(getChildFragmentManager(), datas);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.article_fragment, container, false);

        viewPager = (ViewPager) view.findViewById(R.id.article_fragment_viewpager);
        viewPager.setPageTransformer(true,new ZoomOutPageTransformer());
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(this);
        return view;
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//        检测ViewPager的滑动，事项右滑刷新，左化加载
        if (positionOffsetPixels == 0) {
            count++;
        }
        if (count > 5) {
            if (position == 0) {
                if (positionOffset != 0) {
                    isRefresh = false;
                }
                if (positionOffset == 0 && isRefresh) {
                    Toast.makeText(getActivity(), getString(R.string.now_is_lastest_content), Toast.LENGTH_SHORT).show();
                    isRefresh = false;

                }
            }
            if (position == 9) {
                if (positionOffset != 0) {
                    isRefresh = false;
                }
                if (positionOffset == 0 && isRefresh) {
                    Toast.makeText(getActivity(), getString(R.string.now_is_not_content), Toast.LENGTH_SHORT).show();
                    isRefresh = false;
                }
            }
        }


    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (state == ViewPager.SCROLL_STATE_IDLE) {
            isRefresh = true;
            count = 0;
        }
    }

}
