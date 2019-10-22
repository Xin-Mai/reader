package com.example.ceshi;

import android.widget.ImageView;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class Ranking {
    private String _id;
    private String updated;
    private String title;
    private String tag;
    private String cover;
    private String icon;
    private int __v;
    private String monthRank;
    private String totalRank;
    private String shortTitle;
    private String created;
    private boolean isSub;
    private String collapse;
    private String gender;
    private int priortiiy;
    private List<Book> books = new ArrayList<>();

    public Ranking(){
        for(int i =0;i<5;i++)
            books.add(new Book());
    }

    public String getTitle() {
        return title;
    }

    public ImageView getBookCover(int position){
        return books.get(position).getCover();
    }
}
