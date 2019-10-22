package com.example.ceshi;

import android.widget.ImageView;

public class Book {
    private String _id;
    private String title;
    private String shortIntro;
    private String author;
    private String site;
    private String banned;
    private String latelyFollower;
    private String retentionRatio;
    private ImageView cover;

    public ImageView getCover() {
        return cover;
    }

    public String getShortIntro() {
        return shortIntro;
    }
}
