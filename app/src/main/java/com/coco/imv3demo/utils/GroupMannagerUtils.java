package com.coco.imv3demo.utils;

import android.util.Log;

import com.tencent.imsdk.TIMCallBack;
import com.tencent.imsdk.TIMGroupAddOpt;
import com.tencent.imsdk.TIMGroupManager;
import com.tencent.imsdk.TIMGroupMemberInfo;
import com.tencent.imsdk.TIMGroupMemberRoleType;
import com.tencent.imsdk.TIMGroupSettings;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMUserConfig;
import com.tencent.imsdk.TIMValueCallBack;
import com.tencent.imsdk.ext.group.TIMGroupBaseInfo;
import com.tencent.imsdk.ext.group.TIMGroupDetailInfo;
import com.tencent.imsdk.ext.group.TIMGroupManagerExt;
import com.tencent.imsdk.ext.group.TIMGroupMemberResult;
import com.tencent.imsdk.ext.group.TIMGroupMemberRoleFilter;
import com.tencent.imsdk.ext.group.TIMGroupMemberSucc;
import com.tencent.imsdk.ext.group.TIMGroupPendencyGetParam;
import com.tencent.imsdk.ext.group.TIMGroupPendencyItem;
import com.tencent.imsdk.ext.group.TIMGroupPendencyListGetSucc;
import com.tencent.imsdk.ext.group.TIMGroupPendencyMeta;
import com.tencent.imsdk.ext.group.TIMGroupSelfInfo;

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


    /*群组管理*/

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
//     * @param user    邀请的用户
     * @param groupId 群组id
     * @param list    待加入群组的用户列表
     */
    public void inviteGroupMember(/*String user,*/ String groupId, ArrayList<String> list) {
        //添加用户
//        list.add(user);
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


    /*获取群组资料*/

    /**
     * 设置拉取字段
     *
     * @param customTag 自定义字段
     */
    public void setUserConfig(String customTag) {
        TIMGroupSettings settings = new TIMGroupSettings();

        //设置群资料拉取字段，这里只关心群头像、群类型、群主ID和自定义字段
        TIMGroupSettings.Options groupOpt = new TIMGroupSettings.Options();
        long groupFlags = 0;
        groupFlags |= TIMGroupManager.TIM_GET_GROUP_BASE_INFO_FLAG_FACE_URL
                | TIMGroupManager.TIM_GET_GROUP_BASE_INFO_FLAG_GROUP_TYPE
                | TIMGroupManager.TIM_GET_GROUP_BASE_INFO_FLAG_OWNER_UIN;
        groupOpt.setFlags(groupFlags);
        groupOpt.addCustomTag(customTag);
        settings.setGroupInfoOptions(groupOpt);

        //设置群成员资料拉取字段，这里只关心群名片、群角色
        TIMGroupSettings.Options memberOpt = new TIMGroupSettings.Options();
        long memberFlags = 0;
        memberFlags |= TIMGroupManager.TIM_GET_GROUP_MEM_INFO_FLAG_NAME_CARD
                | TIMGroupManager.TIM_GET_GROUP_MEM_INFO_FLAG_ROLE_INFO;
        memberOpt.setFlags(memberFlags);
        settings.setMemberInfoOptions(memberOpt);

        TIMUserConfig config = new TIMUserConfig();
        config.setGroupSettings(settings);

        //初始化群设置
        TIMManager.getInstance().setUserConfig(config);
    }

    /**
     * 群成员获取群资料
     *
     * @param list 需要获取信息的群组 ID 列表
     */
    public void getGroupDetailInfo(List<String> list) {
        //获取群组详细信息
        TIMGroupManagerExt.getInstance().getGroupDetailInfo(
                list,
                new TIMValueCallBack<List<TIMGroupDetailInfo>>() {
                    @Override
                    public void onError(int i, String s) {
                        Log.e(TAG, "onError: " + i + "===" + s);
                    }

                    @Override
                    public void onSuccess(List<TIMGroupDetailInfo> timGroupDetailInfos) {
                        for (TIMGroupDetailInfo info : timGroupDetailInfos) {
                            Log.d(TAG, "groupId: " + info.getGroupId()           //群组 ID
                                    + " group name: " + info.getGroupName()              //群组名称
                                    + " group owner: " + info.getGroupOwner()            //群组创建者帐号
                                    + " group create time: " + info.getCreateTime()      //群组创建时间
                                    + " group last info time: " + info.getLastInfoTime() //群组信息最后修改时间
                                    + " group last msg time: " + info.getLastMsgTime()  //最新群组消息时间
                                    + " group member num: " + info.getMemberNum());      //群组成员数量
                        }
                    }
                });

    }

    /**
     * 非群成员获取群组资料
     *
     * @param groupList 参数含义同上
     */
    public void getGroupPublicInfo(List<String> groupList) {
        //获取群组公开信息
        TIMGroupManagerExt.getInstance().getGroupPublicInfo(groupList, new TIMValueCallBack<List<TIMGroupDetailInfo>>() {
            @Override
            public void onError(int code, String desc) {
                //错误码 code 和错误描述 desc，可用于定位请求失败原因
                //错误码 code 列表请参见错误码表
                Log.e(TAG, "get gruop list failed: " + code + " desc" + desc);

            }

            @Override
            public void onSuccess(List<TIMGroupDetailInfo> timGroupDetailInfos) {
                //此时TIMGroupDetailInfo只含有群公开资料，其余字段为空
                Log.e(TAG, "onSuccess: " + "非群成员获取群组资料成功");
            }
        });

    }

    /**
     * 获取自己在群组中的信息
     *
     * @param groupId
     */
    public void getSelfInfo(String groupId) {
        TIMGroupManagerExt.getInstance().getSelfInfo(groupId, new TIMValueCallBack<TIMGroupSelfInfo>() {
            @Override
            public void onError(int i, String s) {
                Log.e(TAG, "onError: " + i + "===" + s);
            }

            @Override
            public void onSuccess(TIMGroupSelfInfo timGroupSelfInfo) {
                Log.e(TAG, "onSuccess: " + timGroupSelfInfo.getNameCard());

            }
        });
    }

    /**
     * 获取群内某个人资料
     *
     * @param groupId
     * @param memberId
     */
    public void getGroupMemberInfo(String groupId, List<String> memberId) {
        TIMGroupManagerExt.getInstance().getGroupMembersInfo(groupId, memberId, new TIMValueCallBack<List<TIMGroupMemberInfo>>() {
            @Override
            public void onError(int i, String s) {
                Log.e(TAG, "onError: " + i + "===" + s);
            }

            @Override
            public void onSuccess(List<TIMGroupMemberInfo> timGroupMemberInfos) {
                Log.e(TAG, "onSuccess: " + "获取群内某个人资料成功");
            }
        });

    }

    /*修改群资料*/

    /**
     * 修改群资料
     *
     * @param param 修改群名,修改群简介,修改群头像,修改群公告,修改加群选项,修改群维度自定义字段,设置全员禁言 通过param参数设置传入
     */
    public void modifyGroupInfo(TIMGroupManagerExt.ModifyGroupInfoParam param) {
        TIMGroupManagerExt.getInstance().modifyGroupInfo(param, new TIMCallBack() {
            @Override
            public void onError(int code, String desc) {
                Log.e(TAG, "modify group info failed, code:" + code + "|desc:" + desc);
            }

            @Override
            public void onSuccess() {
                Log.e(TAG, "modify group info succ");
            }
        });
    }

    /**
     * 修改群成员资料
     *
     * @param param 可修改用户群内身份,对群成员进行禁言,修改群成员名片,修改群成员维度自定义字段,修改群消息接收选项
     */
    public void modifyMemberInfo(TIMGroupManagerExt.ModifyMemberInfoParam param) {
        TIMGroupManagerExt.getInstance().modifyMemberInfo(param, new TIMCallBack() {
            @Override
            public void onError(int code, String desc) {
                Log.e(TAG, "modifyMemberInfo failed, code:" + code + "|msg: " + desc);
            }

            @Override
            public void onSuccess() {
                Log.d(TAG, "modifyMemberInfo succ");
            }
        });
    }

    /*拉取群未决消息*/

    public void getGroupPendencyList() {
        TIMGroupPendencyGetParam param = new TIMGroupPendencyGetParam();
        param.setTimestamp(0);//首次获取填 0
        param.setNumPerPage(10);

        TIMGroupManagerExt.getInstance().getGroupPendencyList(param, new TIMValueCallBack<TIMGroupPendencyListGetSucc>() {
            @Override
            public void onError(int code, String desc) {

            }

            @Override
            public void onSuccess(TIMGroupPendencyListGetSucc timGroupPendencyListGetSucc) {
                //meta中的nextStartTimestamp如果不为 0,可以先保存起来，
                // 作为获取下一页数据的参数设置到 TIMGroupPendencyGetParam 中
                TIMGroupPendencyMeta meta = timGroupPendencyListGetSucc.getPendencyMeta();
                Log.d(TAG, meta.getNextStartTimestamp()
                        + "|" + meta.getReportedTimestamp() + "|" + meta.getUnReadCount());

                List<TIMGroupPendencyItem> pendencyItems = timGroupPendencyListGetSucc.getPendencies();
                for (TIMGroupPendencyItem item : pendencyItems) {
                    //对群未决进行相应操作，比如查看/通过/拒绝等
                    //TODO 待完善
                }
            }
        });
    }

    //TODO 群资料存储 群事件消息 群系统消息


}
