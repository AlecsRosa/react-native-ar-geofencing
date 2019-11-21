package geofrdddew.fuoricitta.it.ar_geofence;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;

public class GeofenceBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "Ciao";
    Context context;

    // ...
    public void onReceive(Context context, Intent intent) {
        this.context = context;

        GeofencingEvent geofencingEvent = GeofencingEvent.fromIntent(intent);

        if (geofencingEvent.hasError()) {
            Log.e(TAG, String.valueOf(geofencingEvent.getErrorCode()));
            return;
        }

        // Get the transition type.
        int geofenceTransition = geofencingEvent.getGeofenceTransition();
        String id = geofencingEvent.getTriggeringGeofences().get(0).getRequestId();

        switch (geofenceTransition) {
            case Geofence.GEOFENCE_TRANSITION_ENTER: sendNotification("Sei nei pressi di " + id); break;
            case Geofence.GEOFENCE_TRANSITION_EXIT: sendNotification("Sei uscito da " + id); break;
        }
    }


    private void sendNotification(String notificationDetails) {
        // Get an instance of the Notification manager
        NotificationManager mNotificationManager = (NotificationManager) this.context.getSystemService(Context.NOTIFICATION_SERVICE);

        // Android O requires a Notification Channel.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "APP NAME";
            // Create the channel for the notification
            NotificationChannel mChannel =  new NotificationChannel("CHANNEL ID", name, NotificationManager.IMPORTANCE_HIGH);

            // Set the Notification Channel for the Notification Manager.
            mNotificationManager.createNotificationChannel(mChannel);
        }

        // Create an explicit content Intent that starts the main Activity.
        Intent notificationIntent = new Intent(this.context.getApplicationContext(), MainActivity.class);

        // Construct a task stack.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this.context);

        // Add the main Activity to the task stack as the parent.
        stackBuilder.addParentStack(MainActivity.class);

        // Push the content Intent onto the stack.
        stackBuilder.addNextIntent(notificationIntent);

        // Get a PendingIntent containing the entire back stack.
        PendingIntent notificationPendingIntent =
                stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        // Get a notification builder that's compatible with platform versions >= 4
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this.context);

        // Define the notification settings.
        builder.setSmallIcon(R.drawable.common_google_signin_btn_icon_dark)
                // In a real app, you may want to use a library like Volley
                // to decode the Bitmap.
                .setColor(Color.RED)
                .setContentTitle(notificationDetails)
                .setContentText("Contenuto della notifica")
                .setContentIntent(notificationPendingIntent);

        // Set the Channel ID for Android O.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder.setChannelId("CHANNEL ID"); // Channel ID
        }

        // Dismiss notification once the user touches it.
        builder.setAutoCancel(true);

        // Issue the notification
        mNotificationManager.notify(0, builder.build());
    }

}
