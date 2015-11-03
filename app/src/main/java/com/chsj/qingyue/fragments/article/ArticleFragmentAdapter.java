package com.chsj.qingyue.fragments.article;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;

import java.util.List;

/**
 * ProjectName : com.chsj.qingyue
 * Created by : whl
 * 2015/11/3
 */
public class ArticleFragmentAdapter extends FragmentPagerAdapter {
    private List<ArticleFragmentItem> datas;

    public ArticleFragmentAdapter(FragmentManager fm, List<ArticleFragmentItem> datas) {

        super(fm);
        this.datas = datas;
    }


    @Override
    public Fragment getItem(int position) {
        return datas.get(position);
    }

    @Override
    public int getCount() {
        return datas.size();
    }




}
