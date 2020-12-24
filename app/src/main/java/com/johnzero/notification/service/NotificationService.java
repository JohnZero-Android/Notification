package com.johnzero.notification.service;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import com.johnzero.notification.util.Utils;

/**
 * @author: JohnZero
 * @date: 2020-12-05
 **/
public class NotificationService extends NotificationListenerService {
    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        String text= (String) sbn.getNotification().extras.get("android.text");
        if(text!=null){
            Utils.log("open:" + text);
            //获取剪贴板管理器：
            ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            // 创建普通字符型ClipData
            ClipData mClipData = ClipData.newPlainText("Label", text);
            // 将ClipData内容放到系统剪贴板里。
            cm.setPrimaryClip(mClipData);
        }
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        Utils.log("remove:" + sbn.getPackageName());
    }
}
