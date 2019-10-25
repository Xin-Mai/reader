package com.example.ceshi;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.ceshi.ui.main.SectionsPagerAdapter;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

 /*       List<Fragment> fragmentList=new ArrayList<>();
        fragmentList.add(new Fragment1());
        fragmentList.add(new Fragment2());*/
        Log.d("fragment","create ok");
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        final ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = findViewById(R.id.fab);

        tabs.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition(),true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public static Response getRespond( String murl){
        final String url=murl;
        final List<Response> mresponse = new ArrayList<>();
 //       final Response[] mresponse = {null};
       new Thread(new Runnable() {
           @Override
           public void run() {
               try{
                   OkHttpClient client = new OkHttpClient();
                   Request request = new Request.Builder()
                           .url(url).build();
                   Response response = client.newCall(request).execute();  //这里有问题
                   if(response!=null)
                   {
                       mresponse.add(response);
                       Log.d("getHot","getResponse");
                   }

                   else
                       Log.d("getHot","noRespond");
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
       }).start();
        return  mresponse.get(0);
    }

    public static Bitmap getBitmap(String murl){
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
    }
}
