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
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Fragment1 extends Fragment {
    private List<Book> hotList = new ArrayList<>(); //热门书籍
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.hot_frag,container,false);
        RecyclerView recyclerView = view.findViewById(R.id.hot_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        try {
            getHot();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BookAdapter adapter = new BookAdapter(hotList);
        recyclerView.setAdapter(adapter);
        return view;
    }

    private void getHot() throws IOException {
        Gson gson= new Gson();
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().get()
                .url("http://api.zhuishushenqi.com/ranking/{totalRank}").build();
        Response response = client.newCall(request).execute();
        String respondData = response.body().string();
        hotList=gson.fromJson(respondData, new TypeToken<List<Book>>() {
        }.getType());
    }
}
