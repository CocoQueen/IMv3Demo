package com.coco.imv3demo;

import android.os.Environment;
import android.util.Log;

import com.tencent.imsdk.TIMConversation;
import com.tencent.imsdk.TIMConversationType;
import com.tencent.imsdk.TIMFaceElem;
import com.tencent.imsdk.TIMImageElem;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMMessage;
import com.tencent.imsdk.TIMTextElem;
import com.tencent.imsdk.TIMValueCallBack;

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



}
