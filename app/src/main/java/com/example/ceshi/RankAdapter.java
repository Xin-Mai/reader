package com.example.ceshi;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.bean.AllRank;

import java.util.ArrayList;
import java.util.List;

public class RankAdapter extends RecyclerView.Adapter<RankAdapter.ViewHolder> {
    private List<AllRank> mRankings;

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView rank_title;
        ImageView book1;
        ImageView book2;//准备换一个布局，卡片式
        ImageView book3;
        ImageView book4;
        ImageView book5;   //每个排行榜显示五本书

        public  ViewHolder(View view){
            super(view);
            rank_title=(TextView) view.findViewById(R.id.rank_title);
            book1=(ImageView)view.findViewById(R.id.book1_pic);
            book2=(ImageView)view.findViewById(R.id.book2_pic);
            book3=(ImageView)view.findViewById(R.id.book3_pic);
            book4=(ImageView)view.findViewById(R.id.book4_pic);
            book5=(ImageView)view.findViewById(R.id.book5_pic);
        }
    }

    public RankAdapter(List<AllRank> rankings){
        mRankings=rankings;
    }

    @Override
    public RankAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rank_item,parent,false);
        RankAdapter.ViewHolder holder = new RankAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RankAdapter.ViewHolder holder, int position){
        AllRank ranking = mRankings.get(position);
        holder.rank_title.setText(ranking.getTitle());
        holder.book1=ranking.getBookCover(1);
        holder.book2=ranking.getBookCover(2);
        holder.book3=ranking.getBookCover(3);
        holder.book4=ranking.getBookCover(4);
        holder.book5=ranking.getBookCover(5);
        //Bitmap bitmap = BitmapFactory.decodeByteArray(imageByte,0,imageByte.length);

    }

    @Override
    public  int getItemCount(){
        return mRankings.size();
    }
}
