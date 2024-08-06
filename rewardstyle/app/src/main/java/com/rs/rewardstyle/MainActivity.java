package com.rs.rewardstyle;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.rs.rewardstyle.model.Ltk;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private int curIndex = 0;
    private ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<Ltk> list = new ArrayList<>();
        list.addAll(Utility.getArray(Utility.getJsonFromAssets(this, "storage.json")));

        Button next = findViewById(R.id.next);
        Button prev = findViewById(R.id.prev);
        TextView id = findViewById(R.id.lkt_id);
        ImageView img = findViewById(R.id.hero_img);
        ListView listView = findViewById(R.id.products_list);


        adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, list.get(curIndex).getProduct_ids());
        listView.setAdapter(adapter);
        SetNewData(list, id, img);


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                curIndex++;
                SetNewData(list, id, img);
            }
        });

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                curIndex--;
                SetNewData(list, id, img);

            }
        });
    }

    private void SetNewData(List<Ltk> list, TextView id, ImageView img) {
        if (!list.isEmpty() && curIndex < list.size() && curIndex >= 0) {
            id.setText(list.get(curIndex).getId());
            Glide.with(this)
                    .load(list.get(curIndex).getHero_image())
                    .into(img);
            adapter.clear();
            adapter.addAll(list.get(curIndex).getProduct_ids());
            adapter.notifyDataSetChanged();
        }

    }

    private Bitmap getBitmap(String url_str) {
        URL url = null;
        try {
            url = new URL(url_str);
        } catch (MalformedURLException e) {
            Log.e("MainActivity", e.getMessage());
        }
        Bitmap bmp = null;
        try {
            bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (IOException e) {
            Log.e("MainActivity", e.getMessage());
        }
        return bmp;
    }
}