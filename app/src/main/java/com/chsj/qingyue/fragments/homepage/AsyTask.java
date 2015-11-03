package com.chsj.qingyue.fragments.homepage;

import android.os.AsyncTask;

import com.chsj.qingyue.tools.HttpTools;

import java.io.UnsupportedEncodingException;

/**
 * ProjectName: com.chsj.com
 * Created By:chsj
 * Date:2015/11/3
 */
public class AsyTask extends AsyncTask<String, Void, String> {


    private CallBack callBack;

    public AsyTask(CallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    protected String doInBackground(String... params) {

        String result = null;
        String url = params[0];
        if (url != null) {

            byte[] data = HttpTools.doGet(url);
            try {
                String jsonStr = new String(data, "utf-8");
                result = jsonStr;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        return result;
    }


    @Override
    protected void onPostExecute(String s) {

        callBack.setJsonStr(s);

    }

    public interface CallBack {

        void setJsonStr(String str);
    }

}


