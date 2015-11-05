package com.chsj.qingyue.fragments.music;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * ProjectName: com.chsj.com
 * Created By:chsj
 * Date:2015/11/4
 */
public class SongAdapter extends FragmentPagerAdapter {

    private List<Fragment> datas;


    public SongAdapter(FragmentManager fm, List<Fragment> datas) {
        super(fm);
        this.datas = datas;
    }

    @Override
    public int getCount() {

        int ret = 0;
        if (datas != null) {
            ret = datas.size();
        }
        return ret;
    }

    @Override
    public Fragment getItem(int position) {
        return datas.get(position);
    }


}
