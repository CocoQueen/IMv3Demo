package com.coco.imv3demo.gridview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.coco.imv3demo.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GridViewActivity extends AppCompatActivity {

    private GridView gridview;
    private GridAdapter adapter;
    private List<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);
        gridview = findViewById(R.id.gridview);
        for (int i = 0; i < 100; i++) {
            list.add("条目" + i);
        }

        adapter = new GridAdapter(this, gridview, list);
        //添加并且显示
        gridview.setAdapter(adapter);
        //添加消息处理
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(GridViewActivity.this, list.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
