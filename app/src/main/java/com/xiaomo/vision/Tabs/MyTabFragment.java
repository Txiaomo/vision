package com.xiaomo.vision.Tabs;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.xiaomo.vision.MyPage.Register;
import com.xiaomo.vision.R;
import com.xiaomo.vision.Utils.ExampleUtil;

import cn.jpush.android.api.JPushInterface;

import static com.xiaomo.vision.Activity.MainActivity.MESSAGE_RECEIVED_ACTION;

/**
 * Created by Administrator on 2018/4/28 0028.
 */

public class MyTabFragment extends Fragment {
    private static final String packageName = "com.alibaba.android.rimet";
    private Button openDding, openPush,register;
    public static final String KEY_TITLE = "title";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_EXTRAS = "extras";
    private MessageReceiver mMessageReceiver;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_tab_my, container, false);
        initView(view);
        registerMessageReceiver();
        openDding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intentDding = getActivity().getPackageManager().getLaunchIntentForPackage(packageName);

                    startActivity(intentDding);
                } catch (Exception e) {
                    Toast.makeText(getContext(), "error" + e, Toast.LENGTH_SHORT).show();
                }
            }
        });
        openPush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JPushInterface.init(getActivity().getApplicationContext());
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), Register.class);
                startActivity(intent);
            }
        });
        return view;
    }

    //接收自定义消息的广播
    public static class MessageReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
                String messge = intent.getStringExtra(JPushInterface.EXTRA_MESSAGE);
                //附加消息，键值对形式，以Json格式展示
                String extras = intent.getStringExtra(JPushInterface.EXTRA_EXTRA);
                Log.e("TAG", "messge=" + messge + "---extras=" + extras);
                StringBuilder showMsg = new StringBuilder();
                showMsg.append(KEY_MESSAGE + " : " + messge + "\n");
                if (!ExampleUtil.isEmpty(extras)) {
                    showMsg.append(KEY_EXTRAS + " : " + extras + "\n");
                }
                Log.e("TAG", showMsg.toString());
                notification(context, messge);
                if (messge.equals("openDD")) {
                    setIntent(context);
                }
            } else if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {

            }
        }
    }

    public void initView(View view) {
        openDding = (Button) view.findViewById(R.id.startApp);
        openPush = (Button) view.findViewById(R.id.acceptJPush);
        register= (Button) view.findViewById(R.id.register);
    }

    public static void setIntent(Context context) {
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(packageName);
        context.startActivity(intent);
    }

    public void registerMessageReceiver() {
        mMessageReceiver = new MessageReceiver();
        IntentFilter filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        filter.addAction(MESSAGE_RECEIVED_ACTION);
        getActivity().registerReceiver(mMessageReceiver, filter);
    }

    public static void notification(Context context, String msg) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = (NotificationCompat.Builder) new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.vision)
                .setContentTitle("vision")
                .setContentText(msg)
                .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE | Notification.DEFAULT_LIGHTS);

        builder.setDefaults(Notification.FLAG_AUTO_CANCEL);
        notificationManager.notify(2, builder.build());
    }

}
