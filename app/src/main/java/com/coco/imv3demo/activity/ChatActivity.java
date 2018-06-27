package com.coco.imv3demo.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.coco.imv3demo.R;
import com.coco.imv3demo.utils.CameraAndPhotoUtils;
import com.coco.imv3demo.utils.ConversationUtils;
import com.tencent.imsdk.TIMCallBack;
import com.tencent.imsdk.TIMConversation;
import com.tencent.imsdk.TIMConversationType;
import com.tencent.imsdk.TIMElem;
import com.tencent.imsdk.TIMElemType;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMMessage;
import com.tencent.imsdk.TIMMessageListener;
import com.tencent.imsdk.TIMSoundElem;
import com.tencent.imsdk.TIMValueCallBack;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity implements View.OnClickListener {

    private String username;
    private Button btn_all, btn_get;
    private TIMConversation conversation;
    private static final String TAG = "ChatActivity";
    private EditText mEd;
    private String content;
    private Button loginOut,btn_invate;
//    private Uri imageUri;
//    public static final int Cut_PHOTO = 1;
//    public static final int SHOW_PHOTO = 2;
//    public static final int PHOTO_ALBUM = 3;
//    public static final int SHOW_PHOTO_ALBUM = 4;
//    private Uri uri;
//    private String path;

    protected static final int CHOOSE_PICTURE = 0;
    protected static final int TAKE_PICTURE = 1;
    private static final int CROP_SMALL_PICTURE = 2;
    protected static Uri tempUri;
    private List<String> pathlist=new ArrayList<String>();//存放图片路径的集合


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
                ConversationUtils.getInstance().getConversation(TIMConversationType.C2C,"lzllzllhl2");
               ConversationUtils.getInstance().sendMessage(content);
                mEd.setText("");
                break;
            case R.id.btn_get_all:
//                GroupMannagerUtils.getInstance().createGroup("Private", "coco2",
//                        "introduction", "公告", username,
//                        null,null);
//                break;

            case R.id.btn_invate:

                showChoosePicDialog();
//                String path = pathlist.get(0);

//                ArrayList<String>list=new ArrayList<>();
//                list.add("lzllzllhl2");
////                GroupMannagerUtils.getInstance().getGroupList();
//                GroupMannagerUtils.getInstance().inviteGroupMember("@TGS#1YYHUFJFF",list);
////                GroupMannagerUtils.getInstance().modifyGroupOwner("@TGS#1YYHUFJFF","lzllzllhl2");

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



//    private void sendImgMsg() {
//
//        // 创建File对象,用于存储选择的照片
//        File outputImage = new File(Environment.getExternalStorageDirectory(), "outputTest.jpg");
//        try {
//            if (outputImage.exists()) {
//                outputImage.delete();
//            }
//            outputImage.createNewFile();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        imageUri = Uri.fromFile(outputImage);
//        Intent intent = new Intent("android.intent.action.GET_CONTENT");
//        intent.setType("image/*");
//        //允许裁剪
//        intent.putExtra("crop", true);
//        //允许缩放
//        intent.putExtra("scale", true);
//        //图片的输出位置
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
//        startActivityForResult(intent, Cut_PHOTO);
//
//    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        switch (requestCode) {
//            case Cut_PHOTO:
//                if (resultCode == RESULT_OK) {
//                    uri = data.getData();
//                    Intent intent = new Intent("com.android.camera.action.CROP");
//                    intent.setDataAndType(uri, "image/*");
//                    intent.putExtra("scale", true);
//                    intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
//                    // 启动裁剪
//                    startActivityForResult(intent, SHOW_PHOTO_ALBUM);
//                }
//                break;
//            case SHOW_PHOTO_ALBUM:
//                if (resultCode == RESULT_OK) {
//                    Uri uri = data.getData();
//                    Cursor cursor = getContentResolver().query(uri, null, null, null, null);
//                    if (cursor != null && cursor.moveToFirst()) {
//                        path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA));
//
//                        //构造一条消息
//                        TIMMessage msg = new TIMMessage();
//                        //添加图片
//                        TIMImageElem elem = new TIMImageElem();
//                        elem.setPath(path);
//
//                        //将 elem 添加到消息
//                        if (msg.addElement(elem) != 0) {
//                            Log.d(TAG, "addElement failed");
//                            return;
//                        }
//
//                        //发送消息
//                        conversation.sendMessage(msg, new TIMValueCallBack<TIMMessage>() {//发送消息回调
//                            @Override
//                            public void onError(int code, String desc) {//发送消息失败
//                                //错误码 code 和错误描述 desc，可用于定位请求失败原因
//                                //错误码 code 列表请参见错误码表
//                                Log.d(TAG, "send message failed. code: " + code + " errmsg: " + desc);
//                            }
//
//                            @Override
//                            public void onSuccess(TIMMessage msg) {//发送消息成功
//                                Log.e(TAG, "SendMsg ok");
//                            }
//                        });
//                    }
//                }
//                break;
//        }
//        super.onActivityResult(requestCode, resultCode, data);
//
//    }

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
    /**
     * 显示调用本地相机的对话框
     */
    public void showChoosePicDialog() {
        // TODO dialog弹出的方法
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("添加图片");
        String[] items = { "选择本地照片", "相机" };
        builder.setNegativeButton("取消", null);
        builder.setItems(items, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case CHOOSE_PICTURE: // 选择本地照片
                        Intent openAlbumIntent = new Intent(
                                Intent.ACTION_GET_CONTENT);
                        openAlbumIntent.setType("image/*");
                        startActivityForResult(openAlbumIntent, CHOOSE_PICTURE);
                        break;
                    case TAKE_PICTURE: // 拍照
                        Intent openCameraIntent = new Intent(
                                MediaStore.ACTION_IMAGE_CAPTURE);
                        tempUri = Uri.fromFile(new File(Environment
                                .getExternalStorageDirectory(), "image.jpg"));
                        // 指定照片保存路径（SD卡），image.jpg为一个临时文件，每次拍照后这个图片都会被替换
                        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);
                        startActivityForResult(openCameraIntent, TAKE_PICTURE);
                        break;
                }
            }
        });
        builder.create().show();
    }
    /**
     * 回调
     * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO ？？？
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) { // 如果返回码是可以用的
            switch (requestCode) {
                case TAKE_PICTURE:
                    startPhotoZoom(tempUri); // 开始对图片进行裁剪处理
                    break;
                case CHOOSE_PICTURE:
                    startPhotoZoom(data.getData()); // 开始对图片进行裁剪处理
                    break;
                case CROP_SMALL_PICTURE:
                    if (data != null) {
                        setImageToView(data); // 让刚才选择裁剪得到的图片显示在界面上
                    }
                    break;
            }
        }
    }

    /**
     * 裁剪图片方法实现
     *
     * @param uri
     */
    protected void startPhotoZoom(Uri uri) {
        // TODO 对图片进行裁剪
        if (uri == null) {
            Log.i("tag", "The uri is not exist.");
        }
        tempUri = uri;
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CROP_SMALL_PICTURE);
    }

    /**
     * 保存裁剪之后的图片数据
     *
     * @param
     *
     */
    protected void setImageToView(Intent data) {
        // TODO 将图片设置到控件上
        Bundle extras = data.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            //获取图片的路径，存放到list集合中
            String imagePath = CameraAndPhotoUtils.savePhoto(photo, Environment
                    .getExternalStorageDirectory().getAbsolutePath(), String
                    .valueOf(System.currentTimeMillis()));

            ConversationUtils.getInstance().getConversation(TIMConversationType.C2C,"lzllzllhl2");
            ConversationUtils.getInstance().sendImageMessage(imagePath);
            pathlist.add(imagePath);
        }
    }
}
