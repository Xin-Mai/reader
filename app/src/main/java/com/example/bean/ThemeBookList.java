package com.example.bean;

import java.util.List;

public class ThemeBookList {
    private boolean ok;
    private int total;
    private List<Book_simple> bookLists;


    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Book_simple> getBookLists() {
        return bookLists;
    }

    public void setBookLists(List<Book_simple> bookLists) {
        this.bookLists = bookLists;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }
}
