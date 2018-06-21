package com.coco.imv3demo;

import android.app.Application;
import android.os.Environment;
import android.util.Log;

import com.tencent.imsdk.TIMConnListener;
import com.tencent.imsdk.TIMConversation;
import com.tencent.imsdk.TIMFriendshipSettings;
import com.tencent.imsdk.TIMGroupEventListener;
import com.tencent.imsdk.TIMGroupMemberInfo;
import com.tencent.imsdk.TIMGroupSettings;
import com.tencent.imsdk.TIMGroupTipsElem;
import com.tencent.imsdk.TIMLogLevel;
import com.tencent.imsdk.TIMLogListener;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMRefreshListener;
import com.tencent.imsdk.TIMSNSChangeInfo;
import com.tencent.imsdk.TIMSdkConfig;
import com.tencent.imsdk.TIMUserConfig;
import com.tencent.imsdk.TIMUserProfile;
import com.tencent.imsdk.TIMUserStatusListener;
import com.tencent.imsdk.ext.group.TIMGroupAssistantListener;
import com.tencent.imsdk.ext.group.TIMGroupCacheInfo;
import com.tencent.imsdk.ext.group.TIMUserConfigGroupExt;
import com.tencent.imsdk.ext.message.TIMUserConfigMsgExt;
import com.tencent.imsdk.ext.sns.TIMFriendGroup;
import com.tencent.imsdk.ext.sns.TIMFriendshipProxyListener;
import com.tencent.imsdk.ext.sns.TIMUserConfigSnsExt;
import com.tencent.qalsdk.QALInitHelper;
import com.tencent.qalsdk.QALSDKManager;
import com.tencent.qalsdk.service.QalService;

import java.util.List;

import tencent.tls.platform.TLSAccountHelper;
import tencent.tls.platform.TLSLoginHelper;

/**
 * Created by ydx on 18-6-21.
 */

public class MyApplication extends Application {

    private static final String TAG = "MyApplication";

    static MyApplication app;
    int sdkAppId = 1400096181;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        initSDK();

    }

    private void initSDK() {


//初始化 SDK 基本配置
        TIMSdkConfig config = new TIMSdkConfig(sdkAppId)
//                .setAccoutType("18395")
                .enableCrashReport(false)
                .enableLogPrint(true)
                .setLogLevel(TIMLogLevel.DEBUG);
//                .setLogPath(Environment.getExternalStorageDirectory().getPath() + "/justfortest/");

        //初始化 SDK
        boolean init = TIMManager.getInstance().init(getApplicationContext(), config);
        Log.e(TAG, "initSDK: "+init);
//        //基本用户配置
//        TIMUserConfig userConfig = new TIMUserConfig()
//                //设置群组资料拉取字段
//                .setGroupSettings(initGroupSettings())
//                //设置资料关系链拉取字段
//                .setFriendshipSettings(initFriendshipSettings())
//                //设置用户状态变更事件监听器
//                .setUserStatusListener(new TIMUserStatusListener() {
//                    @Override
//                    public void onForceOffline() {
//                        //被其他终端踢下线
//                        Log.i(TAG, "onForceOffline");
//                    }
//
//                    @Override
//                    public void onUserSigExpired() {
//                        //用户签名过期了，需要刷新 userSig 重新登录 SDK
//                        Log.i(TAG, "onUserSigExpired");
//                    }
//                })
//                //设置连接状态事件监听器
//                .setConnectionListener(new TIMConnListener() {
//                    @Override
//                    public void onConnected() {
//                        Log.i(TAG, "onConnected");
//                    }
//
//                    @Override
//                    public void onDisconnected(int code, String desc) {
//                        Log.i(TAG, "onDisconnected");
//                    }
//
//                    @Override
//                    public void onWifiNeedAuth(String name) {
//                        Log.i(TAG, "onWifiNeedAuth");
//                    }
//                })
//                //设置群组事件监听器
//                .setGroupEventListener(new TIMGroupEventListener() {
//                    @Override
//                    public void onGroupTipsEvent(TIMGroupTipsElem elem) {
//                        Log.i(TAG, "onGroupTipsEvent, type: " + elem.getTipsType());
//                    }
//                })
//                //设置会话刷新监听器
//                .setRefreshListener(new TIMRefreshListener() {
//                    @Override
//                    public void onRefresh() {
//                        Log.i(TAG, "onRefresh");
//                    }
//
//                    @Override
//                    public void onRefreshConversation(List<TIMConversation> conversations) {
//                        Log.i(TAG, "onRefreshConversation, conversation size: " + conversations.size());
//                    }
//                });
//
//        //消息扩展用户配置
//        userConfig = new TIMUserConfigMsgExt(userConfig)
//                //禁用消息存储
//                .enableStorage(false)
//                //开启消息已读回执
//                .enableReadReceipt(true);
//        //资料关系链扩展用户配置
//        userConfig = new TIMUserConfigSnsExt(userConfig)
//                //开启资料关系链本地存储
//                .enableFriendshipStorage(true)
//                //设置关系链变更事件监听器
//                .setFriendshipProxyListener(new TIMFriendshipProxyListener() {
//                    @Override
//                    public void OnAddFriends(List<TIMUserProfile> users) {
//                        Log.i(TAG, "OnAddFriends");
//                    }
//
//                    @Override
//                    public void OnDelFriends(List<String> identifiers) {
//                        Log.i(TAG, "OnDelFriends");
//                    }
//
//                    @Override
//                    public void OnFriendProfileUpdate(List<TIMUserProfile> profiles) {
//                        Log.i(TAG, "OnFriendProfileUpdate");
//                    }
//
//                    @Override
//                    public void OnAddFriendReqs(List<TIMSNSChangeInfo> reqs) {
//                        Log.i(TAG, "OnAddFriendReqs");
//                    }
//
//
//                    public void OnAddFriendGroups(List<TIMFriendGroup> friendgroups) {
//                        Log.i(TAG, "OnAddFriendGroups");
//                    }
//
//
//                    public void OnDelFriendGroups(List<String> names) {
//                        Log.i(TAG, "OnDelFriendGroups");
//                    }
//
//
//                    public void OnFriendGroupUpdate(List<TIMFriendGroup> friendgroups) {
//                        Log.i(TAG, "OnFriendGroupUpdate");
//                    }
//                });

//        //群组管理扩展用户配置
//        userConfig = new TIMUserConfigGroupExt(userConfig)
//                //开启群组资料本地存储
//                .enableGroupStorage(true)
//                //设置群组资料变更事件监听器
//                .setGroupAssistantListener(new TIMGroupAssistantListener() {
//                    @Override
//                    public void onMemberJoin(String groupId, List<TIMGroupMemberInfo> memberInfos) {
//                        Log.i(TAG, "onMemberJoin");
//                    }
//
//                    @Override
//                    public void onMemberQuit(String groupId, List<String> members) {
//                        Log.i(TAG, "onMemberQuit");
//                    }
//
//                    @Override
//                    public void onMemberUpdate(String groupId, List<TIMGroupMemberInfo> memberInfos) {
//                        Log.i(TAG, "onMemberUpdate");
//                    }
//
//                    @Override
//                    public void onGroupAdd(TIMGroupCacheInfo groupCacheInfo) {
//                        Log.i(TAG, "onGroupAdd");
//                    }
//
//                    @Override
//                    public void onGroupDelete(String groupId) {
//                        Log.i(TAG, "onGroupDelete");
//                    }
//
//                    @Override
//                    public void onGroupUpdate(TIMGroupCacheInfo groupCacheInfo) {
//                        Log.i(TAG, "onGroupUpdate");
//                    }
//                });
//
//        //将用户配置与通讯管理器进行绑定
//        TIMManager.getInstance().setUserConfig(userConfig);


    }

    private TIMFriendshipSettings initFriendshipSettings() {
        return null;
    }

    private TIMGroupSettings initGroupSettings() {
        return null;
    }

    public static MyApplication getApp() {
        return app;
    }
}
