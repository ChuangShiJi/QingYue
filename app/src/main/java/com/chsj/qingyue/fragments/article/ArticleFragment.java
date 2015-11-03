package com.chsj.qingyue.fragments.article;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chsj.qingyue.R;

import java.util.LinkedList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArticleFragment extends Fragment implements ViewPager.OnPageChangeListener {

    private ViewPager viewPager;
    private ArticleFragmentAdapter adapter;
    private List<ArticleFragmentItem> datas;

    public ArticleFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        datas = new LinkedList<ArticleFragmentItem>();
        for (int i = 0; i < 10; i++) {
            ArticleFragmentItem item=new ArticleFragmentItem();
            Bundle bundle=new Bundle();

            bundle.putInt("page",i+1);
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
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(this);
        return view;
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
//        Bundle bundle=new Bundle();
//        bundle.putInt("page",position+1);
//        datas.get(position).get
//        datas.get(position).setArguments(bundle);

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
