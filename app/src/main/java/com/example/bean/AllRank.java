package com.example.bean;
//单一排行榜适用
public class AllRank {
    private RankBody ranking;
    private boolean ok;

    public RankBody getRanking() {
        return ranking;
    }

    public void setRanking(RankBody ranking) {
        this.ranking = ranking;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }
}
