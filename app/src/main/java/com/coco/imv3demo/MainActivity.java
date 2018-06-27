package com.coco.imv3demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tencent.imsdk.TIMCallBack;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMUser;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import rx.Subscriber;
import tencent.tls.platform.TLSErrInfo;
import tencent.tls.platform.TLSHelper;
import tencent.tls.platform.TLSLoginHelper;
import tencent.tls.platform.TLSPwdLoginListener;
import tencent.tls.platform.TLSUserInfo;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";

    private EditText user;
    private EditText pass;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user = findViewById(R.id.user);
        pass = findViewById(R.id.pass);
        login = findViewById(R.id.btn);
        login.setOnClickListener(this);


//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://api.heydaycn.com/")
//                .addConverterFactory(ScalarsConverterFactory.create())
//                .build();
//
//       ApiService service;
//        service = retrofit.create(ApiService.class);

//        Call<String> call = service.getSig("x-auth-token");
//
//        call.enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                String s = response.body();
//                Log.e(TAG, "onResponse: "+s);
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//
//            }
//        });


    }

    public void login() {
        final String user = this.user.getText().toString().trim();
        String pass = this.pass.getText().toString().trim();

        String sig="eJxlj1FrgzAUhd-9FeLzmIkxSgZ7KJ2dQp2UrmXri7gmpre1mqWx2Jb991FbmLDDffs*7uFcLNu2nffp-LFYr5u2Nrk5KeHYT7aDnIc-qBTwvDA50fwfFJ0CLfKiNEL3EFNKPYSGDnBRGyjhblTn6nqbaqAc*C7ve24-fIQw8hlmQwVkD9NoMU4mQda5sCx1mq2*M5*08vXcxBtpPlfAK8YpOYUvajJ7m7MRRKNt4*4RFEWi027qytAN2-C42I3JR-UVYxkj2CZRk-ktzJ4HlQb24j7KYwENCPMG9Cj0AZq6FzyEKfYIusaxfqxfRdhe5A__";


//        TLSLoginHelper loginHelper = TLSLoginHelper.getInstance().init(getApplicationContext(),1400104919,29747,"1.0");
//
////        String userSig = TLSHelper.getInstance().getUserSig(user);
////        TLSHelper tlsHelper = TLSHelper.getInstance();
//        loginHelper.TLSPwdLogin(user, pass.getBytes(), new TLSPwdLoginListener() {
//            @Override
//            public void OnPwdLoginSuccess(TLSUserInfo tlsUserInfo) {
//                String usersig = loginHelper.getUserSig(user);
//                Log.e(TAG, "OnPwdLoginSuccess: "+usersig);
//
//                TIMUser timUser=new TIMUser();
//                timUser.setIdentifier(user);
//                timUser.setAppIdAt3rd(String.valueOf(ApiConstant.SDKAPPID));
//                timUser.setAccountType(String.valueOf(ApiConstant.ACCOUNTTYPE));
//
//                TIMManager.getInstance().login(user, usersig, new TIMCallBack() {
//                    @Override
//                    public void onError(int i, String s) {
//                        Log.e(TAG, "onError: "+i+"===="+s );
//                    }
//
//                    @Override
//                    public void onSuccess() {
//                        Log.e(TAG, "onSuccess: ");
//                    }
//                });
//            }
//
//            @Override
//            public void OnPwdLoginReaskImgcodeSuccess(byte[] bytes) {
//
//            }
//
//            @Override
//            public void OnPwdLoginNeedImgcode(byte[] bytes, TLSErrInfo tlsErrInfo) {
//
//            }
//
//            @Override
//            public void OnPwdLoginFail(TLSErrInfo tlsErrInfo) {
//                Log.e(TAG, "OnPwdLoginFail: "+tlsErrInfo.ErrCode+tlsErrInfo.ExtraMsg);
//            }
//
//            @Override
//            public void OnPwdLoginTimeout(TLSErrInfo tlsErrInfo) {
//
//            }
//        });
        TIMManager.getInstance().login(user, sig, new TIMCallBack() {
            @Override
            public void onError(int code, String desc) {
                //错误码 code 和错误描述 desc，可用于定位请求失败原因
                //错误码 code 列表请参见错误码表
                Log.e(TAG, "login failed. code: " + code + " errmsg: " + desc);
                Toast.makeText(MainActivity.this, "登录失败"+code, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess() {
                Log.e(TAG, "login succ");
                Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(MainActivity.this,ChatActivity.class);
                intent.putExtra("username",user);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn:
                login();
                break;
        }
    }
}
