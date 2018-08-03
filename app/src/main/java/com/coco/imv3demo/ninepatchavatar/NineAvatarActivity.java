package com.coco.imv3demo.ninepatchavatar;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.abbott.mutiimgloader.call.MergeCallBack;
import com.abbott.mutiimgloader.util.JImageLoader;
import com.abbott.mutiimgloader.weixin.WeixinMerge;
import com.coco.imv3demo.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class NineAvatarActivity extends AppCompatActivity {

//    static List<String> urls = new ArrayList<>();
//    List<List<String>> mDatas = new ArrayList<>();

//    static {
//        urls.add("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1505294718&di=b6934dd570c0c6962a8dbbb12eac27f5&src=http://www.zhlzw.com/UploadFiles/Article_UploadFiles/201204/20120412123914329.jpg");
//        urls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1505304803685&di=3a859c35334d8d86177f0cfb8adfdc65&imgtype=0&src=http%3A%2F%2Fwww.zhlzw.com%2FUploadFiles%2FArticle_UploadFiles%2F201204%2F20120412123926750.jpg");
//        urls.add("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1505307059&di=f0681cbe5ff7604b9cd62bdb7b071e6c&src=http://b.zol-img.com.cn/sjbizhi/images/2/750x530/1354868342195.jpg");
//        urls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1505317145477&di=ebea82181a485571bddea5ad41b0b841&imgtype=0&src=http%3A%2F%2Fi3.sinaimg.cn%2Fgm%2F2011%2F1103%2FU4511P115DT20111103112522.jpg");
//        urls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1505317145477&di=26e3b5ab30a9fdcc0b077bc9eea340e1&imgtype=0&src=http%3A%2F%2Fww2.sinaimg.cn%2Fmw600%2F6b146538jw1dztjeivhpuj.jpg");
//        urls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1505317145476&di=9dfc96ab94f153e49cb2db2da09a97cf&imgtype=0&src=http%3A%2F%2Fwww.discuz.images.zq.sd.cn%2FDiscuz%2Fforum%2F201308%2F30%2F193725qm9qwzz7qidmqqmq.jpg");
//        urls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1505317145476&di=fa19d31e36fd331242a014910a13669a&imgtype=0&src=http%3A%2F%2F9.pic.pc6.com%2Fthumb%2Fup%2F2014-6%2F14019543202414731_600_0.jpg");
//        urls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1505317401448&di=dc4492aeeb496dc73880c63c439186d6&imgtype=jpg&src=http%3A%2F%2Fimg2.imgtn.bdimg.com%2Fit%2Fu%3D877642269%2C2202333197%26fm%3D214%26gp%3D0.jpg");
//        urls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1505317145471&di=4f9b528159a635467f39746b91139829&imgtype=0&src=http%3A%2F%2Fwww.16sucai.com%2Fuploadfile%2F2011%2F1009%2F20111009041805525.jpg");
////        urls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1505317145471&di=086a4eed6ca7f934fe1c3765940cf867&imgtype=0&src=http%3A%2F%2Fb.zol-img.com.cn%2Fsjbizhi%2Fimages%2F6%2F320x510%2F1382519980823.jpg");
////        urls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1505317145471&di=a6fc4073f44d99befc39fe3c30be688f&imgtype=0&src=http%3A%2F%2Fb.zol-img.com.cn%2Fsjbizhi%2Fimages%2F6%2F320x510%2F1395393066343.jpg");
////        urls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1505317145471&di=35cbde9ae1b124fedbdaa96a258297fb&imgtype=0&src=http%3A%2F%2Fb.zol-img.com.cn%2Fsjbizhi%2Fimages%2F5%2F320x510%2F1372754988391.jpg");
////        urls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1505317145470&di=19d439fa1a302aef8fd2fcadfbca771c&imgtype=0&src=http%3A%2F%2Fww2.sinaimg.cn%2Fmw600%2F6b146538jw1dztje3wasfj.jpg");
////        urls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1505317523533&di=a1fef3d8ef1dcbd564c9e59d6ac6c3d5&imgtype=jpg&src=http%3A%2F%2Fimg4.imgtn.bdimg.com%2Fit%2Fu%3D2277168614%2C4145779101%26fm%3D214%26gp%3D0.jpg");
////        urls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1505317145469&di=e9d63be606584c29bc5bc8abf109dc9d&imgtype=0&src=http%3A%2F%2Fs4.sinaimg.cn%2Fmw690%2F609f8b00gd5ee83e31233%26690");
////        urls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1505317488354&di=996c983ba9cd30bf04b6bd684b0167ce&imgtype=0&src=http%3A%2F%2Fs6.sinaimg.cn%2Fmiddle%2F10bc65e44c8d713456d55%26960");
//    }

//    MergeCallBack mergeCallBack;
//    private ImageView imageView;
//    private JImageLoader imageLoader;

    private NineGridImageView groudIcon1, groudIcon2, groudIcon3,
            groudIcon4, groudIcon5, groudIcon6, groudIcon7, groudIcon8, groudIcon9;
    private List<String> mPostList1, mPostList2, mPostList3, mPostList4,
            mPostList5, mPostList6, mPostList7, mPostList8, mPostList9;

    private String[] IMG_URL_LIST = {
            "https://pic4.zhimg.com/02685b7a5f2d8cbf74e1fd1ae61d563b_xll.jpg",
            "https://pic4.zhimg.com/fc04224598878080115ba387846eabc3_xll.jpg",
            "https://pic3.zhimg.com/d1750bd47b514ad62af9497bbe5bb17e_xll.jpg",
            "https://pic4.zhimg.com/da52c865cb6a472c3624a78490d9a3b7_xll.jpg",
            "https://pic3.zhimg.com/0c149770fc2e16f4a89e6fc479272946_xll.jpg",
            "https://pic1.zhimg.com/76903410e4831571e19a10f39717988c_xll.png",
            "https://pic3.zhimg.com/33c6cf59163b3f17ca0c091a5c0d9272_xll.jpg",
            "https://pic4.zhimg.com/52e093cbf96fd0d027136baf9b5cdcb3_xll.png",
            "https://pic3.zhimg.com/f6dc1c1cecd7ba8f4c61c7c31847773e_xll.jpg",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nine_avatar);

//        imageView = findViewById(R.id.imageView);
//
//
//        mergeCallBack = new WeixinMerge();
//        imageLoader = new JImageLoader(this);
//        imageLoader.configDefaultPic(R.mipmap.ic_launcher_round);
//        initData();
        groudIcon1 = findViewById(R.id.groudIcon1);
        groudIcon2 = findViewById(R.id.groudIcon2);
        groudIcon3 = findViewById(R.id.groudIcon3);
        groudIcon4 = findViewById(R.id.groudIcon4);
        groudIcon5 = findViewById(R.id.groudIcon5);
        groudIcon6 = findViewById(R.id.groudIcon6);
        groudIcon7 = findViewById(R.id.groudIcon7);
        groudIcon8 = findViewById(R.id.groudIcon8);
        groudIcon9 = findViewById(R.id.groudIcon9);

        mPostList1 = new ArrayList<>();
        mPostList2 = new ArrayList<>();
        mPostList3 = new ArrayList<>();
        mPostList4 = new ArrayList<>();
        mPostList5 = new ArrayList<>();
        mPostList6 = new ArrayList<>();
        mPostList7 = new ArrayList<>();
        mPostList8 = new ArrayList<>();
        mPostList9 = new ArrayList<>();

        for (int i = 0; i < 1; i++) {
            mPostList1.add(IMG_URL_LIST[i]);
        }
        for (int i = 0; i < 2; i++) {
            mPostList2.add(IMG_URL_LIST[i]);
        }
        for (int i = 0; i < 3; i++) {
            mPostList3.add(IMG_URL_LIST[i]);
        }
        for (int i = 0; i < 4; i++) {
            mPostList4.add(IMG_URL_LIST[i]);
        }
        for (int i = 0; i < 5; i++) {
            mPostList5.add(IMG_URL_LIST[i]);
        }
        for (int i = 0; i < 6; i++) {
            mPostList6.add(IMG_URL_LIST[i]);
        }
        for (int i = 0; i < 7; i++) {
            mPostList7.add(IMG_URL_LIST[i]);
        }
        for (int i = 0; i < 8; i++) {
            mPostList8.add(IMG_URL_LIST[i]);
        }
        for (int i = 0; i < 9; i++) {
            mPostList9.add(IMG_URL_LIST[i]);
        }
        NineGridImageViewAdapter<String> mAdapter = new NineGridImageViewAdapter<String>() {
            @Override
            public void onDisplayImage(Context context, ImageView imageView, String s) {
                //显示图片
                Picasso.with(context).load(s).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher_round).into(imageView);
                //显示圆形图片
                //Picasso.with(context).load(s).transform(new CircleImageTransformation()).placeholder(R.mipmap.ic_holding).error(R.mipmap.ic_error).into(imageView);
            }

            @Override
            public ImageView generateImageView(Context context) {
                return super.generateImageView(context);
            }
        };
        groudIcon1.setAdapter(mAdapter);
        groudIcon1.setImagesData(mPostList1);

        groudIcon2.setAdapter(mAdapter);
        groudIcon2.setImagesData(mPostList2);

        groudIcon3.setAdapter(mAdapter);
        groudIcon3.setImagesData(mPostList3);

        groudIcon4.setAdapter(mAdapter);
        groudIcon4.setImagesData(mPostList4);

        groudIcon5.setAdapter(mAdapter);
        groudIcon5.setImagesData(mPostList5);

        groudIcon6.setAdapter(mAdapter);
        groudIcon6.setImagesData(mPostList6);

        groudIcon7.setAdapter(mAdapter);
        groudIcon7.setImagesData(mPostList7);

        groudIcon8.setAdapter(mAdapter);
        groudIcon8.setImagesData(mPostList8);

        groudIcon9.setAdapter(mAdapter);
        groudIcon9.setImagesData(mPostList9);
    }

//    private void initData() {
//        for (int i = 0; i < urls.size(); i++) {
//            mDatas.add(urls);
//            List<String> list = mDatas.get(i);
//            imageLoader.displayImages(list, imageView, mergeCallBack);
//        }
//    }

}
