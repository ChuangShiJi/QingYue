package com.chsj.qingyue.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * ProjectName : com.chsj.qingyue.adapters
 * Created by : ChSJ.Team
 * Email : zhaoq_hero163.com
 * On 2015/11/3 // 22:23
 */
public class QuestionFragmentItemAdapter extends FragmentPagerAdapter{

    private List<Fragment> fragments;

    public QuestionFragmentItemAdapter(FragmentManager childFragmentManager, List<Fragment> fragments) {
        super(childFragmentManager);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }
}
