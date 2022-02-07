package com.example.ouralarm;

import static com.example.ouralarm.App.HS;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    NotificationManagerCompat notificationManagerCompat;
    private static final int NOTIFICATION_PERMISSION_CODE = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        notificationManagerCompat = NotificationManagerCompat.from(this);
    }

    public void scheduleNotification(Context context, long delay, int notificationId) {//delay is after how much time(in millis) from current time you want to schedule the notification


        Intent intent = new Intent(context, HappyBitrhday.class);
        PendingIntent activity = PendingIntent.getActivity(context, notificationId, intent, PendingIntent.FLAG_CANCEL_CURRENT);



        Notification notification = new NotificationCompat.Builder(this, HS).setSmallIcon(R.drawable.ic_baseline_favorite_24)
                .setContentTitle(getResources().getString(R.string.contentTitle))
                .setContentText(getResources().getString(R.string.contentText))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_REMINDER)
                .setStyle(new NotificationCompat.InboxStyle()
                        .addLine(getResources().getString(R.string.Halimem1))
                        .addLine(getResources().getString(R.string.Halimem2))
                        .addLine(getResources().getString(R.string.Halimem3))
                        .addLine(getResources().getString(R.string.Halimem4))
                        .addLine(getResources().getString(R.string.Halimem5))
                        .addLine(getResources().getString(R.string.Halimem6))
                        .addLine(getResources().getString(R.string.Halimem7))
                        .setBigContentTitle(getResources().getString(R.string.bigcontent))
                        .setSummaryText(getResources().getString(R.string.sum)))
                .setContentIntent(activity).setAutoCancel(true)
                .setColor(Color.MAGENTA)
                .build();


        Intent notificationIntent = new Intent(context, MyNotificationPublisher.class);
        notificationIntent.putExtra(MyNotificationPublisher.NOTIFICATION_ID, notificationId);
        notificationIntent.putExtra(MyNotificationPublisher.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, notificationId, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        long futureInMillis = SystemClock.elapsedRealtime() + delay;
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);
    }


    public void HappyBirthday(View view) {

        EditText editText = (EditText)findViewById(R.id.pass);



        if(editText.getText().toString().equals("1005")){
            scheduleNotification(this,1000 * 60 * 3 ,1);
            editText.setText("");
        }
        else{
            editText.setText("");
        }


    }


}