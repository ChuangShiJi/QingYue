package com.chsj.qingyue.tools;

/**
 * ProjectName: com.chsj.com
 * Created By:chsj
 * Date:2015/11/4
 */

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 检测当前网络是否可用
 */
public class NetWorkUtils {

    public static boolean isConnect(Context context) {

        boolean ret = false;

        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager != null) {
            NetworkInfo networkInfo = manager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) {
                if (networkInfo.getState() == NetworkInfo.State.CONNECTED) {
                    ret = true;
                }
            } else {
                ret = false;
            }
        }
        return ret;
    }

}
