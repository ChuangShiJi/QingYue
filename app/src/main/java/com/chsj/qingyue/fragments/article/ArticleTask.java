package com.chsj.qingyue.fragments.article;

import android.os.AsyncTask;

import com.chsj.qingyue.tools.HttpTools;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

/**
 * ProjectName : com.chsj.qingyue
 * Created by : whl
 * 2015/11/3
 */
public class ArticleTask extends AsyncTask<String, Void, ArticleEntity> {

    private ArticleCompleteListener listener;

    public ArticleTask(ArticleCompleteListener listener) {
        this.listener = listener;
    }

    @Override
    protected ArticleEntity doInBackground(String... params) {
        ArticleEntity ret = null;
        try {
            if (params != null) {
                byte[] bytes = HttpTools.doPost(params[0], params[1].getBytes());
                String str = new String(bytes, "UTF-8");
                JSONObject object1=new JSONObject(str);
                JSONObject object2=object1.getJSONObject("contentEntity");
                ret = new ArticleEntity();
                ret.parseJson(object2);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    protected void onPostExecute(ArticleEntity articleEntity) {
        listener.resultComplete(articleEntity);
    }

    public  interface ArticleCompleteListener {
        void resultComplete(ArticleEntity articleEntity);

    }
}
