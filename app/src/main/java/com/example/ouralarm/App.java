package com.example.ouralarm;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ContentResolver;
import android.content.Context;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Build;

import java.util.List;

public class App extends Application {
    public static final String HS = "Halimem";
    @Override
    public void onCreate(){
        super.onCreate();
        createNotificationChannel();

    }

    private void createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){


            NotificationChannel ch = new NotificationChannel(HS,
                    "Halimem",
                    NotificationManager.IMPORTANCE_HIGH
            );

            Uri sound = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + getPackageName() + "/raw/asik");
            AudioAttributes attributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_NOTIFICATION)

                    .build();

            ch.setDescription("Happy Birthday My Loveeee TEEE AAAMOOOOOO");
            ch.setSound(sound,attributes);

            NotificationManager manager = getSystemService(NotificationManager.class);


            if (manager != null) {
                List<NotificationChannel> channelList = manager.getNotificationChannels();

                for (int i = 0; channelList != null && i < channelList.size(); i++) {
                    manager.deleteNotificationChannel(channelList.get(i).getId());
                }
            }
            assert manager != null;
            manager.createNotificationChannel(ch);
            
        }
    }
}
