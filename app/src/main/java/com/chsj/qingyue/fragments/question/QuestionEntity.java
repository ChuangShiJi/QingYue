package com.chsj.qingyue.fragments.question;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * ProjectName : com.chsj.qingyue
 * Created by : ChSJ.Team
 * Email : zhaoq_hero163.com
 * On 2015/11/3 // 20:48
 */
public class QuestionEntity implements Serializable {

    /**
     * entQNCmt : {"strId":"","strCnt":"","strD":"","pNum":"","upFg":""}
     * strLastUpdateDate : 2015-11-02 17:23:18
     * strDayDiffer :
     * sWebLk : http://m.wufazhuce.com/question/2015-11-02
     * strPraiseNumber : 5020
     * strQuestionId : 1155
     * strQuestionTitle : 为什么有些活得很自我的人也可以在工作社交中如鱼得水？
     * strQuestionContent : 大闷锅问：很多长辈告诉我，进入职场后社交中不能太过自我，可我后来发现有些活得很自我的人在工作中也很如鱼得水，这是为什么？
     * strAnswerTitle : A-M-E-N-G 答大闷锅：
     * strAnswerContent : 前不久，一个朋友得意洋洋地对我们一票朋友说.....
     * strQuestionMarketTime : 2015-11-03
     * sEditor : 锛堣矗浠荤紪杈戯細鍗ぉ鎴愶級
     */

    private String strLastUpdateDate;
    private String strDayDiffer;
    private String sWebLk;
    private String strPraiseNumber;
    private String strQuestionId;
    private String strQuestionTitle;
    private String strQuestionContent;
    private String strAnswerTitle;
    private String strAnswerContent;
    private String strQuestionMarketTime;
    private String sEditor;


    public void setStrLastUpdateDate(String strLastUpdateDate) {
        this.strLastUpdateDate = strLastUpdateDate;
    }

    public void setStrDayDiffer(String strDayDiffer) {
        this.strDayDiffer = strDayDiffer;
    }

    public void setSWebLk(String sWebLk) {
        this.sWebLk = sWebLk;
    }

    public void setStrPraiseNumber(String strPraiseNumber) {
        this.strPraiseNumber = strPraiseNumber;
    }

    public void setStrQuestionId(String strQuestionId) {
        this.strQuestionId = strQuestionId;
    }

    public void setStrQuestionTitle(String strQuestionTitle) {
        this.strQuestionTitle = strQuestionTitle;
    }

    public void setStrQuestionContent(String strQuestionContent) {
        this.strQuestionContent = strQuestionContent;
    }

    public void setStrAnswerTitle(String strAnswerTitle) {
        this.strAnswerTitle = strAnswerTitle;
    }

    public void setStrAnswerContent(String strAnswerContent) {
        this.strAnswerContent = strAnswerContent;
    }

    public void setStrQuestionMarketTime(String strQuestionMarketTime) {
        this.strQuestionMarketTime = strQuestionMarketTime;
    }

    public void setSEditor(String sEditor) {
        this.sEditor = sEditor;
    }


    public String getStrLastUpdateDate() {
        return strLastUpdateDate;
    }

    public String getStrDayDiffer() {
        return strDayDiffer;
    }

    public String getSWebLk() {
        return sWebLk;
    }

    public String getStrPraiseNumber() {
        return strPraiseNumber;
    }

    public String getStrQuestionId() {
        return strQuestionId;
    }

    public String getStrQuestionTitle() {
        return strQuestionTitle;
    }

    public String getStrQuestionContent() {
        return strQuestionContent;
    }

    public String getStrAnswerTitle() {
        return strAnswerTitle;
    }

    public String getStrAnswerContent() {
        return strAnswerContent;
    }

    public String getStrQuestionMarketTime() {
        return strQuestionMarketTime;
    }

    public String getSEditor() {
        return sEditor;
    }

    @Override
    public String toString() {
        return "QuestionEntity{" +
                ", strLastUpdateDate='" + strLastUpdateDate + '\'' +
                ", strDayDiffer='" + strDayDiffer + '\'' +
                ", sWebLk='" + sWebLk + '\'' +
                ", strPraiseNumber='" + strPraiseNumber + '\'' +
                ", strQuestionId='" + strQuestionId + '\'' +
                ", strQuestionTitle='" + strQuestionTitle + '\'' +
                ", strQuestionContent='" + strQuestionContent + '\'' +
                ", strAnswerTitle='" + strAnswerTitle + '\'' +
                ", strAnswerContent='" + strAnswerContent + '\'' +
                ", strQuestionMarketTime='" + strQuestionMarketTime + '\'' +
                ", sEditor='" + sEditor + '\'' +
                '}';
    }

}
