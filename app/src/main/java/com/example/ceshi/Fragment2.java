package com.example.ceshi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bean.RankCollection;
import com.example.bean.RankSingle;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Fragment2 extends Fragment {
    private RankCollection mRankCollection;
    private List<RankSingle> male_rank=new ArrayList<>();
    private List<RankSingle> female_rank = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.page2,container,false);
        RecyclerView male_recyclerView= view.findViewById(R.id.male_recycler_view);
        RecyclerView female_recyclerView=view.findViewById(R.id.female_recycler_view);
        GridLayoutManager layoutManager=new GridLayoutManager(view.getContext(),2);
        try {
            getRank();
        } catch (IOException e) {
            e.printStackTrace();
        }
        RankAdapter female_adapter=new RankAdapter(female_rank);
        RankAdapter male_adapter = new RankAdapter(male_rank);
        male_recyclerView.setAdapter(male_adapter);
        female_recyclerView.setAdapter(female_adapter);
        male_recyclerView.setLayoutManager(layoutManager);
        female_recyclerView.setLayoutManager(layoutManager);
        return view;
    }

    private void getRank() throws IOException {
        Gson gson=new Gson();
        OkHttpClient client = new OkHttpClient();
        Request request=new Request.Builder().get()
        .url("http://api.zhuishushenqi.com/ranking/gender").build();
        Response response=client.newCall(request).execute();
        String respondData = response.body().string();
        mRankCollection=gson.fromJson(respondData,RankCollection.class );
        male_rank=mRankCollection.getMale();
        female_rank=mRankCollection.getFemale();
    }
}
