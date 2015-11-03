package com.chsj.qingyue.fragments.article;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * ProjectName : com.chsj.qingyue
 * Created by : whl
 * 2015/11/3
 */
public class ArticleEntity {

    /**
     * strLastUpdateDate : 2015-11-02 11:03:22
     * strContent : 文章内容
     * sWebLk : http://m.wufazhuce.com/article/2015-11-03
     * wImgUrl : http://211.152.49.184:9000/upload/onephoto/f1446433401988.jpg
     * sRdNum : 84418
     * strPraiseNumber : 7873
     * strContDayDiffer :
     * strContentId : 1214
     * strContTitle : 标题
     * strContAuthor : 作者
     * strContAuthorIntroduce : 作者介绍
     * strContMarketTime : 2015-11-03
     * sGW : 杞胯溅鐑ф帀鏈€鍚庣殑姹芥补锛屽敱鐗囨満鎹笂鏂扮殑榛戣兌锛岄厭鍚ч噷鐨勪汉绾风悍鏁ｅ幓锛屾棭椁愬簵鐨勪笁鏄庢不鍒氬ソ鏂伴矞鍑虹倝銆傝€屾粴婊氱孩灏樹腑锛岄偅浠藉崙寰殑銆佷綆澹颁笅姘旂殑鐖憋紝缁堜簬涔熺唲鐏簡銆�
     * sAuth : 鍛ㄨ嫃濠曪紝闈掑勾浣滃銆�
     * sWbN : 寰俊鍏紬鍙凤細sujiewriting
     * subTitle :
     */
//最新更新时间
    private String strLastUpdateDate;
    //    文章内容
    private String strContent;
    private String sWebLk;
    private String wImgUrl;
    //    阅读次数
    private String sRdNum;
    //    点赞次数
    private String strPraiseNumber;
    private String strContDayDiffer;
    //    文本ID
    private String strContentId;
    //    文章名称
    private String strContTitle;
    //   文章作者
    private String strContAuthor;
    //    文章作者介绍
    private String strContAuthorIntroduce;
    //    文章完成时间
    private String strContMarketTime;
    private String sGW;
    //
    private String sAuth;
    //    微信账号
    private String sWbN;
    private String subTitle;

    public void parseJson(JSONObject jsonObject) throws JSONException {
        if (jsonObject != null) {

            strLastUpdateDate = jsonObject.getString("strLastUpdateDate");
            strContent = jsonObject.getString("strContent");
            strPraiseNumber = jsonObject.getString("strPraiseNumber");
            strContTitle=jsonObject.getString("strContTitle");
            strContAuthor=jsonObject.getString("strContAuthor");
            strContAuthorIntroduce=jsonObject.getString("strContAuthorIntroduce");
            strContMarketTime=jsonObject.getString("strContMarketTime");
            sWbN=jsonObject.getString("sWbN");
            sGW=jsonObject.getString("sGW");
        }

    }

    public void setStrLastUpdateDate(String strLastUpdateDate) {
        this.strLastUpdateDate = strLastUpdateDate;
    }

    public void setStrContent(String strContent) {
        this.strContent = strContent;
    }

    public void setSWebLk(String sWebLk) {
        this.sWebLk = sWebLk;
    }

    public void setWImgUrl(String wImgUrl) {
        this.wImgUrl = wImgUrl;
    }

    public void setSRdNum(String sRdNum) {
        this.sRdNum = sRdNum;
    }

    public void setStrPraiseNumber(String strPraiseNumber) {
        this.strPraiseNumber = strPraiseNumber;
    }

    public void setStrContDayDiffer(String strContDayDiffer) {
        this.strContDayDiffer = strContDayDiffer;
    }

    public void setStrContentId(String strContentId) {
        this.strContentId = strContentId;
    }

    public void setStrContTitle(String strContTitle) {
        this.strContTitle = strContTitle;
    }

    public void setStrContAuthor(String strContAuthor) {
        this.strContAuthor = strContAuthor;
    }

    public void setStrContAuthorIntroduce(String strContAuthorIntroduce) {
        this.strContAuthorIntroduce = strContAuthorIntroduce;
    }

    public void setStrContMarketTime(String strContMarketTime) {
        this.strContMarketTime = strContMarketTime;
    }

    public void setSGW(String sGW) {
        this.sGW = sGW;
    }

    public void setSAuth(String sAuth) {
        this.sAuth = sAuth;
    }

    public void setSWbN(String sWbN) {
        this.sWbN = sWbN;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getStrLastUpdateDate() {
        return strLastUpdateDate;
    }

    public String getStrContent() {
        return strContent;
    }

    public String getSWebLk() {
        return sWebLk;
    }

    public String getWImgUrl() {
        return wImgUrl;
    }

    public String getSRdNum() {
        return sRdNum;
    }

    public String getStrPraiseNumber() {
        return strPraiseNumber;
    }

    public String getStrContDayDiffer() {
        return strContDayDiffer;
    }

    public String getStrContentId() {
        return strContentId;
    }

    public String getStrContTitle() {
        return strContTitle;
    }

    public String getStrContAuthor() {
        return strContAuthor;
    }

    public String getStrContAuthorIntroduce() {
        return strContAuthorIntroduce;
    }

    public String getStrContMarketTime() {
        return strContMarketTime;
    }

    public String getSGW() {
        return sGW;
    }

    public String getSAuth() {
        return sAuth;
    }

    public String getSWbN() {
        return sWbN;
    }

    public String getSubTitle() {
        return subTitle;
    }
}
