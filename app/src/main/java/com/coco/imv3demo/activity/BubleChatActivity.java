package com.coco.imv3demo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.coco.imv3demo.R;

import java.util.ArrayList;
import java.util.List;

public class BubleChatActivity extends AppCompatActivity {

    private EditText edit;
    private Button btn;
    private ListView listView;
    private String s;
    List<String>list=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buble_chat);

        edit = findViewById(R.id.mEd_send);
        btn = findViewById(R.id.mBtn_send);
        listView = findViewById(R.id.mLv_send);

        s = edit.getText().toString().trim();


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.add(s);
                listView.setAdapter(new BubleAdapter(list,BubleChatActivity.this));

            }
        });


    }
}
