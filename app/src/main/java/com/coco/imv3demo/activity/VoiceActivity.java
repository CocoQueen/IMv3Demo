package com.coco.imv3demo.activity;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.coco.imv3demo.R;
import com.coco.imv3demo.utils.RecordButton;

public class VoiceActivity extends AppCompatActivity {
    RecordButton img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice);
        img = (RecordButton) findViewById(R.id.mBtn);
        img.setOnFinishedRecordListener(new RecordButton.OnFinishedRecordListener() {
            @Override
            public void onFinishedRecord(String audioPath) {
                //录音完成回调
                Toast.makeText(VoiceActivity.this, audioPath, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void readCancel() {

            }

            @Override
            public void noCancel() {

            }

            @Override
            public void onActionDown() {

            }

            @Override
            public void onActionUp() {

            }

            @Override
            public void onActionMove() {

            }
        });
//        img.getDrawable().setLevel(4000);
        img.getResources().getDrawable(R.mipmap.zeffect_recordbutton__recordview_bottom).setLevel(4000);
        new ProgressTask().execute();
    }

    public class ProgressTask extends AsyncTask<Void, Integer, Void> {
        private int mProgress = 0;

        @Override
        protected Void doInBackground(Void... params) {
            while (mProgress <= 10000) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress(mProgress += 500);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            System.out.println("当前进度：" + values[0]);
        }

    }
}
