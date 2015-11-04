package com.chsj.qingyue.fragments.homepage;

import com.chsj.qingyue.fragments.object.Song;
import com.chsj.qingyue.fragments.object.SongDetails;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

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

    public static List<Song> getSongs(JSONObject jsonObject) {

        List<Song> songs = null;

        try {
            JSONObject object = jsonObject.getJSONObject("result");

            JSONArray array = object.getJSONArray("songlist");

            songs = new ArrayList<Song>();
            int len = array.length();

            for (int i = 0; i < len; i++) {
                JSONObject object1 = array.getJSONObject(i);
                Song song = new Song();
                song.setSongId(object1.getString("songid"));
                song.setSongTitle(object1.getString("title"));
                song.setSongArtist(object1.getString("artist"));
                song.setSongThubm(object1.getString("thumb"));
                songs.add(song);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return songs;
    }

    public static SongDetails getSongDetail(String str) {

        SongDetails songDetails = new SongDetails();

        try {
            JSONObject object = new JSONObject(str);
            JSONObject data = object.getJSONObject("data");
            JSONArray songList = data.getJSONArray("songList");
            JSONObject jsonObject = songList.getJSONObject(0);

            songDetails.setTime(Integer.parseInt(jsonObject.getString("time")));
            songDetails.setLrcLink(jsonObject.getString("lrcLink"));
            songDetails.setSongLink(jsonObject.getString("songLink"));
            songDetails.setSongPicBig(jsonObject.getString("songPicBig"));
            songDetails.setSongPicRadio(jsonObject.getString("songPicRadio"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return songDetails;
    }
}
