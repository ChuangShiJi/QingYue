package com.chsj.qingyue.fragments.music;


import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.chsj.qingyue.Constants;
import com.chsj.qingyue.R;
import com.chsj.qingyue.ZoomOutPageTransformer;
import com.chsj.qingyue.fragments.homepage.AsyTask;
import com.chsj.qingyue.fragments.homepage.ParseTool;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SongFragment extends Fragment {


    private ViewPager viewPager;

    private SongAdapter songAdapter;

    private List<Fragment> datas;

    private List<Song> songs;


    private ImageView imgPro;

    private AnimationDrawable anim;

    public SongFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_song, container, false);

        imgPro = (ImageView) view.findViewById(R.id.fragment_song_pro);
        viewPager = (ViewPager) view.findViewById(R.id.fragment_song_view_pager);
        datas = new ArrayList<Fragment>();
        songAdapter = new SongAdapter(getChildFragmentManager(), datas);

        viewPager.setPageTransformer(true, new ZoomOutPageTransformer());

        viewPager.setAdapter(songAdapter);
        viewPager.setOffscreenPageLimit(10);

        //获取歌曲列表
        initSongs();

        //启动加载动画
        anim = (AnimationDrawable) imgPro.getBackground();
        anim.start();

        return view;
    }

    private void initSongs() {
        new AsyTask(new AsyTask.CallBack() {
            @Override
            public void setJsonStr(String str) {

                try {
                    JSONObject jsonObject = new JSONObject(str);
                    songs = ParseTool.getSongs(jsonObject);

                    initFragment();

                    anim.stop();
                    imgPro.setVisibility(View.INVISIBLE);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }).execute(Constants.OLD_SONG_URL);
    }

    private void initFragment() {
        int len = songs.size();
        for (int i = 0; i < len; i++) {

            SongDetailsFragment songDetailsFragment = new SongDetailsFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable("Song", songs.get(i));
            songDetailsFragment.setArguments(bundle);
            bundle.putInt("tag", i);
            datas.add(songDetailsFragment);
        }

        songAdapter.notifyDataSetChanged();
        viewPager.setCurrentItem(Constants.CURRENT_FRAGMENT);
    }

}
