package com.example.ceshi;

import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.bean.Book;
import com.example.ceshi.ui.main.PlaceholderFragment;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.List;

import okhttp3.Response;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder>{
    private List<? extends Book> mBookList;
    final static int LOAD_COVER=1;

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView cover;
        TextView short_intro;

        public ViewHolder(View view){
            super(view);
            cover=(ImageView)view.findViewById(R.id.cover);
            short_intro=(TextView)view.findViewById(R.id.introduction);
        }
    }

    public BookAdapter(List<? extends Book> bookList){
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
        Log.d("cover",book.getCover());
        URL url= null;
        String surl;
        try {
            surl= URLDecoder.decode(book.getCover());
            StringBuilder sb=new StringBuilder(surl.substring(7));
            sb=sb.insert(4,'s');
            surl=sb.toString();
            url = new URL(surl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        setCover(holder,url);
//        holder.cover.setImageBitmap(PlaceholderFragment.getBitmap(book.getCover()));
        holder.short_intro.setText(book.getShortIntro());
    }

    private void setCover(ViewHolder viewHolder,URL url){
        final ViewHolder holder=viewHolder;
        final URL murl = url;
        new Thread(new Runnable(){
            @Override
            public void run(){
                try {
                    holder.cover.setImageBitmap(BitmapFactory.decodeStream(murl.openStream()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    @Override
    public  int getItemCount(){
        return 2;
//        return mBookList.size();
    }

 /*   private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message message){
            switch (message.what){
                case LOAD_COVER:
                    holder.cover.setImageBitmap
                    break;

            }
        }
    };*/
}
