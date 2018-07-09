package com.coco.imv3demo.activity;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.coco.imv3demo.R;

import java.util.ArrayList;
import java.util.List;

public class LuYinActivity extends AppCompatActivity implements AudioRecordButton.AudioRecordFinishListener, AdapterView.OnItemClickListener, MediaPlayer.OnCompletionListener {

    private ListView listView;
    private AudioRecordButton btn;
    List<Recorder>list=new ArrayList<>();
    VoiceListAdapter adapter;
    private View anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lu_yin);
        listView = findViewById(R.id.listview);
        btn = findViewById(R.id.record);

        btn.setAudioRecordFinishListener(this);
        adapter=new VoiceListAdapter(this,list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onFinish(float second, String filePath) {
        Recorder recorder=new Recorder(second,filePath);
        list.add(recorder);
        adapter.notifyDataSetChanged();
        listView.setSelection(list.size()-1);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        if (anim!=null){
            anim.setBackgroundResource(R.drawable.adj);
            anim=null;
        }
        //播放动画
        anim = view.findViewById(R.id.anim);
        anim.setBackgroundResource(R.drawable.play_anim);
        AnimationDrawable animDraw = (AnimationDrawable) anim.getBackground();
        animDraw.start();
        //播放音频
        MediaManager.playSound(list.get(position).getFilePath(),this);
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        anim.setBackgroundResource(R.drawable.adj);

    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onPause() {
        super.onPause();
        MediaManager.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MediaManager.resume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MediaManager.release();
    }
}
