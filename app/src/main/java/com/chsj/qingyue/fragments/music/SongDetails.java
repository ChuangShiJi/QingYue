package com.chsj.qingyue.fragments.music;

import java.io.Serializable;

/**
 * ProjectName: com.chsj.com
 * Created By:chsj
 * Date:2015/11/4
 */
public class SongDetails implements Serializable {

    private String songPicBig;
    private String songPicRadio;
    private String lrcLink;
    private String songLink;
    private int time;
    private String songName;

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSongPicBig() {
        return songPicBig;
    }

    public void setSongPicBig(String songPicBig) {
        this.songPicBig = songPicBig;
    }

    public String getSongPicRadio() {
        return songPicRadio;
    }

    public void setSongPicRadio(String songPicRadio) {
        this.songPicRadio = songPicRadio;
    }

    public String getLrcLink() {
        return lrcLink;
    }

    public void setLrcLink(String lrcLink) {
        this.lrcLink = lrcLink;
    }

    public String getSongLink() {
        return songLink;
    }

    public void setSongLink(String songLink) {
        this.songLink = songLink;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
