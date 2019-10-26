package com.example.ceshi;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.bean.AllRank;
import com.example.bean.RankSingle;
import com.example.ceshi.ui.main.PlaceholderFragment;

import java.util.ArrayList;
import java.util.List;

public class RankAdapter extends RecyclerView.Adapter<RankAdapter.ViewHolder> {
    private List<RankSingle> mRankings;

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView rank_title;
        ImageView cover;

        public  ViewHolder(View view){
            super(view);
            rank_title=(TextView) view.findViewById(R.id.rank_title);
            cover = (ImageView)view.findViewById(R.id.rank_cover);
        }
    }

    public RankAdapter(List<RankSingle> rankings){
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
        RankSingle ranking= mRankings.get(position);
        holder.rank_title.setText(ranking.getShortTitle());
 //       holder.cover.setImageBitmap(PlaceholderFragment.getBitmap(ranking.getCover()));
        //Bitmap bitmap = BitmapFactory.decodeByteArray(imageByte,0,imageByte.length);

    }

    @Override
    public  int getItemCount(){
        return mRankings.size();
    }
}
