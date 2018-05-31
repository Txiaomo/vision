package com.xiaomo.vision.Activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.WindowManager;
import android.widget.EditText;

import com.xiaomo.vision.R;
import com.xiaomo.vision.Tabs.ListTabFragment;
import com.xiaomo.vision.Tabs.MessageTabFragment;
import com.xiaomo.vision.Tabs.MyTabFragment;
import com.xiaomo.vision.Utils.ExampleUtil;

import java.util.ArrayList;
import java.util.List;

import cn.jpush.android.api.JPushInterface;

public class MainActivity extends FragmentActivity {

    private FragmentPagerAdapter fragmentPagerAdapter;
    private List<Fragment> fragments;
    private static EditText editText;
    private ViewPager viewPager;

    public static final String MESSAGE_RECEIVED_ACTION = "com.example.jpushdemo.MESSAGE_RECEIVED_ACTION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initDate();
    }

    private void initDate() {
        //设备串号，在真机上才能获得，模拟器上为一串0
        String imei = ExampleUtil.getImei(this, "");
        //创建应用时获得的AppKey
        String appKey = ExampleUtil.getAppKey(this);
        //应用程序第一次注册到JPush服务器时,服务器会返回一个唯一的该设备的标识:RegistertionID
//        registrationID = JPushInterface.getRegistrationID(this);
        //应用包名
        String packageName = getPackageName();
        //设备Id
        String deviceId = ExampleUtil.getDeviceId(this);
        //应用版本号
        String version = ExampleUtil.GetVersion(this);
        fragments=new ArrayList<>();
        fragments.add(new MessageTabFragment());
        fragments.add(new ListTabFragment());
        fragments.add(new MyTabFragment());
        fragmentPagerAdapter=new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        };
        viewPager.setAdapter(fragmentPagerAdapter);
    }


    public void initView() {
        editText = (EditText) findViewById(R.id.editText);
        viewPager= (ViewPager) findViewById(R.id.viewPager);
    }

    @Override
    protected void onResume() {
        super.onResume();
        JPushInterface.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        JPushInterface.onPause(this);
    }

}
