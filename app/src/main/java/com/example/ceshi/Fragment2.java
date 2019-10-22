package com.example.ceshi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Fragment2 extends Fragment {
    private List<Ranking> mrankings=new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.page2,container,false);
        RecyclerView recyclerView= view.findViewById(R.id.rank_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
        try {
            getRank();
        } catch (IOException e) {
            e.printStackTrace();
        }
        RankAdapter adapter = new RankAdapter(mrankings);
        recyclerView.setAdapter(adapter);
        return view;
    }

    private void getRank() throws IOException {
        Gson gson=new Gson();
        OkHttpClient client = new OkHttpClient();
        Request request=new Request.Builder().get()
        .url("http://api.zhuishushenqi.com/ranking/gender").build();
        Response response=client.newCall(request).execute();
        String respondData = response.body().string();
        mrankings=gson.fromJson(respondData, new TypeToken<List<Ranking>>() {
        }.getType());
    }
}
