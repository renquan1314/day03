package com.example.ren.wanandroid.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ren.wanandroid.R;
import com.example.ren.wanandroid.bean.RegisterBean;
import com.example.ren.wanandroid.utils.ApiService;
import com.example.ren.wanandroid.utils.RxUtils;
import com.google.gson.Gson;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "RegisterActivity";
    private Toolbar mToolbar;
    /**
     * 邮箱/手机号
     */
    private EditText mRegisterCall;
    /**
     * 输入密码
     */
    private EditText mRegisterWord1;
    /**
     * 确认密码
     */
    private EditText mRegisterWord2;
    /**
     * 注册
     */
    private Button mZhuce;
    private String call;
    private String pass1;
    private String pass2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mRegisterCall = (EditText) findViewById(R.id.register_call);
        mRegisterWord1 = (EditText) findViewById(R.id.register_word1);
        mRegisterWord2 = (EditText) findViewById(R.id.register_word2);
        mZhuce = (Button) findViewById(R.id.zhuce);
        mZhuce.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.zhuce:
                call = mRegisterCall.getText().toString();
                pass1 = mRegisterWord1.getText().toString();
                pass2 = mRegisterWord2.getText().toString();
                if (!TextUtils.isEmpty(call)&&!TextUtils.isEmpty(pass1)&&!TextUtils.isEmpty(pass2)){
                    if (call.length()==11&& pass1.equals(pass2)){
                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(ApiService.URL)
                                .addConverterFactory(GsonConverterFactory.create())
                                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                                .build();
                        ApiService apiService = retrofit.create(ApiService.class);
                        Observable<ResponseBody> observable = apiService.register(call, pass1, pass2);
                        observable.compose(RxUtils.<ResponseBody>rxObserableSchedulerHelper())
                                .subscribe(new Observer<ResponseBody>() {
                                    @Override
                                    public void onSubscribe(Disposable d) {
                                    }

                                    @Override
                                    public void onNext(ResponseBody responseBody) {
                                        RegisterBean bean = null;
                                        try {
                                            bean = new Gson().fromJson(responseBody.string(), RegisterBean.class);
                                            Log.i(TAG, "onNext: "+bean.toString());
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                        if (bean.getErrorCode()==0){
                                            Toast.makeText(RegisterActivity.this, "注册成功~~，请登录", Toast.LENGTH_SHORT).show();
                                            finish();
                                        }else if (bean.getErrorMsg().equals("用户名已经被注册！")){
                                            Toast.makeText(RegisterActivity.this, "用户名已经被注册！", Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onError(Throwable e) {

                                    }

                                    @Override
                                    public void onComplete() {

                                    }
                                });
                    }else{
                        Toast.makeText(RegisterActivity.this,"用户名密码不正确",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(RegisterActivity.this,"用户名密码不能为空",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
