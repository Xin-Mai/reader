package com.example.ceshi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bean.Book;
import com.example.bean.Book_simple;
import com.example.bean.ThemeBookList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Fragment1 extends Fragment {
    private List<Book_simple> hotList ; //热门书籍
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
        Response response = MainActivity.getRespond
                ("http://api.zhuishushenqi.com/book-list?duration=last-seven-days&sort=collectorCount");
        String respondData = response.body().string();
        ThemeBookList HotThemeList = gson.fromJson(respondData,ThemeBookList.class);
        hotList=HotThemeList.getBookLists();
    }
}
