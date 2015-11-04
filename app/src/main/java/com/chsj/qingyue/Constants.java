package com.chsj.qingyue;

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

    public static final String POSITION_URL =
            "http://rest.wufazhuce.com/OneForWeb/one/getQ_N?" +
                    "strDate=null&strRow=%s";

    //----------------------------------------
    //经典老歌下的歌曲列表
    public static final String OLD_SONG_URL = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=qianqian&version=2.1.0&method=baidu.ting.radio.getChannelSong&format=json&pn=0&rn=10&channelname=public_shiguang_jingdianlaoge";

    //某一歌曲的详细信息
    public static final String SONG_DETAILS_URL = "http://ting.baidu.com/data/music/links?songIds=%s";

}
