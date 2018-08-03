package com.coco.imv3demo.gridview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.coco.imv3demo.R;
import com.coco.imv3demo.activity.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class AddGridViewActivity extends AppCompatActivity {

    private List<Integer> mDatas;
    private GridView mGridView;
    private GridViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_grid_view);
        mGridView=(GridView) findViewById(R.id.gv_test);

        initDatas();
        adapter=new GridViewAdapter(AddGridViewActivity.this,mDatas);
        mGridView.setAdapter(adapter);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                if(position==parent.getChildCount()-1){
                    mDatas.add(R.drawable.ic_launcher);
                    Toast.makeText(AddGridViewActivity.this, "您点击了添加", Toast.LENGTH_SHORT).show();
                    adapter=new GridViewAdapter(AddGridViewActivity.this, mDatas);
                    mGridView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }

            }
        });

    }
    private void initDatas() {
        mDatas=new ArrayList<>();
        mDatas.add(R.drawable.ic_launcher);
        mDatas.add(R.drawable.ic_launcher);
        mDatas.add(R.drawable.ic_launcher);
        mDatas.add(R.drawable.ic_launcher);
        mDatas.add(R.drawable.ic_launcher);

    }


}
