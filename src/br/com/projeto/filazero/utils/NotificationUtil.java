package br.com.projeto.filazero.utils;

import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;
import br.com.projeto.filazero.R;



/**
 * @author Ricardo Lecheta
 * 
 */
public class NotificationUtil {
	static int ID = R.drawable.ic_launcher;

	/**
	 * Issues a notification to inform the user that server has sent a message.
	 * 
	 * @param notificationIntent
	 */
	@SuppressLint("NewApi")
	public static void generateNotification(Context context, String message,Intent notificationIntent) {

		NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

		//PendingIntent intent = PendingIntent.getActivity(context, 0,notificationIntent,Intent.FLAG_ACTIVITY_NEW_TASK);

		String title = context.getString(R.string.app_name);
		Intent it = new Intent(context,notificationIntent.getClass());	
		 
		
		Notification.Builder builder = new Notification.Builder(context)
				.setContentTitle(title).setContentText(message)
				.setSmallIcon(R.drawable.ic_launcher)
				.setContentIntent(PendingIntent.getActivity(context, 0, it, Intent.FLAG_ACTIVITY_NEW_TASK));

		Notification notification = builder.build();		

		// Configura a intent para abrir a activity no topo, somente se não
		// estiver aberta
		notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
		Intent.FLAG_ACTIVITY_SINGLE_TOP);

		notification.flags |= Notification.FLAG_AUTO_CANCEL;
		notificationManager.notify(0, notification);
	}
			
	public static void createNotification(Context ctx,String message,Intent intentNotification){
		String title = ctx.getString(R.string.app_name);
		int icone  = R.drawable.ic_launcher;
		Notification notification = new Notification(icone, message, System.currentTimeMillis());	
		 
		PendingIntent intent = PendingIntent.getActivity(ctx, 0,intentNotification,Intent.FLAG_ACTIVITY_NEW_TASK);
					 
		notification.defaults |= Notification.DEFAULT_VIBRATE;
		 
		long[] vibrate = {0,100,200,300};
		 
		notification.vibrate = vibrate;
				 
		notification.setLatestEventInfo(ctx, title, message, intent);
						 
		NotificationManager manager = (NotificationManager) ctx.getSystemService(Context.NOTIFICATION_SERVICE);
		
		intentNotification.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
				Intent.FLAG_ACTIVITY_SINGLE_TOP);
		 
		//Então é só exibir a notificação pronta no sistema
		notification.flags |= Notification.FLAG_AUTO_CANCEL;
		manager.notify(0, notification);
		 
	}
}
