package com.two.vote.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


public class Score {
//    @Id
    private Integer scoreid;
    private BigDecimal fraction;
    private Long articleid;
    private Long optionssid;
    private int userid;

    public Score(Integer scoreid, BigDecimal fraction, Long articleid) {
        this.scoreid = scoreid;
        this.fraction = fraction;
        this.articleid = articleid;
    }

    public Score(Integer scoreid, BigDecimal fraction, Long articleid, Long optionssid, int userid) {
        this.scoreid = scoreid;
        this.fraction = fraction;
        this.articleid = articleid;
        this.optionssid = optionssid;
        this.userid = userid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }


    public Score() {
    }

    public Integer getScoreid() {
        return scoreid;
    }

    public void setScoreid(Integer scoreid) {
        this.scoreid = scoreid;
    }

    public BigDecimal getFraction() {
        return fraction;
    }

    public void setFraction(BigDecimal fraction) {
        this.fraction = fraction;
    }

    public Long getArticleid() {
        return articleid;
    }

    public void setArticleid(Long articleid) {
        this.articleid = articleid;
    }

    public Long getOptionssid() {
        return optionssid;
    }

    public void setOptionssid(Long optionssid) {
        this.optionssid = optionssid;
    }

    @Override
    public String toString() {
        return "Score{" +
                "scoreid=" + scoreid +
                ", fraction=" + fraction +
                ", articleid=" + articleid +
                ", optionssid=" + optionssid +
                ", userid=" + userid +
                '}';
    }
}
