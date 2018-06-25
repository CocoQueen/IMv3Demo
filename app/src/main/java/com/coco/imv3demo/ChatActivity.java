package com.coco.imv3demo;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tencent.imcore.Msg;
import com.tencent.imsdk.TIMCallBack;
import com.tencent.imsdk.TIMConversation;
import com.tencent.imsdk.TIMConversationType;
import com.tencent.imsdk.TIMElem;
import com.tencent.imsdk.TIMElemType;
import com.tencent.imsdk.TIMImageElem;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMMessage;
import com.tencent.imsdk.TIMMessageListener;
import com.tencent.imsdk.TIMTextElem;
import com.tencent.imsdk.TIMValueCallBack;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ChatActivity extends AppCompatActivity implements View.OnClickListener {

    private String username;
    private Button btn_all, btn_get;
    private TIMConversation conversation;
    private static final String TAG = "ChatActivity";
    private EditText mEd;
    private String content;
    private Button loginOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        username = getIntent().getStringExtra("username");
        setTitle(username);
        initView();
    }

    private void initView() {
        mEd = findViewById(R.id.mEd);
        content = mEd.getText().toString().trim();
        btn_all = findViewById(R.id.btn_send_all);
        btn_get = findViewById(R.id.btn_get_all);
        loginOut = findViewById(R.id.btn_loginOut);
        btn_all.setOnClickListener(this);
        btn_get.setOnClickListener(this);
        loginOut.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_send_all:
                sendImgMsg();
//                sendTextMsg(TIMConversationType.C2C);
                mEd.setText("");
                break;
            case R.id.btn_get_all:
//                getTextMsg();
                break;
            case R.id.btn_loginOut:
                loginOut();
                break;
        }
    }

    public void loginOut() {
        TIMManager.getInstance().logout(new TIMCallBack() {
            @Override
            public void onError(int i, String s) {
                Log.e(TAG, "onError: " + i + "===" + s);
            }

            @Override
            public void onSuccess() {
                Log.e(TAG, "onSuccess: " + "退出成功");
            }
        });
        finish();
    }

    private void getTextMsg() {
        TIMManager.getInstance().addMessageListener(new TIMMessageListener() {
            @Override
            public boolean onNewMessages(List<TIMMessage> list) {
                TIMMessage msg = list.get(0); /* 消息 */

                for (int i = 0; i < msg.getElementCount(); ++i) {
                    TIMElem elem = msg.getElement(i);

                    //获取当前元素的类型
                    TIMElemType elemType = elem.getType();
                    Log.d(TAG, "elem type: " + elemType.name());
                    if (elemType == TIMElemType.Text) {
                        //处理文本消息
                        String s = msg.getMsg().toString();
                        Toast.makeText(ChatActivity.this, s, Toast.LENGTH_SHORT).show();
                    } else if (elemType == TIMElemType.Image) {
                        //处理图片消息
                    }//...处理更多消息
                }

                return false;
            }
        });

    }

    private void sendTextMsg(TIMConversationType type) {


        conversation = TIMManager.getInstance().getConversation(type, "lzllzllhl2");
        //构造一条消息
        TIMMessage msg = new TIMMessage();

        //添加文本内容
        TIMTextElem elem = new TIMTextElem();
        elem.setText(content);

        //将elem添加到消息
        if (msg.addElement(elem) != 0) {
            Log.d(TAG, "addElement failed");
            return;
        }

        conversation.sendMessage(msg, new TIMValueCallBack<TIMMessage>() {
            @Override
            public void onError(int code, String errMsg) {

                Log.e(TAG, "onError: " + code + "=====" + errMsg);
            }

            @Override
            public void onSuccess(TIMMessage timMessage) {
                Log.e(TAG, "onSuccess: " + timMessage.getMsg());

            }
        });
    }

    private void sendImgMsg() {
        //构造一条消息
        TIMMessage msg = new TIMMessage();
//添加图片
        TIMImageElem elem = new TIMImageElem();
        elem.setPath(Environment.getExternalStorageDirectory() + "/DCIM/Camera/1.jpg");

//将 elem 添加到消息
        if (msg.addElement(elem) != 0) {
            Log.d(TAG, "addElement failed");
            return;
        }

//发送消息
        conversation.sendMessage(msg, new TIMValueCallBack<TIMMessage>() {//发送消息回调
            @Override
            public void onError(int code, String desc) {//发送消息失败
                //错误码 code 和错误描述 desc，可用于定位请求失败原因
                //错误码 code 列表请参见错误码表
                Log.d(TAG, "send message failed. code: " + code + " errmsg: " + desc);
            }

            @Override
            public void onSuccess(TIMMessage msg) {//发送消息成功
                Log.e(TAG, "SendMsg ok");
            }
        });
    }
}
