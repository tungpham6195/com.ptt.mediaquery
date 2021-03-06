package com.ptt.libdemo.java_only;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.ptt.libdemo.R;
import com.ptt.mediaquery.MediaQuery;

@SuppressLint("Registered")
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MediaQuery.get().getImageRepository().queryAlbum();
    }
}
