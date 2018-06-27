package com.coco.imv3demo;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
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
import com.tencent.imsdk.TIMGroupAddOpt;
import com.tencent.imsdk.TIMGroupMemberRoleType;
import com.tencent.imsdk.TIMImageElem;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMMessage;
import com.tencent.imsdk.TIMMessageListener;
import com.tencent.imsdk.TIMSoundElem;
import com.tencent.imsdk.TIMTextElem;
import com.tencent.imsdk.TIMValueCallBack;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
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
    private Button loginOut,btn_invate;
    private Uri imageUri;
    public static final int Cut_PHOTO = 1;
    public static final int SHOW_PHOTO = 2;
    public static final int PHOTO_ALBUM = 3;
    public static final int SHOW_PHOTO_ALBUM = 4;
    private Uri uri;
    private String path;

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
        btn_invate=findViewById(R.id.btn_invate);
        btn_invate.setOnClickListener(this);
        btn_all.setOnClickListener(this);
        btn_get.setOnClickListener(this);
        loginOut.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_send_all:
                sendTextMsg(TIMConversationType.C2C);
                mEd.setText("");
                break;
            case R.id.btn_get_all:
                GroupMannagerUtils.getInstance().createGroup("Private", "coco2",
                        "introduction", "公告", username,
                        null,null);
                break;
            case R.id.btn_invate:
//                ArrayList<String>list=new ArrayList<>();
//                list.add("lzllzllhl2");
//                GroupMannagerUtils.getInstance().getGroupList();
//                GroupMannagerUtils.getInstance().inviteGroupMember("@TGS#1YYHUFJFF",list);
                GroupMannagerUtils.getInstance().modifyGroupOwner("@TGS#1YYHUFJFF","lzllzllhl2");
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

    //https://blog.csdn.net/holee_bk/article/details/69252085
    //https://blog.csdn.net/xiaolaohuqwer/article/details/52070827
    //https://blog.csdn.net/qq_40543575/article/details/79592971

    private void sendImgMsg() {

        // 创建File对象,用于存储选择的照片
        File outputImage = new File(Environment.getExternalStorageDirectory(), "outputTest.jpg");
        try {
            if (outputImage.exists()) {
                outputImage.delete();
            }
            outputImage.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        imageUri = Uri.fromFile(outputImage);
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        //允许裁剪
        intent.putExtra("crop", true);
        //允许缩放
        intent.putExtra("scale", true);
        //图片的输出位置
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intent, Cut_PHOTO);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case Cut_PHOTO:
                if (resultCode == RESULT_OK) {
                    uri = data.getData();
                    Intent intent = new Intent("com.android.camera.action.CROP");
                    intent.setDataAndType(uri, "image/*");
                    intent.putExtra("scale", true);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                    // 启动裁剪
                    startActivityForResult(intent, SHOW_PHOTO_ALBUM);
                }
                break;
            case SHOW_PHOTO_ALBUM:
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    Cursor cursor = getContentResolver().query(uri, null, null, null, null);
                    if (cursor != null && cursor.moveToFirst()) {
                        path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA));

                        //构造一条消息
                        TIMMessage msg = new TIMMessage();
                        //添加图片
                        TIMImageElem elem = new TIMImageElem();
                        elem.setPath(path);

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
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);

    }

    private void sendVoiceMsg() {
        //构造一条消息
        TIMMessage msg = new TIMMessage();

//添加语音
        TIMSoundElem elem = new TIMSoundElem();
        elem.setPath(Environment.getExternalStorageDirectory() + ""); //填写语音文件路径
        elem.setDuration(20);  //填写语音时长

//将 elem 添加到消息
        if (msg.addElement(elem) != 0) {
            Log.d(TAG, "addElement failed");
            return;
        }
//发送消息
        conversation.sendMessage(msg, new TIMValueCallBack<TIMMessage>() {//发送消息回调
            @Override
            public void onError(int code, String desc) {//发送消息失败
                //错误码code和错误描述desc，可用于定位请求失败原因
                //错误码code含义请参见错误码表
                Log.d(TAG, "send message failed. code: " + code + " errmsg: " + desc);
            }

            @Override
            public void onSuccess(TIMMessage msg) {//发送消息成功
                Log.e(TAG, "SendMsg ok");
            }
        });
    }
}
