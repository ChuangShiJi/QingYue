package com.chsj.qingyue;

import android.support.v4.app.Fragment;

import com.chsj.qingyue.fragments.article.ArticleFragment;
import com.chsj.qingyue.fragments.homepage.HomePageFragment;
import com.chsj.qingyue.fragments.music.SongFragment;
import com.chsj.qingyue.fragments.person.PersonFragment;
import com.chsj.qingyue.fragments.question.QuestionFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * ProjectName : com.chsj.qingyue
 * Created by : ChSJ
 * Email : zhaoq_hero163.com
 * On 2015/11/2 // 17:49
 */
public class Constants {


    public static final String SP_NAME = "app";

    //    欢迎页显示的版本，根据版本判断是否显示
    public static final String SP_KEY_IS_AGREE = "agree";


    public static final String URL_HOME_PAGE =
            "http://rest.wufazhuce.com/OneForWeb/one/getHp_N?" +
                    "strDate=null&strRow=%d";
    //    文章的地址链接
    public static final String ARTICLE_URL =
            "http://rest.wufazhuce.com/OneForWeb/one/getC_N";

    public static final String QUESTION_URL =
            "http://rest.wufazhuce.com/OneForWeb/one/getQ_N?" +
                    "strDate=null&strRow=%s";

    //----------------------------------------
    //歌曲列表
    public static final String OLD_SONG_URL = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=qianqian&version=2.1.0&method=baidu.ting.radio.getChannelSong&format=json&pn=0&rn=10&channelname=public_shiguang_jingdianlaoge";

    //某一歌曲的详细信息
    public static final String SONG_DETAILS_URL = "http://ting.baidu.com/data/music/links?songIds=%s";

    /**
     * 发送广播数据
     */
    public static final String GET_DATA_TO_SHARE = "chsj.get.data.share";

    public static final String DATA_TO_EXTRA = "data";

    //音乐总长度
    public static final String EXTRA_PROGREES_MAX = "total";

    //音乐当前进度
    public static final String EXTRA_PROGREES_CUR = "current";

    //更新进度的广播
    public static final String ACTION_PROGRESS = "progress";
    //更新播放的位置
    public static final String ACTION_SEEKTO = "seek";

    public static List<String> lrcList = new ArrayList<String>();

    public static int CURRENT_FRAGMENT = 0;

    //程序是否退出
    public static boolean isExit = false;


    //控制Fragment切换不销毁
    //当前Activity显示的Fragment
    public static Fragment ACTIVITY_CURRENT_FRAGMENT = new HomePageFragment();

    public static final HomePageFragment FRAGMENT_HOME = (HomePageFragment) Constants.ACTIVITY_CURRENT_FRAGMENT;
    public static final ArticleFragment FRAGMENT_ARTICLE = new ArticleFragment();
    public static final QuestionFragment FRAGMENT_QUESTION = new QuestionFragment();
    public static final SongFragment FRAGMENT_SONG = new SongFragment();
    public static final PersonFragment FRAGMENT_PERSON = new PersonFragment();

    //-----------------------------------------------

}
