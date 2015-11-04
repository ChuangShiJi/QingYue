package com.chsj.qingyue.fragments.object;

import java.io.Serializable;

/**
 * ProjectName: com.chsj.com
 * Created By:chsj
 * Date:2015/11/4
 */
public class Song implements Serializable {

    private String songId;

    private String songTitle;

    private String songArtist;

    private String songThubm;

    public String getSongId() {
        return songId;
    }

    public void setSongId(String songId) {
        this.songId = songId;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public String getSongArtist() {
        return songArtist;
    }

    public void setSongArtist(String songArtist) {
        this.songArtist = songArtist;
    }

    public String getSongThubm() {
        return songThubm;
    }

    public void setSongThubm(String songThubm) {
        this.songThubm = songThubm;
    }
}
