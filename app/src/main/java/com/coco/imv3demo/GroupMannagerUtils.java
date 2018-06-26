package com.coco.imv3demo;

import android.util.Log;

import com.tencent.imsdk.TIMCallBack;
import com.tencent.imsdk.TIMGroupAddOpt;
import com.tencent.imsdk.TIMGroupManager;
import com.tencent.imsdk.TIMGroupMemberInfo;
import com.tencent.imsdk.TIMGroupMemberRoleType;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMValueCallBack;
import com.tencent.imsdk.ext.group.TIMGroupBaseInfo;
import com.tencent.imsdk.ext.group.TIMGroupManagerExt;
import com.tencent.imsdk.ext.group.TIMGroupMemberResult;
import com.tencent.imsdk.ext.group.TIMGroupMemberRoleFilter;
import com.tencent.imsdk.ext.group.TIMGroupMemberSucc;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ydx on 18-6-26.
 */

public class GroupMannagerUtils {

    private static final String TAG = "GroupMannagerUtils";

    private static GroupMannagerUtils utils = new GroupMannagerUtils();

    public GroupMannagerUtils() {

    }

    public static GroupMannagerUtils getInstance() {
        return utils;
    }

    /**
     * 创建群的方法
     *
     * @param groupType    群类型
     *                     目前支持的群类型：私有群（Private）、公开群（Public）、
     *                     聊天室（ChatRoom）、互动直播聊天室（AVChatRoom）和在线成员广播大群（BChatRoom）
     * @param groupName    群名称
     * @param introduction 群简介
     * @param notification 群公告
     * @param user         群成员
     * @param option       加群选项
     * @param type         群成员类型
     */
    public void createGroup(String groupType, String groupName, String introduction, String notification, String user,
                            TIMGroupAddOpt option, TIMGroupMemberRoleType type) {
        TIMGroupManager.CreateGroupParam param = new TIMGroupManager.CreateGroupParam(groupType, groupName);
        //指定群简介
        param.setIntroduction(introduction);
        //指定群公告
        param.setNotification(notification);
        //加群选项
        param.setAddOption(option);
        //添加群成员
        List<TIMGroupMemberInfo> infos = new ArrayList<TIMGroupMemberInfo>();
        TIMGroupMemberInfo member = new TIMGroupMemberInfo("lzllzllhl2");
        member.setRoleType(type);
        infos.add(member);
        param.setMembers(infos);
        //创建群组
        TIMGroupManager.getInstance().createGroup(param, new TIMValueCallBack<String>() {
            @Override
            public void onError(int code, String desc) {
                Log.d(TAG, "create group failed. code: " + code + " errmsg: " + desc);
            }

            @Override
            public void onSuccess(String s) {
                Log.d(TAG, "create group succ, groupId:" + s);
                Log.e(TAG, "onSuccess: " + "创建群组成功");
            }
        });
    }

    /**
     * 邀请用户入群
     * <p>
     * 权限说明:
     * 私有群：群成员无需受邀用户同意，直接将其拉入群中。
     * 公开群/聊天室：不允许群成员邀请他人入群。
     * 互动直播聊天室：不允许任何人（包括 App 管理员）邀请他人入群。
     *
     * @param user    邀请的用户
     * @param groupId 群组id
     * @param list    待加入群组的用户列表
     */
    public void inviteGroupMember(String user, String groupId, ArrayList<String> list) {
        //添加用户
        list.add(user);
        //将 list 中的用户加入群组
        TIMGroupManagerExt.getInstance().inviteGroupMember(
                groupId,   //群组 ID
                list,      //待加入群组的用户列表
                new TIMValueCallBack<List<TIMGroupMemberResult>>() {
                    @Override
                    public void onError(int i, String s) {
                        Log.e(TAG, "onError: " + i + "=====" + s);
                    }

                    @Override
                    public void onSuccess(List<TIMGroupMemberResult> timGroupMemberResults) {
                        for (TIMGroupMemberResult r : timGroupMemberResults) {
                            Log.d(TAG, "result: " + r.getResult()  //操作结果:  0:添加失败；1：添加成功；2：原本是群成员
                                    + " user: " + r.getUser());    //用户帐号
                            Log.e(TAG, "onSuccess: " + "邀请用户进群成功");
                        }
                    }
                });
    }

    /**
     * 申请加入群组
     * <p>
     * 权限说明：
     * 私有群：不能由用户主动申请入群。
     * 公开群和聊天室：可以主动申请进入， 如果群组设置为需要审核，申请后管理员和群主会受到申请入群系统消息，需要等待管理员或者群主审核，如果群组设置为任何人可加入，则直接入群成功。
     * 直播大群：可以任意加入群组。
     *
     * @param GroupId 所要加入的群组id
     * @param reason  加群理由
     */
    public void applyJoinGroup(String GroupId, String reason) {
        TIMGroupManager.getInstance().applyJoinGroup(GroupId, reason, new TIMCallBack() {
            @java.lang.Override
            public void onError(int code, String desc) {
                Log.e(TAG, "disconnected" + code + "=====" + desc);
            }

            @java.lang.Override
            public void onSuccess() {
                Log.i(TAG, "join group");
                Log.e(TAG, "onSuccess: " + "加入群组成功");
            }
        });
    }

    /**
     * 退出群组
     * 私有群：全员可退出群组。
     * 公开群、聊天室和直播大群：群主不能退出。
     *
     * @param groupId
     */
    public void quitGroup(String groupId) {
        //退出群组
        TIMGroupManager.getInstance().quitGroup(
                groupId,  //群组 ID
                new TIMCallBack() {
                    @Override
                    public void onError(int i, String s) {
                        Log.e(TAG, "onError: " + i + "====" + s);
                    }

                    @Override
                    public void onSuccess() {
                        Log.e(TAG, "onSuccess: " + "退出群组成功");

                    }
                });
    }

    /**
     * 删除群成员
     *
     * @param list    群成员列表
     * @param user    群成员
     * @param reason  删除理由
     * @param groupId 群id
     */
    public void deleteGroupMember(ArrayList<String> list, String user, String reason, String groupId) {
        list.add(user);
        TIMGroupManagerExt.DeleteMemberParam param = new TIMGroupManagerExt.DeleteMemberParam(groupId, list);
        param.setReason(reason);
        TIMGroupManagerExt.getInstance().deleteGroupMember(param, new TIMValueCallBack<List<TIMGroupMemberResult>>() {
            @Override
            public void onError(int code, String desc) {
                Log.e(TAG, "deleteGroupMember onErr. code: " + code + " errmsg: " + desc);
            }

            @Override
            public void onSuccess(List<TIMGroupMemberResult> results) { //群组成员操作结果
                for (TIMGroupMemberResult r : results) {
                    Log.d(TAG, "result: " + r.getResult()  //操作结果:  0：删除失败；1：删除成功；2：不是群组成员
                            + " user: " + r.getUser());    //用户帐号

                    Log.e(TAG, "onSuccess: " + "删除群成员 成功");
                }
            }
        });
    }

    /**
     * 获取群成员列表
     *
     * @param groupId
     */
    public void getGroupMemeber(String groupId) {
        //获取群组成员信息
        TIMGroupManagerExt.getInstance().getGroupMembers(
                groupId, //群组 ID
                new TIMValueCallBack<List<TIMGroupMemberInfo>>() {
                    @Override
                    public void onError(int i, String s) {
                        Log.e(TAG, "onError: " + i + s);
                    }

                    @Override
                    public void onSuccess(List<TIMGroupMemberInfo> timGroupMemberInfos) {
                        for (TIMGroupMemberInfo info : timGroupMemberInfos) {
                            Log.d(TAG, "user: " + info.getUser() +
                                    "join time: " + info.getJoinTime() +
                                    "role: " + info.getRole());
                            Log.e(TAG, "onSuccess: " + "获取群成员列表成功");
                        }
                    }
                });
    }

    /**
     * 获取加入的群组列表
     */
    public void getGroupList() {
        //获取已加入的群组列表
        TIMGroupManagerExt.getInstance().getGroupList(new TIMValueCallBack<List<TIMGroupBaseInfo>>() {
            @Override
            public void onError(int i, String s) {
                Log.e(TAG, "onError: " + i + "" + s);
            }

            @Override
            public void onSuccess(List<TIMGroupBaseInfo> timGroupBaseInfos) {
                for (TIMGroupBaseInfo info : timGroupBaseInfos) {
                    Log.d(TAG, "group id: " + info.getGroupId() +
                            " group name: " + info.getGroupName() +
                            " group type: " + info.getGroupType());
                    Log.e(TAG, "onSuccess: " + "获取群组列表成功");
                }
            }
        });
    }

    public void deleteGroup(String groupId) {
        //解散群组
        TIMGroupManager.getInstance().deleteGroup(groupId, new TIMCallBack() {
            @Override
            public void onError(int code, String desc) {

                //错误码 code 和错误描述 desc，可用于定位请求失败原因
                //错误码 code 列表请参见错误码表
                Log.d(TAG, "login failed. code: " + code + " errmsg: " + desc);
            }

            @Override
            public void onSuccess() {
                //解散群组成功
                Log.e(TAG, "onSuccess: " + "解散群组成功");
            }
        });
    }

    /**
     * 转让群
     *
     * @param groupId     群id
     * @param newOwenerId 新群主账号id
     */
    public void modifyGroupOwner(String groupId, String newOwenerId) {
        TIMGroupManagerExt.getInstance().modifyGroupOwner(groupId, newOwenerId, new TIMCallBack() {
            @Override
            public void onError(int i, String s) {
                Log.e(TAG, "onError: " + i + "====" + s);

            }

            @Override
            public void onSuccess() {
                Log.e(TAG, "onSuccess: " + "群组转让成功");

            }
        });
    }

    /**
     * 获取指定类型成员
     *
     * @param groupId
     * @param filter  角色过滤类型
     * @param custom  要获取的自定义key列表
     */
    public void getGroupMemberByFliter(String groupId, TIMGroupMemberRoleFilter filter, List<String> custom) {
        TIMGroupManagerExt.getInstance().getGroupMembersByFilter(groupId, 0, filter, custom, 0, new TIMValueCallBack<TIMGroupMemberSucc>() {
            @Override
            public void onError(int i, String s) {
                Log.e(TAG, "onError: " + i + "===" + s);
            }

            @Override
            public void onSuccess(TIMGroupMemberSucc timGroupMemberSucc) {

                Log.e(TAG, "onSuccess: " + "获取指定类型成员成功" + timGroupMemberSucc.getMemberInfoList());

            }
        });
    }

}
