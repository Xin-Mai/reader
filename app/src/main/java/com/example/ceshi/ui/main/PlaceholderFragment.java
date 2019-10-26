package com.example.ceshi.ui.main;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bean.Book_simple;
import com.example.bean.ThemeBookList;
import com.example.ceshi.BookAdapter;
import com.example.ceshi.MainActivity;
import com.example.ceshi.R;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private List<Book_simple> hotList ; //热门书籍
    public static final int UPDATE_P1 =1;
    public static final int UPDATE_P2=2;

    private PageViewModel pageViewModel;    //返回和page对应的键值对

    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);   //放进去当前页码
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);  //修改页码
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view=null;
        switch(pageViewModel.getIndex()){
            case 1:
                view = inflater.inflate(R.layout.hot_frag,container,false);
                RecyclerView recyclerView = view.findViewById(R.id.hot_recycler_view);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
                recyclerView.setLayoutManager(linearLayoutManager);
                try {
                    getHot();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;
            case 2:
                view = inflater.inflate(R.layout.page2,container,false);
                RecyclerView male_recyclerView= view.findViewById(R.id.male_recycler_view);
                RecyclerView female_recyclerView=view.findViewById(R.id.female_recycler_view);
                LinearLayoutManager layoutManager=new LinearLayoutManager(this.getContext());
                break;
        }
        return view;
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message message){
            switch (message.what){
                case UPDATE_P1:
                    BookAdapter adapter=new BookAdapter(hotList);
                    RecyclerView recyclerView = getView().findViewById(R.id.hot_recycler_view);
                    recyclerView.setAdapter(adapter);
                    break;
                case UPDATE_P2:
                    break;
            }
        }
    };
    //调用api
    private void getHot() throws IOException {
        this.getRespond
                ("https://api.zhuishushenqi.com/book-list?duration=last-seven-days&sort=collectorCount"
                ,new okhttp3.Callback(){
                    @Override
                        public void onResponse(Call call,Response response) throws IOException{
                        String responseData="";
                        try{
                            responseData = response.body().string();
                        }catch (JsonSyntaxException e){
                            Log.d("Gson",e.toString());
                        }
                        setHotList(responseData);
                        Message msg = new Message() ;
                        msg.what=UPDATE_P1;
                        handler.sendMessage(msg);       //提醒更新
                    }

                    @Override
                    public void onFailure(Call call,IOException e){

                    }
                        });
    }
//获取数据
    private void getRespond(String murl, final okhttp3.Callback callback){
        final String url=murl;
        final OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(callback);
  /*      new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Request request=new Request.Builder()
                            .url(url)
                            .build();
                    client.newCall(request).enqueue(callback);
                    Response response=client.newCall(request).execute();//这里有问题
                    String RespondData = response.body().string();

                }catch (IOException e){
                    Log.d("ExcePtion",e.toString());
                    e.printStackTrace();
                }
            }
        }).start();*/
        Log.d("startOKHTTP","start");
    }
//初始化hotlist
    public void setHotList(String respondData){
        Gson gson=new Gson();
        ThemeBookList HotThemeList=null;
        try {
            HotThemeList = gson.fromJson(respondData, ThemeBookList.class);//这里有问题
        }catch (JsonSyntaxException e){
            Log.d("Json",e.toString());
        }
        hotList=HotThemeList.getBookLists();
    }

 /*   public static Bitmap getBitmap(String murl){
        final Bitmap[] bitmap = {null};
        final String urls=murl;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    try {
                        URL url = new URL(urls);
                        bitmap[0] = BitmapFactory.decodeStream(url.openStream());
                    } catch (MalformedURLException ex) {
                        ex.printStackTrace();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        return bitmap[0];
    }*/
}