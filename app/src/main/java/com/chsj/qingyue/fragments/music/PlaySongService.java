package com.chsj.qingyue.fragments.music;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.chsj.qingyue.Constants;

import java.io.IOException;

/**
 * ProjectName: com.chsj.com
 * Created By:chsj
 * Date:2015/11/4
 */
public class PlaySongService extends Service {


    private LocalBroadcastManager lbMgr;// 本地广播管理器

    private SeektoReceiver seekReceiver;

    private MediaPlayer mediaPlayer;
    private int sumLen; // 总时长

    //音乐地址
    private static String url = "";

    private static boolean isFirst = true;


    private int currentFragment;

    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = new MediaPlayer();


        // 获取本地广播管理器对象
        lbMgr = LocalBroadcastManager.getInstance(getApplicationContext());

        seekReceiver = new SeektoReceiver();
        lbMgr.registerReceiver(seekReceiver, new IntentFilter(Constants.ACTION_SEEKTO));
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        //播放界面传过的信息
        String urlTemp = intent.getStringExtra("url");
        currentFragment = intent.getIntExtra("tag", 0);


        if (url != null && urlTemp.equals(url)) {


            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
            } else {

                mediaPlayer.start();
                new ProgressThread().start();
            }
        } else {

            url = urlTemp;

            mediaPlayer.reset();
            try {
                mediaPlayer.setDataSource(url);
                mediaPlayer.prepareAsync();
                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mediaPlayer.start();
                        new ProgressThread().start();
                    }
                });

            } catch (IOException e) {
                e.printStackTrace();
            }


        }


        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
        mediaPlayer.release();
        lbMgr.unregisterReceiver(seekReceiver);
    }

    private class ProgressThread extends Thread {

        @Override
        public void run() {

            while (mediaPlayer != null && mediaPlayer.isPlaying()) {

                sumLen = mediaPlayer.getDuration();
                int currentPosition = mediaPlayer.getCurrentPosition();
                Intent intent = new Intent(Constants.ACTION_PROGRESS);
                intent.putExtra(Constants.EXTRA_PROGREES_MAX, sumLen);
                intent.putExtra(Constants.EXTRA_PROGREES_CUR, currentPosition);
                intent.putExtra("tag", currentFragment);
                lbMgr.sendBroadcast(intent);// 发送播放的进度广播

                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    class SeektoReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub
            int seekPosition = intent.getIntExtra(Constants.EXTRA_PROGREES_CUR, 0);

            Log.d("current1", seekPosition + "==");
            mediaPlayer.seekTo(seekPosition);//设置播放器的播放位置
        }
    }
}
