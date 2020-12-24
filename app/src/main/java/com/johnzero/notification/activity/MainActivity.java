package com.johnzero.notification.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;

import com.johnzero.notification.R;
import com.johnzero.notification.service.NotificationService;

import static android.service.notification.NotificationListenerService.requestRebind;

/**
 * @author: JohnZero
 * @date: 2020-12-05
 **/
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestNotificationListener();
    }

    public void requestNotificationListener(){
        String string = Settings.Secure.getString(getContentResolver(), "enabled_notification_listeners");
        if (string.contains(NotificationService.class.getName())) tryReconnectService();
        else startActivity(new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"));
    }

    public void tryReconnectService() {
        toggleNotificationListenerService();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            ComponentName componentName = new ComponentName(getApplicationContext(), NotificationService.class);
            //It say to Notification Manager RE-BIND your service to listen notifications again inmediatelly!
            requestRebind(componentName);
        }
    }

    private void toggleNotificationListenerService() {
        PackageManager pm = getPackageManager();
        pm.setComponentEnabledSetting(new ComponentName(this, NotificationService.class),
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
        pm.setComponentEnabledSetting(new ComponentName(this, NotificationService.class),
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
    }

}