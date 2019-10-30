package com.example.ren.wanandroid.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ren.wanandroid.R;
import com.example.ren.wanandroid.bean.LoginBean;
import com.example.ren.wanandroid.utils.ApiService;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 登 录
     */
    private Button mLogin;
    public static final String TAG = "LoginActivity";
    /**
     * 注 册
     */
    private Button mRegister;
    private static SharedPreferences sharedPreferences;
    /**
     * 请输入用户名
     */
    private EditText mNameLogin;
    /**
     * 请输入密码
     */
    private EditText mWordLogin;
    private String name;
    private String word;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        initView();
        sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
    }

    private void initView() {
        mLogin = (Button) findViewById(R.id.login);
        mLogin.setOnClickListener(this);
        mRegister = (Button) findViewById(R.id.register);
        mRegister.setOnClickListener(this);
        mNameLogin = (EditText) findViewById(R.id.name_login);
        mWordLogin = (EditText) findViewById(R.id.word_login);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.login:
                initData();
                break;
            case R.id.register:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
        }
    }

    private void initData() {
        name = mNameLogin.getText().toString();
        word = mWordLogin.getText().toString();
        if (name.equals("") &&word.equals("")){
            Toast.makeText(LoginActivity.this, "账号密码不能为空~~~", Toast.LENGTH_SHORT).show();
            return;
        }

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(ApiService.URL)
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(getOkhttpClient())
                        .build();
                ApiService myServer = retrofit.create(ApiService.class);
                myServer.login(name, word)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<ResponseBody>() {

                            @Override
                            public void onSubscribe(Disposable d) {

                            }


                            @Override
                            public void onNext(ResponseBody value) {
                                try {
                                    LoginBean bean = new Gson().fromJson(value.string(), LoginBean.class);
                                    if (bean.getErrorCode()==0){
                                        Toast.makeText(LoginActivity.this, "登陆成功~~~", Toast.LENGTH_SHORT).show();
                                        Intent intent = getIntent();
                                        intent.putExtra("call",name);
                                        setResult(2,intent);
                                        finish();
                                        mNameLogin.setText("");
                                        mWordLogin.setText("");
                                    }else {
                                        Toast.makeText(LoginActivity.this, "登陆失败~~~", Toast.LENGTH_SHORT).show();
                                    }
                                    Log.e(TAG, "onNext: " + value.string());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });

    }

    private OkHttpClient getOkhttpClient() {
        return new OkHttpClient.Builder()
                .addNetworkInterceptor(new CookieInterceptor())
                .build();
    }

    static class CookieInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Response response = null;
            String cookieGet = sharedPreferences.getString("cookie", null);
            if (cookieGet != null) {
                Request.Builder builder = request.newBuilder();
                builder.addHeader("Cookie", cookieGet);
                Request build = builder.build();
                response = chain.proceed(build);
            } else {
                Log.e("liangxq", "intercept: " + request.url() + "\n" + request.body() + "\n" + request.headers());
                response = chain.proceed(request);
                List<String> headers = response.headers("Set-Cookie");
                for (String s : headers) {
                    Log.e("liangxq", "intercept: " + s);
                }
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < headers.size(); i++) {
                    String cookie = headers.get(i);
                    String substring = cookie.substring(0, cookie.indexOf(";"));
                    stringBuilder.append(substring);
                    if (!(i == headers.size() - 1)) {
                        stringBuilder.append(";");
                    }
                }
                sharedPreferences.edit().putString("cookie", stringBuilder.toString()).apply();
            }
            return response;
        }
    }

}
