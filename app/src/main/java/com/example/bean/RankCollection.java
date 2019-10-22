package com.example.bean;

import java.util.List;
//所有排行榜
public class RankCollection {
    private List<RankSingle> male;
    private List<RankSingle> female;

    public List<RankSingle> getMale() {
        return male;
    }

    public void setMale(List<RankSingle> male) {
        this.male = male;
    }

    public List<RankSingle> getFemale() {
        return female;
    }

    public void setFemale(List<RankSingle> female) {
        this.female = female;
    }
}
