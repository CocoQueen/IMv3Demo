package com.coco.imv3demo;

/**
 * Created by ydx on 16-12-28.
 */
public class ApiConstant {
    /* RELEASE   发布 */
//    public static final String BASE_URL = "http://www.xinwenhd.com/";

    /* DEV  */
//    public static final String BASE_URL = "https://api.heydaycn.com/";
//    public static final String BASE_URL = "http://120.77.215.34:9001/";
    /* local  本地*/
//    public static final String BASE_URL = "http://192.168.1.119:9000/";


    //微信支付
    public static final String WX_APPID = " wxe900ec9a534a24da";

    // splash ad

    public static final String LOADING_LOGO_AD = "loading/logo";

    public static final String LOADING_AD = "loading/ad_android";

    /**
     * user
     */


    /* 绑定设备 */
    public static final String USER_BIND_DEVICE = "user/bind_device";
    /* 修改密码 */
    public static final String USER_CHANGE_PWD = "user/change_password";
    /* 检查验证码 */
    public static final String CHECK_CODE = "user/check_code";
    /* 检查用户是否存在 */
    public static final String USER_CHECK_MOBILE = "user/check_mobile";
    /* 检查用户是否存在 并发送验证码 */
    public static final String USER_CHECK_MOBILE_AND_SENT_CODE = "user/check_mobile_and_sent_code";
    /* 检查用户名是否存在 */
    public static final String USER_CHECK_USERNAME = "user/check_username";
    /* 忘记密码 修改密码 */
    public static final String USER_FORGET_PWD_CHANGE_PWD = "user/forget_password/change_password";
    /* 忘记密码 验证手机号并发送验证码 */
    public static final String USER_FORGET_PWD_SENT_CHECK_CODE = "user/forget_password/sent_check_code";
    /* 用户信息 */
    public static final String USER_INFO = "user/info";
    /* author info */
    public static final String AUTHOR_INFO = "user/author_info";
    /* 手机号登录 */
    public static final String USER_LOGIN = "user/login_from_mobile";
    /* qq登录 */
    public static final String USER_LOGIN_WITH_QQ = "user/login_from_qq";
    /* 自动登录 */
    public static final String USER_LOGIN_FROM_TOKEN = "user/login_from_token";
    /* wechat 登录 */
    public static final String USER_LOGIN_WITH_WECHAT = "user/login_from_wechat";
    /* weibo 登录 */
    public static final String USER_LOGIN_WITH_WEIBO = "user/login_from_weibo";

    /* twitter 登录 */
    public static final String USER_LOGIN_WITH_TWITTER = "user/login_from_twitter";
    /* 注销登录 */
    public static final String USER_LOGOUT = "user/logout";
    /* 发送验证码 */
    public static final String USER_SENT_CODE = "user/sent_check_code";
    /* 注册并登录 */
    public static final String USER_SIGN_UP_WITH_CODE = "user/sign_from_code_and_login";
    /* 验证token */
    public static final String USER_AUTO_LOGIN = "user/login_from_token_and_refresh_user_info";
    /* 更新用户频道 */
    public static final String USER_UPDATE_USER_CHANNEL = "user/update_user_channel";
    /* 更新用户 */
    public static final String USER_UPDATE_INFO = "user/update_user_info";
    /* 用户频道 */
    public static final String USER_CHANNEL = "user/user_channel";
    /* 第三方账号绑定手机号 */
    public static final String USER_BIND_MOBILE = "user/bind_mobile_and_set_password";
    /**
     * app version
     */
    public static final String APP_VERSION_CHECK = "app_version/check_overseas";

    public static final String APP_VERSION_CHECK_INTERNAL_VERSION = "app_version/check_internalversion";

    /**
     * oss controller
     */
    public static final String OSS_GET_TOKEN = "oss/get_token";

    /**
     * news controller
     */
    /* 获取新闻 */
    public static final String NEWS_GET = "news/detail";
    public static final String NEWS_DETAIL_WITH_AD = "news/detail_with_ad_android";
    /* 获取新闻列表 */
    public static final String NEWS_GET_LIST = "news/list";
    public static final String NEWS_LIST_WITH_AD = "news/list_with_ad_android";
    /* 获取全部新闻，仅测试使用 */
    public static final String NEWS_GET_LIST_ALL = "news/list_all";
    /* 获取作者所发的新闻 */
    public static final String NEWS_GET_BY_AUTHOR_ID = "news/list_by_author_id";
    /* 获取推荐频道新闻 */
    public static final String NEWS_RECOMMEND = "news/recommend_news";
    /* 获取公号推荐新闻 */
    public static final String NEWS_LIST_BY_FOLLOW = "news/list_by_follow";
    /* like*/
    public static final String NEWS_LIKE = "news/like";
    /*search*/
    public static final String NEWS_SEARCH = "news/search";
    /*HomeModel recommend news*/
    public static final String NEWS_HOME_RECOMMEND_NEWS_LIST = "news/recommend_news";
    /* chris news list */
    public static final String NEWS_CHRIST_LIST = "news/christ_news";

    /**
     * carousel controller
     */
    /* 获取轮播图 通过频道id */
    public static final String CAROUSEL_GET_LIST = "carousel/list_android";
    /* 获取全部轮播图  */
    public static final String CAROUSEL_GET_LIST_ALL = "carousel/list_all";
    /* 宗教频道轮播图 */
    public static final String CAROUSEL_GET_CHRIST_LIST = "carousel/christ_channel_carousel_android";
    /* 推荐频道轮播图 */
    public static final String CAROUSEL_GET_RECOMMEND_LIST = "carousel/recommend_channel_carousel_android";
    /**
     * channel controller
     */
    /* 获取频道列表 */
    public static final String CHANNEL_GET_USER_CHANNEL_LIST = "channel/user_with_ad";
    /* 获取默认频道 */
    public static final String CHANNEL_GET_GUEST_CHANNEL_LIST = "channel/guest_with_ad";

    /**
     * favorite
     */
    /* 添加收藏 */
    public static final String FAVORITE_ADD = "favourite/add";
    /* 检查收藏 */
    public static final String FAVORITE_CHECK = "favourite/check";
    /* 收藏列表 */
    public static final String FAVORITE_LIST = "favourite/list";
    /* 移除收藏 */
    public static final String FAVORITE_REMOVE = "favourite/remove";
    /* 移除收藏 */
    public static final String FAVORITE_REMOVE_LIST = "favourite/remove_list";

    /**
     * comment
     */
    public static final String COMMENT_ADD = "comment/add";

    public static final String COMMENT_LIKE = "comment/like";

    public static final String COMMENT_LIST = "comment/list";

    public static final String COMMENT_REPLY = "comment/reply";

    public static final String COMMENT_LIST_HOT = "comment/list_hot";

    public static final String COMMENT_LIST_RECENT = "comment/list_recent";

    public static final String COMMENT_MY_NEWS_COMMENT = "comment/my_news_comment";

    public static final String COMMENT_MY_NEWS_REPLY = "comment/my_news_reply";

    public static final String COMMENT_MY_POST_COMMENT = "comment/my_post_comment";

    public static final String COMMENT_MY_POST_REPLY = "comment/my_post_reply";

    /**
     * bible
     */
    public static final String BIBLE_VOLUME_LIST = "bible/chapter";

    public static final String BIBLE_CHAPTER_LIST = "bible/list_chapter";
    /**
     * follow
     */
    public static final String FOLLOW_ADD = "follow/add";

    public static final String FOLLOW_CHECK = "follow/check";

//    public static final String FOLLOW_LIST = "follow/list";

    public static final String FOLLOW_REMOVE = "follow/remove";
    /**
     * pub
     */

    public static final String PUB_INFO = "pub/info";

    public static final String PUB_LIST_PUB_AND_TOP_NEWS = "pub/list_pub_and_top_news";

    public static final String PUB_RECOMMEND = "pub/recommend";

    /**
     * life
     */
    public static final String LIFE_LIKE = "life/like";

    public static final String LIFE_POST_CREATE = "life/post/create";

    public static final String LIFE_POST_CAROUSEL = "life/carousel_android";

    public static final String LIFE_POST_DELETE = "life/post/delete";

    public static final String LIFE_POST_OFFLINE = "life/post/offline";

    public static final String LIFE_POST_ONLINE = "life/post/online";

    public static final String LIFE_POST_UPDATE = "life/post/update";

    public static final String LIFE_POST_DETAIL = "life/post/detail_with_merchant";

    public static final String LIFE_POST_LIST = "life/post_list";

    public static final String LIFE_POST_AND_PRODUCT_LIST = "life/post_and_product_list";

    public static final String LIFE_POST_SEARCH = "life/search";

    public static final String LIFE_POST_SEARCH_WITH_MERCHANT = "life/search_with_merchant";

    public static final String LIFE_POST_SEARCH_WITH_MERCHANT_LITE = "life/search_with_merchant_lite";

    public static final String LIFE_POST_MY_ONLINE = "life/post/my_online_list";

    public static final String LIFE_POST_MY_OffLINE = "life/post/my_offline_list";

    public static final String LIFE_POST_MERCHANT_RECENT_LIST = "life/list_merchant_recent";

    public static final String LIFE_POST_MERCHANT_RECENT_WITH_MERCHANT = "life/list_merchant_recent_with_merchant";

    public static final String LIFE_POST_MERCHANT_RECENT_WITH_MERCHANT_LITE = "life/list_merchant_recent_with_merchant_lite";


    public static final String LIFE_POST_AND_MERCHANT_LIST = "life/post_and_merchant_list";

    public static final String LIFE_POST_AND_MERCHANT_LIST_LITE = "life/post_and_merchant_list_lite";

    public static final String COMPLAIN = "complain/add";

    /**
     * product
     */

    /*商品列表*/
    public static final String PRODUCT_LIST = "product/list";
    /*商品详情*/
    public static final String PRODUCT_DETAIL = "product/detail";
    /*商家的商品列表*/
    public static final String PRODUCT_LIST_BY_MERCHANT = "product/list_by_merchant";
    /* product comment*/
    public static final String PRODUCT_COMMENT_LIST = "product/list_product_comment";
    /**
     * order
     */

    public static final String ORDER_CREATE = "order/create";

    public static final String ORDER_CANCEL = "order/cancel";

    public static final String ORDER_INFO = "order/info";

    public static final String ORDER_LIST = "order/list";

    public static final String ORDER_PAY = "order/pay";

    public static final String ORDER_REFUND = "order/refund";

    public static final String ORDER_webhook = "order/webhook";

    public static final String ORDER_RATING = "order/rating";

    public static final String CHECK_STATUS_SENT_SMS = "/order/check_pay_status_and_sent_sms";

    public static final String ORDER_WX_PAY = "order/wx_pay";

    /**
     * merchant
     */

    public static final String MERCHANT_INFO = "merchant/info";


    //IM相关
    public static final int SDKAPPID = 1400096181;
    public static final int ACCOUNTTYPE = 27611;

    public static final String ADD_USER = "im/add_user_test";
    public static final String CHECK_SIG = "im/check_sig_test";
    public static final String CREATE_LIVE = "im/create_live";
    public static final String GENTLS_SIGNATURE_EX = "im/get_sig";
    public static final String LIST_LIVE = "im/list_live";
    public static final String UPDATE_LIVE = "im/update_live";
    public static final String UPDATE_USERINFO_TEST = "im/update_user_info_test";

    public static final String UPDATE_USER_AND_IM = "user/update_user_info_and_im";
    public static final String SIGN_FROM_CODE_AND_GETSIG = "user/sign_from_code_and_login_and_get_sig";
    public static final String SIGNUP_FROM_WEIBO_AND_GETSIG = "user/login_from_weibo_and_get_sig";
    public static final String SIGNUP_FROM_WECHAT_AND_GETSIG = "user/login_from_wechat_and_get_sig";
    public static final String SIGNUP_FROM_TWITTER_AND_GETSIG = "user/login_from_twitter_and_get_sig";
    public static final String LOGIN_FROM_TOKEN_AND_REFRESH_USERINFO_AND_SIG = "user/login_from_token_and_refresh_user_info_and_get_sig";
    public static final String SIGNUP_FROM_QQ_AND_GETSIG = "user/login_from_qq_and_get_sig";
    public static final String LOGIN_FROM_MOBILE_AND_GETSIG = "user/login_from_mobile_and_get_sig";
    public static final String SIGNUP_FROM_GOOGLE_AND_GETSIG = "user/login_from_google_and_get_sig";
    public static final String SIGNUP_FROM_FACEBOOK_AND_GETSIG = "user/login_from_facebook_and_get_sig";


}
