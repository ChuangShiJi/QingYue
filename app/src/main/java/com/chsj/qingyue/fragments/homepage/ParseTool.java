package com.chsj.qingyue.fragments.homepage;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * ProjectName: com.chsj.com
 * Created By:chsj
 * Date:2015/11/3
 */
public class ParseTool {
    public static HpEntity parse(String jsonStr) {

        HpEntity ret = null;

        try {
            JSONObject object = new JSONObject(jsonStr);
            String result = object.getString("result");
            if ("SUCCESS".equals(result)) {

                JSONObject hpEntity = object.getJSONObject("hpEntity");

                String strThumbnailUrl = hpEntity.getString("strThumbnailUrl");
                String strAuthor = hpEntity.getString("strAuthor");
                String strContent = hpEntity.getString("strContent");
                String strPn = hpEntity.getString("strPn");

                ret = new HpEntity(strThumbnailUrl, strAuthor, strContent, strPn);

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return ret;

    }
}
