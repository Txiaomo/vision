package com.xiaomo.vision.Application;

import android.app.Application;
import android.app.Notification;
import android.util.Log;

import com.xiaomo.vision.Activity.MainActivity;
import com.xiaomo.vision.R;

import cn.jpush.android.api.BasicPushNotificationBuilder;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.im.android.api.JMessageClient;

/**
 * Created by Administrator on 2018/4/27 0027.
 */

public class VisionApplication extends Application {
    private static final String TAG="JIGUANG-Example";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG,"[ExampleApplication] onCreate");
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        JMessageClient.init(this);
        BasicPushNotificationBuilder builder=new BasicPushNotificationBuilder(this);
        builder.statusBarDrawable= R.drawable.vision;
        builder.notificationFlags= Notification.FLAG_AUTO_CANCEL
                | Notification.FLAG_SHOW_LIGHTS;//自动消失和呼吸灯闪烁
        builder.notificationDefaults=Notification.DEFAULT_SOUND
                | Notification.DEFAULT_VIBRATE
                | Notification.DEFAULT_LIGHTS;//设置为铃声、震动、呼吸灯闪烁
        JPushInterface.setPushNotificationBuilder(1,builder);
    }
}
