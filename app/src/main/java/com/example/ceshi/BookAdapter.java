package com.example.ceshi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder>{
    private List<Book> mBookList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView cover;
        TextView short_intro;

        public ViewHolder(View view){
            super(view);
            cover=(ImageView)view.findViewById(R.id.cover);
            short_intro=(TextView)view.findViewById(R.id.introduction);
        }
    }

    public BookAdapter(List<Book> bookList){
        mBookList=bookList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.hot_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,int position){
        Book book = mBookList.get(position);
        holder.cover=book.getCover();
        holder.short_intro.setText(book.getShortIntro());
    }

    @Override
    public  int getItemCount(){
        return mBookList.size();
    }
}
