package com.coco.imv3demo.utils;

import android.util.Log;

import com.tencent.imsdk.TIMCallBack;
import com.tencent.imsdk.TIMFriendshipManager;
import com.tencent.imsdk.TIMUserProfile;
import com.tencent.imsdk.TIMValueCallBack;
import com.tencent.imsdk.ext.sns.TIMAddFriendRequest;
import com.tencent.imsdk.ext.sns.TIMDelFriendType;
import com.tencent.imsdk.ext.sns.TIMFriendAddResponse;
import com.tencent.imsdk.ext.sns.TIMFriendResult;
import com.tencent.imsdk.ext.sns.TIMFriendshipManagerExt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by ydx on 18-6-28.
 */

public class RelationShipUtils {
    private static final String TAG = "RelationShipUtils";
    static RelationShipUtils utils = new RelationShipUtils();

    public RelationShipUtils() {
    }

    public static RelationShipUtils getInstance() {
        return utils;
    }

    /*设置自己的资料*/

    /**
     * @param param param: 可设置 昵称、加好友选项、头像、地理位置、个人签名、生日信息、语言信息、性别类型、自定义信息
     */
    public void setSelfProfile(TIMFriendshipManager.ModifyUserProfileParam param) {
//        param.setNickname();  设置昵称
//        param.setAllowType();设置好友验证方式
//        param.setFaceUrl();设置头像
//        param.setLocation();设置地理位置
//        param.setSelfSignature();设置个性签名
//        param.setBirthday();设置生日信息
//        param.setLanguage();设置语言
//        param.setGender();设置性别
//        param.setCustomInfo();设置自定义信息

        TIMFriendshipManager.getInstance().modifyProfile(param, new TIMCallBack() {
            @Override
            public void onError(int code, String desc) {
                //错误码 code 和错误描述 desc，可用于定位请求失败原因
                //错误码 code 列表请参见错误码表
                Log.e(TAG, "modifyProfile failed: " + code + " desc" + desc);
            }

            @Override
            public void onSuccess() {

                Log.e(TAG, "modifyProfile succ");
            }
        });
    }

    /*获取自己的资料*/
    public void getSelfProfile() {
        //获取自己的资料
        TIMFriendshipManager.getInstance().getSelfProfile(new TIMValueCallBack<TIMUserProfile>() {
            @Override
            public void onError(int code, String desc) {
                //错误码 code 和错误描述 desc，可用于定位请求失败原因
                //错误码 code 列表请参见错误码表
                Log.e(TAG, "getSelfProfile failed: " + code + " desc");
            }

            @Override
            public void onSuccess(TIMUserProfile result) {
                Log.e(TAG, "getSelfProfile succ");
                Log.e(TAG, "identifier: " + result.getIdentifier() + " nickName: " + result.getNickName()
                        + " remark: " + result.getSelfSignature() + " allow: " + result.getAllowType());
            }
        });
    }

    /*获取任何人的资料*/

    /**
     * @param users 待获取用户资料的用户列表
     */
    public void getAnyUserProfile(List<String> users) {

        //获取用户资料
        TIMFriendshipManager.getInstance().getUsersProfile(users, new TIMValueCallBack<List<TIMUserProfile>>() {
            @Override
            public void onError(int code, String desc) {
                //错误码 code 和错误描述 desc，可用于定位请求失败原因
                //错误码 code 列表请参见错误码表
                Log.e(TAG, "getUsersProfile failed: " + code + " desc");
            }

            @Override
            public void onSuccess(List<TIMUserProfile> result) {
                Log.e(TAG, "getUsersProfile succ");
                for (TIMUserProfile res : result) {
                    Log.e(TAG, "identifier: " + res.getIdentifier() + " nickName: " + res.getNickName()
                            + " remark: " + res.getRemark());
                }
            }
        });
    }

    /**
     * 获取好友资料
     *
     * @param users 待获取用户资料的好友列表
     */

    public void getFriendProfile(List<String> users) {

        //获取好友资料
        TIMFriendshipManagerExt.getInstance().getFriendsProfile(users, new TIMValueCallBack<List<TIMUserProfile>>() {
            @Override
            public void onError(int code, String desc) {
                //错误码 code 和错误描述 desc，可用于定位请求失败原因
                //错误码 code 列表请参见错误码表
                Log.e(TAG, "getFriendsProfile failed: " + code + " desc");
            }

            @Override
            public void onSuccess(List<TIMUserProfile> result) {
                Log.e(TAG, "getFriendsProfile succ");
                for (TIMUserProfile res : result) {
                    Log.e(TAG, "identifier: " + res.getIdentifier() + " nickName: " + res.getNickName()
                            + " remark: " + res.getRemark());
                }
            }
        });
    }

    /**
     * 设置好友备注
     *
     * @param param setRemark();
     */
    public void setFriendProfile(TIMFriendshipManagerExt.ModifySnsProfileParam param) {

        TIMFriendshipManagerExt.getInstance().modifySnsProfile(param, new TIMCallBack() {
            @Override
            public void onError(int code, String desc) {
                //错误码 code 和错误描述 desc，可用于定位请求失败原因
                //错误码 code 列表请参见错误码表
                Log.e(TAG, "modifySnsProfile failed: " + code + " desc" + desc);
            }

            @Override
            public void onSuccess() {
                Log.e(TAG, "modifySnsProfile succ");
            }
        });
    }

    /**
     * 添加好友
     *
     * @param reqList 请求列表
     */
    public void addFriend(List<TIMAddFriendRequest> reqList) {
//        //创建请求列表
//        List<TIMAddFriendRequest> reqList = new ArrayList<TIMAddFriendRequest>();
//
//        //添加好友请求
//        TIMAddFriendRequest req = new TIMAddFriendRequest("sample_user_1");
//        req.setAddrSource("DemoApp");
//        req.setAddWording("add me");
//        req.setRemark("Cat");
//
//        reqList.add(req);

        //申请添加好友
        TIMFriendshipManagerExt.getInstance().addFriend(reqList, new TIMValueCallBack<List<TIMFriendResult>>() {
            @Override
            public void onError(int code, String desc) {
                //错误码 code 和错误描述 desc，可用于定位请求失败原因
                //错误码 code 列表请参见错误码表
                Log.e(TAG, "addFriend failed: " + code + " desc");
            }

            @Override
            public void onSuccess(List<TIMFriendResult> result) {
                Log.e(TAG, "addFriend succ");
                for (TIMFriendResult res : result) {
                    Log.e(TAG, "identifier: " + res.getIdentifer() + " status: " + res.getStatus());
                }
            }
        });
    }

    /**
     * 删除好友（可选择删除好友的方式：单向/双向）
     *
     * @param param
     */
    public void deleteFriend(TIMFriendshipManagerExt.DeleteFriendParam param) {

        TIMFriendshipManagerExt.getInstance().delFriend(param, new TIMValueCallBack<List<TIMFriendResult>>() {
            @Override
            public void onError(int code, String desc) {
                //错误码 code 和错误描述 desc，可用于定位请求失败原因
                //错误码 code 列表请参见错误码表
                Log.e(TAG, "delFriend failed: " + code + " desc");
            }

            @Override
            public void onSuccess(List<TIMFriendResult> timFriendResults) {
                Log.d(TAG, "delFriend succ");
                for (TIMFriendResult result : timFriendResults) {
                    Log.d(TAG, "result id: " + result.getIdentifer() + "|status: " + result.getStatus());
                }

            }
        });
    }

    /**
     * 获取好友列表
     */
    public void getFriendList() {
        //获取好友列表
        TIMFriendshipManagerExt.getInstance().getFriendList(new TIMValueCallBack<List<TIMUserProfile>>() {
            @Override
            public void onError(int code, String desc) {
                //错误码 code 和错误描述 desc，可用于定位请求失败原因
                //错误码 code 列表请参见错误码表
                Log.e(TAG, "getFriendList failed: " + code + " desc");
            }

            @Override
            public void onSuccess(List<TIMUserProfile> result) {
                for (TIMUserProfile res : result) {
                    Log.e(TAG, "identifier: " + res.getIdentifier() + " nickName: " + res.getNickName()
                            + " remark: " + res.getRemark());
                }
            }
        });
    }

    /**
     * 好友申请
     *
     * @param response 回应内容
     */
    public void addFriendResponse(TIMFriendAddResponse response) {
        TIMFriendshipManagerExt.getInstance().addFriendResponse(response, new TIMValueCallBack<TIMFriendResult>() {
            @Override
            public void onError(int i, String s) {
                Log.e(TAG, "onError: " + i + "===" + s);
            }

            @Override
            public void onSuccess(TIMFriendResult timFriendResult) {
                Log.e(TAG, "onSuccess: " + timFriendResult.getStatus());
            }
        });
    }

    /**
     * 把用户从黑名单列表删除
     *
     * @param identifiers
     */
    public void deleteBlackList(List<String> identifiers) {

        TIMFriendshipManagerExt.getInstance().delBlackList(identifiers, new TIMValueCallBack<List<TIMFriendResult>>() {
            @Override
            public void onError(int i, String s) {
                Log.e(TAG, "onError: " + i + "===" + s);
            }

            @Override
            public void onSuccess(List<TIMFriendResult> timFriendResults) {
                Log.e(TAG, "onSuccess: " + timFriendResults.size());
            }
        });
    }


}
