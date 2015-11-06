package com.chsj.qingyue.fragments.music;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
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
import com.chsj.qingyue.tools.ImageUtils;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SongDetailsFragment extends Fragment implements View.OnClickListener {


    private PrgReceiver prgReceiver;

    private LocalBroadcastManager lbMgr;

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

    //当前是第几个Fragment
    private int currentFragment;

    //当前正在播放的Fragment
    private int currentF;


    //歌曲播放地址
    private String url;

    //歌词地址
    private String lrcUrl;

    //歌词
    private List<String> lrcs;

    //是否正在播放
    private boolean isPlaying = false;
    private ImageView imgControlPlay;
    private boolean firstLoadLrc = true;


    public SongDetailsFragment() {
        // Required empty public constructor


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        Bundle bundle = getArguments();
        song = (Song) bundle.getSerializable("Song");
        currentFragment = bundle.getInt("tag");
        songId = song.getSongId();

        View view = inflater.inflate(R.layout.fragment_song_details, container, false);

        initView(view);
        initData();

        lbMgr = LocalBroadcastManager.getInstance(getActivity().getApplicationContext());

        prgReceiver = new PrgReceiver();
        lbMgr.registerReceiver(prgReceiver, new IntentFilter(Constants.ACTION_PROGRESS));

        event();
        return view;
    }

    private void event() {


        skBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO 拖动结束时的方法
                //获取当前拖动的位置
                int seekPosition = seekBar.getProgress();

                //向播放组件组件发送广播
                Intent intent = new Intent(Constants.ACTION_SEEKTO);
                intent.putExtra(Constants.EXTRA_PROGREES_CUR, seekPosition);


                lbMgr.sendBroadcast(intent);//发送广播（在Service组件内接收）
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                // TODO Auto-generated method stub

            }
        });
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        lbMgr.unregisterReceiver(prgReceiver);//取消注册本地广播接收器
    }

    /**
     * 初始化显示数据
     */
    private void initData() {

        //初始化歌词....
        lrcs = new ArrayList<String>();

        new AsyTask(new AsyTask.CallBack() {
            @Override
            public void setJsonStr(String str) {

                songDetails = ParseTool.getSongDetail(str);
                url = songDetails.getSongLink();
                lrcUrl = songDetails.getLrcLink();

                if (getActivity() != null && songDetails != null && imgThumb != null) {
                    //Picasso.with(getActivity().getApplicationContext()).load(songDetails.getSongPicRadio()).into(imgThumb);
                    Bitmap bitmap = ImageUtils.getImg("qingyue" + songDetails.getSongPicRadio().replace("jpg", "png"));

                    if (bitmap != null) {//从sdk中获取图片
                        Log.d("sdk", "load from sdk----song");

                        imgThumb.setImageBitmap(bitmap);
                    } else {//从网络下载图片
                        Picasso.with(getActivity().getApplicationContext()).load(songDetails.getSongPicRadio()).into(imgThumb);
                        //缓存歌曲图片到本地
                        Drawable drawable = imgThumb.getDrawable();
                        if (drawable != null) {
                            BitmapDrawable d = (BitmapDrawable) drawable;
                            Bitmap bitmap1 = d.getBitmap();
                            try {
                                ImageUtils.saveImg("qingyue" + songDetails.getSongPicRadio().replace("jpg", "png"), bitmap1);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }


                txtArtist.setText(song.getSongArtist());
                txtTitle.setText(song.getSongTitle());

                if (songDetails != null) {
                    //该页面备选中后   发送广播数据：通过本地广播管理器  来发送广播：
                    Intent intent = new Intent(Constants.GET_DATA_TO_SHARE);
                    Bundle bundle = new Bundle();

                    bundle.putSerializable(Constants.DATA_TO_EXTRA, songDetails);//将网络数据传入
                    intent.putExtras(bundle);

                    lbMgr.sendBroadcast(intent);
                }


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

        imgPlay.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

        Intent intent = new Intent(getActivity().getApplicationContext(), PlaySongService.class);
        intent.putExtra("url", url);

        Log.d("new", url + "===" + firstLoadLrc);

        if (firstLoadLrc) {

            new AsyTask(new AsyTask.CallBack() {
                @Override
                public void setJsonStr(String str) {
                    String[] singLinfLrc = str.split("\n");

                    for (int i = 0; i < singLinfLrc.length; i++) {
                        lrcs.add(singLinfLrc[i]);
                    }

                    Constants.lrcList = lrcs;
                }
            }).execute("http://ting.baidu.com" + lrcUrl);
            firstLoadLrc = false;
        }

        intent.putExtra("tag", currentFragment);
        imgControlPlay = (ImageView) v;
        getActivity().getApplicationContext().startService(intent);
        if (!isPlaying) {
            imgPlay.setBackgroundResource(android.R.drawable.ic_media_pause);
            isPlaying = true;
        } else {
            imgPlay.setBackgroundResource(android.R.drawable.ic_media_play);
            isPlaying = false;
        }


    }

    class PrgReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO 接收播放服务组件中发送的进度广播
            int max = intent.getIntExtra(Constants.EXTRA_PROGREES_MAX, 0);
            int cur = intent.getIntExtra(Constants.EXTRA_PROGREES_CUR, 0);

            currentF = intent.getIntExtra("tag", 0);
            Constants.CURRENT_FRAGMENT = currentF;
            /**
             * 当前fragment是正在播放的fragment时才会更新
             */
            if (currentF == currentFragment) {
                skBar.setEnabled(true);

                String time = getTime(cur, false);

                for (String lrc : Constants.lrcList) {
                    if (lrc.contains(time)) {
                        txtLrc.setText(lrc.substring(10));
                    }
                }

                skBar.setMax(max);
                skBar.setProgress(cur);
                imgPlay.setBackgroundResource(android.R.drawable.ic_media_pause);
                if ((max - cur) <= 100) {
                    imgPlay.setBackgroundResource(android.R.drawable.ic_media_play);
                    isPlaying = false;
                    txtLrc.setText("");
                }

            } else {
                skBar.setEnabled(false);
                skBar.setProgress(0);
                imgPlay.setBackgroundResource(android.R.drawable.ic_media_play);
                txtLrc.setText("");
            }
        }
    }

    /**
     * 获取当前播放时间
     *
     * @param time
     * @param add
     * @return
     */
    public static String getTime(int time, boolean add) {

        StringBuilder builder = new StringBuilder();
        int m = time / 1000 / 60;
        int s = (time / 1000) % 60;
        int ss = (time % 100);
        if (add) {
            builder.append(m / 10).append(m % 10).append(":").append(s / 10).append(s % 10).append(".").append(ss / 10).append(ss % 10);
        } else {
            builder.append(m / 10).append(m % 10).append(":").append(s / 10).append(s % 10);
        }
        return builder.toString();
    }

}
