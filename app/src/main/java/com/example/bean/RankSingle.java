package com.example.bean;

public class RankSingle extends AllRank{
    private String _id;
    private String title;
    private String cover;
    private boolean collapse;
    private String monthRank;
    private String totalRank;
    private String shortTitle;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public boolean isCollapse() {
        return collapse;
    }

    public void setCollapse(boolean collapse) {
        this.collapse = collapse;
    }

    public String getMonthRank() {
        return monthRank;
    }

    public void setMonthRank(String monthRank) {
        this.monthRank = monthRank;
    }

    public String getTotalRank() {
        return totalRank;
    }

    public void setTotalRank(String totalRank) {
        this.totalRank = totalRank;
    }

    public String getShortTitle() {
        return shortTitle;
    }

    public void setShortTitle(String shortTitle) {
        this.shortTitle = shortTitle;
    }
}
