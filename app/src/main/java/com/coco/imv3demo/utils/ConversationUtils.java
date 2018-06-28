package com.coco.imv3demo.utils;

import android.util.Log;

import com.tencent.imsdk.TIMCallBack;
import com.tencent.imsdk.TIMConversation;
import com.tencent.imsdk.TIMConversationType;
import com.tencent.imsdk.TIMElem;
import com.tencent.imsdk.TIMElemType;
import com.tencent.imsdk.TIMFaceElem;
import com.tencent.imsdk.TIMFileElem;
import com.tencent.imsdk.TIMImage;
import com.tencent.imsdk.TIMImageElem;
import com.tencent.imsdk.TIMLocationElem;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMMessage;
import com.tencent.imsdk.TIMMessageListener;
import com.tencent.imsdk.TIMSoundElem;
import com.tencent.imsdk.TIMTextElem;
import com.tencent.imsdk.TIMValueCallBack;
import com.tencent.imsdk.protocol.msg;

import java.util.List;

/**
 * Created by ydx on 18-6-27.
 */

public class ConversationUtils {
    private static final String TAG = "ConversationUtils";
    private static ConversationUtils utils = new ConversationUtils();
    private TIMConversation conversation;

    private ConversationUtils() {
    }

    public static ConversationUtils getInstance() {
        return utils;
    }

    /**
     * 获取会话
     * TODO  这个方法是发送任何类型的消息之前都必须要调用的方法
     *
     * @param type 会话类型
     * @param peer 参与会话的对方, C2C 会话为对方帐号 identifier, 群组会话为群组 Id
     */
    public void getConversation(TIMConversationType type, String peer) {
        conversation = TIMManager.getInstance().getConversation(
                type,
                peer);
    }

    /**
     * 发送文本消息(包含手机系统表情)
     *
     * @param str 发送的消息内容
     */
    public void sendMessage(String str) {
        //构造一条消息
        TIMMessage msg = new TIMMessage();
        //添加文本内容
        TIMTextElem elem = new TIMTextElem();
        elem.setText(str);
        //将elem添加到消息
        if (msg.addElement(elem) != 0) {
            Log.d(TAG, "addElement failed");
            return;
        }
        //发送消息
        conversation.sendMessage(msg, new TIMValueCallBack<TIMMessage>() {//发送消息回调
            @Override
            public void onError(int code, String desc) {//发送消息失败
                //错误码 code 和错误描述 desc，可用于定位请求失败原因
                //错误码 code 含义请参见错误码表
                Log.d(TAG, "send message failed. code: " + code + " errmsg: " + desc);
            }

            @Override
            public void onSuccess(TIMMessage msg) {//发送消息成功
                Log.e(TAG, "SendMsg ok");
            }
        });
    }

    /**
     * 发送图片消息
     *
     * @param imagePath 图片路径
     */
    public void sendImageMessage(String imagePath) {
        //构造一条消息
        TIMMessage msg = new TIMMessage();
        //添加图片
        TIMImageElem elem = new TIMImageElem();
        elem.setPath(imagePath);
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

    /**
     * 发送表情消息
     * 暂时没有
     */
    public void sendEmojiMessage() {
        //构造一条消息
        TIMMessage msg = new TIMMessage();

        //添加表情
        TIMFaceElem elem = new TIMFaceElem();
        byte[] sampleByteArray = new byte[0];
        elem.setData(sampleByteArray); //自定义 byte[]
        elem.setIndex(10);   //自定义表情索引

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
                //错误码 code 含义请参见错误码表
                Log.d(TAG, "send message failed. code: " + code + " errmsg: " + desc);
            }

            @Override
            public void onSuccess(TIMMessage msg) {//发送消息成功
                Log.e(TAG, "SendMsg ok");
            }
        });
    }

    /**
     * 发送语音消息
     *
     * @param path
     */
    public void sendSoundMessage(String path) {
        //构造一条消息
        TIMMessage msg = new TIMMessage();

        //添加语音
        TIMSoundElem elem = new TIMSoundElem();
        elem.setPath(path); //填写语音文件路径
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
     * 发送位置消息
     *
     * @param latitude  纬度
     * @param longitude 经度
     * @param desc      描述
     */
    public void sendLocationMessage(int latitude, int longitude, String desc) {
        //构造一条消息
        TIMMessage msg = new TIMMessage();

        //添加位置信息
        TIMLocationElem elem = new TIMLocationElem();
        elem.setLatitude(latitude);   //设置纬度
        elem.setLongitude(longitude);   //设置经度
        elem.setDesc(desc);

        //将elem添加到消息
        if (msg.addElement(elem) != 0) {
            Log.d(TAG, "addElement failed");
            return;
        }
        //发送消息
        conversation.sendMessage(msg, new TIMValueCallBack<TIMMessage>() {//发送消息回调
            @Override
            public void onError(int code, String desc) {//发送消息失败
                //错误码 code 和错误描述 desc，可用于定位请求失败原因
                //错误码 code 含义请参见错误码表
                Log.d(TAG, "send message failed. code: " + code + " errmsg: " + desc);
            }

            @Override
            public void onSuccess(TIMMessage msg) {//发送消息成功
                Log.e(TAG, "SendMsg ok");
            }
        });
    }

    /**
     * 发送文件消息
     *
     * @param path     路径
     * @param fileName 文件名字
     */
    public void sendFileMessage(String path, String fileName) {
        //构造一条消息
        TIMMessage msg = new TIMMessage();

        //添加文件内容
        TIMFileElem elem = new TIMFileElem();
        elem.setPath(path); //设置文件路径
        elem.setFileName(fileName); //设置消息展示用的文件名称

        //将 elem 添加到消息
        if (msg.addElement(elem) != 0) {
            Log.d(TAG, "addElement failed");
            return;
        }
        //发送消息
        conversation.sendMessage(msg, new TIMValueCallBack<TIMMessage>() {//发送消息回调
            @Override
            public void onError(int code, String desc) {//发送消息失败
                //错误码code和错误描述 desc，可用于定位请求失败原因
                //错误码 code 含义请参见错误码表
                Log.d(TAG, "send message failed. code: " + code + " errmsg: " + desc);
            }

            @Override
            public void onSuccess(TIMMessage msg) {//发送消息成功
                Log.e(TAG, "SendMsg ok");
            }
        });
    }

    public void sendOnlineMessage() {
        TIMMessage msg = new TIMMessage();
//        TIMElem elem = new TIMTextElem();

        conversation.sendMessage(msg, new TIMValueCallBack<TIMMessage>() {
            @Override
            public void onError(int i, String s) {
                Log.e(TAG, "onError: " + i + "====" + s);
            }

            @Override
            public void onSuccess(TIMMessage timMessage) {
                Log.e(TAG, "onSuccess: " + "sendmsg ok");
            }
        });
    }

    /*获取消息并解析*/
    public void getMessage(String path) {

        TIMMessage msg = new TIMMessage();

        TIMManager.getInstance().addMessageListener(new TIMMessageListener() {
            @Override
            public boolean onNewMessages(List<TIMMessage> list) {
                for (int i = 0; i < msg.getElementCount(); ++i) {
//                    TIMMessage msg = list.get(i);
                    TIMElem elem = msg.getElement(i);

                    //获取当前元素的类型
                    TIMElemType elemType = elem.getType();
                    Log.d(TAG, "elem type: " + elemType.name());

                    switch (elemType) {
                        case Text:
                            String textMessage = msg.getMsg().toString();
                            Log.e(TAG, "onNewMessages: " + textMessage);
                            break;
                        case Face:
                            break;
                        case Image:

                            //遍历一条消息的元素列表
                            for (int j = 0; j < msg.getElementCount(); ++j) {
                                TIMElem elem2 = msg.getElement(j);
                                if (elem2.getType() == TIMElemType.Image) {
                                    //图片元素
                                    TIMImageElem e = (TIMImageElem) elem2;
                                    for (TIMImage image : e.getImageList()) {

                                        //获取图片类型, 大小, 宽高
                                        Log.d(TAG, "image type: " + image.getType() +
                                                " image size " + image.getSize() +
                                                " image height " + image.getHeight() +
                                                " image width " + image.getWidth());

                                        image.getImage(path, new TIMCallBack() {
                                            @Override
                                            public void onError(int code, String desc) {//获取图片失败
                                                //错误码 code 和错误描述 desc，可用于定位请求失败原因
                                                //错误码 code 含义请参见错误码表
                                                Log.d(TAG, "getImage failed. code: " + code + " errmsg: " + desc);
                                            }

                                            @Override
                                            public void onSuccess() {//成功，参数为图片数据
                                                //doSomething
                                                Log.d(TAG, "getImage success.");
                                            }
                                        });
                                    }
                                }
                            }
                            break;
                        case Location:
                            break;
                        case Sound:
                            break;
                        case File:
                            break;
                        case Video:
                            break;
                        case UGC:
                            break;
                    }
                }


                return false;
            }
        });
    }
}
