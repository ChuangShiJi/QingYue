package com.chsj.qingyue;

/**
 * ProjectName : com.chsj.qingyue
 * Created by : ChSJ
 * Email : zhaoq_hero163.com
 * On 2015/11/2 // 17:49
 *
 */
public class Constants {


    public static final String SP_NAME="app";

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


    /**
     * 发送广播数据
     */
    public static final String GET_DATA_TO_SHARE = "chsj.get.data.share";

    public static final String DATA_TO_EXTRA = "data";

}
