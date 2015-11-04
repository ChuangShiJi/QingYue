package com.chsj.qingyue.fragments.object;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.chsj.qingyue.Constants;
import com.chsj.qingyue.R;
import com.chsj.qingyue.fragments.homepage.AsyTask;
import com.chsj.qingyue.fragments.homepage.ParseTool;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class SongDetailsFragment extends Fragment {


    //歌曲图片
    private ImageView imgThumb;
    //歌词
    private TextView txtLrc;
    //歌名
    private TextView txtTitle;
    //歌手
    private TextView txtArtist;
    //播放按钮
    private ImageView imgPlay;
    //播放进度
    private SeekBar skBar;

    //歌曲实体
    private Song song;
    //歌曲id，用于查找歌曲详情
    private String songId;

    //歌曲具体详情
    private SongDetails songDetails;

    public SongDetailsFragment() {
        // Required empty public constructor


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        Bundle bundle = getArguments();
        song = (Song) bundle.getSerializable("Song");

        songId = song.getSongId();


        View view = inflater.inflate(R.layout.fragment_song_details, container, false);


        initView(view);
        initData();
        return view;
    }


    /**
     * 初始化显示数据
     */
    private void initData() {
        txtArtist.setText(song.getSongArtist());
        txtTitle.setText(song.getSongTitle());

        new AsyTask(new AsyTask.CallBack() {
            @Override
            public void setJsonStr(String str) {

                songDetails = ParseTool.getSongDetail(str);
                Picasso.with(getActivity().getApplicationContext()).load(songDetails.getSongPicRadio()).into(imgThumb);

            }
        }).execute(String.format(Constants.SONG_DETAILS_URL, songId));

    }

    /**
     * 初始化控件
     *
     * @param view
     */
    private void initView(View view) {
        imgThumb = (ImageView) view.findViewById(R.id.fragment_song_details_song_thumb);
        imgPlay = (ImageView) view.findViewById(R.id.fragment_song_detail_song_play);
        txtTitle = (TextView) view.findViewById(R.id.fragment_song_detail_song_title);
        txtArtist = (TextView) view.findViewById(R.id.fragment_song_detail_song_artist);
        txtLrc = (TextView) view.findViewById(R.id.fragment_song_detail_song_lrc);
        skBar = (SeekBar) view.findViewById(R.id.fragment_song_detail_song_seekbar);
    }


}
