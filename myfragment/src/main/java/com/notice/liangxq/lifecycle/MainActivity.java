package com.notice.liangxq.lifecycle;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioGroup;

import com.notice.liangxq.R;
import com.notice.liangxq.fragments.DiscoverFragment;
import com.notice.liangxq.fragments.home.HomeFragment;
import com.notice.liangxq.fragments.MineFragment;
import com.notice.liangxq.fragments.MoreFragment;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    private static final String TAG = "liangxq";
    private RadioGroup radioGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        radioGroup=findViewById(R.id.tabGroup);
        radioGroup.setOnCheckedChangeListener(this);
        initData();
    }
    private void initData() {
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content, HomeFragment.newInstance("",HomeFragment.class));
        fragmentTransaction.commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(outState!=null){
            outState.putString("liangxq","liangxq");
        }
        Log.e(TAG, "onSaveInstanceState: MainActivity" );
    }



    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (checkedId){
            case R.id.tab_home:
                fragmentTransaction.replace(R.id.content, HomeFragment.newInstance("",HomeFragment.class));
                break;
            case R.id.tab_more:
                fragmentTransaction.replace(R.id.content, MoreFragment.newInstance("",MoreFragment.class));
                break;
            case R.id.tab_discover:
                fragmentTransaction.replace(R.id.content, DiscoverFragment.newInstance("",DiscoverFragment.class));
                break;
            case R.id.tab_mine:
                fragmentTransaction.replace(R.id.content, MineFragment.newInstance("",MineFragment.class));
                break;
        }
        fragmentTransaction.commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
