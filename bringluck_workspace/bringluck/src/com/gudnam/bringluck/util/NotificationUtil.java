package com.gudnam.bringluck.util;

import java.util.HashMap;
import java.util.Iterator;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.gudnam.bringluck.R;

public class NotificationUtil {
	
   public static final int NOTIFICATION_ID = 1;

   public static void sendNotification(Context context, Class<?> viewClass, String title, String content, HashMap<String, String> extraMap)
   {
      NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
 
      Intent intent = new Intent(context.getApplicationContext(), viewClass);
      intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

      Iterator<String> it = extraMap.keySet().iterator();
      while(it.hasNext()){
    	  String key = it.next().toString();
    	  intent.putExtra(key, extraMap.get(key));
      }

      PendingIntent contentIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
      
      NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context).setSmallIcon(R.drawable.alarm_icon)
                                                                                .setContentTitle(title)
                                                                                .setStyle(new NotificationCompat.BigTextStyle().bigText(content))
                                                                                .setContentText(content)
                                                                                .setAutoCancel(true)
                                                                                .setVibrate(new long[] { 0, 500 });
 
      mBuilder.setContentIntent(contentIntent);
      mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
   }
}
